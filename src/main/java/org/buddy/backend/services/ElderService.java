package org.buddy.backend.services;

import java.util.List;
import java.util.stream.Collectors;

import org.buddy.backend.aws.SqsService;
import org.buddy.backend.exceptions.ResourceNotFoundException;
import org.buddy.backend.helpers.AddressHelper;
import org.buddy.backend.models.Address;
import org.buddy.backend.models.Buddy;
import org.buddy.backend.models.BuddyWithinRange;
import org.buddy.backend.models.Connection;
import org.buddy.backend.models.Elder;
import org.buddy.backend.models.ElderProfile;
import org.buddy.backend.models.PersonalData;
import org.buddy.backend.models.RecommendedBuddy;
import org.buddy.backend.repositories.BuddyRepository;
import org.buddy.backend.repositories.ConnectionRepository;
import org.buddy.backend.repositories.ElderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElderService {
    @Autowired
    private ElderRepository elderRepository;
    @Autowired
    private BuddyRepository buddyRepository;
    @Autowired
    private AddressHelper addressHelper;
    @Autowired
    private SqsService sqsService;
    @Autowired
    private ConnectionRepository connRepo;

    public List<Elder> getAllElders() {
        return elderRepository.findAll();
    }

    public Elder getElderById(String id) {
        return elderRepository.findById(id).orElse(null);
    }

    public Elder createElder(Elder elder) {
        if (elder.getPersonalData() != null && elder.getPersonalData().getAddress() != null) {
            Address address = addressHelper.processCoordinatesFromAddress(elder.getPersonalData().getAddress());
            elder.getPersonalData().setAddress(address);
        }

        return elderRepository.save(elder);
    }

    public Elder updateElder(String id, Elder elder) {
        if (elderRepository.existsById(id)) {
            elder.setFirebaseUID(id);
            return elderRepository.save(elder);
        }
        return null;
    }

    public void deleteElder(String id) {
        elderRepository.deleteById(id);
    }

    public Elder findByFirebaseUID(String firebaseUID) {
        return elderRepository.findElderByFirebaseUID(firebaseUID);
    }

    public Elder updateElderProfile(String firebaseUID, ElderProfile updatedProfile) {
        Elder elder = elderRepository.findElderByFirebaseUID(firebaseUID);
        if (elder == null) {
            throw new ResourceNotFoundException("Elder not found with firebaseUID: " + firebaseUID);
        }

        System.out.println("Updating elder profile for " + firebaseUID);

        elder.setElderProfile(updatedProfile);

        sqsService.updateRecommendedBuddies(firebaseUID); // Mandamos a actualizar los recommended buddies

        return elderRepository.save(elder);
    }

    public Elder updateElderPersonalData(String firebaseUID, PersonalData updatedPersonalData) {
        System.out.println("Updating elder personal data for " + firebaseUID);

        Elder elder = elderRepository.findElderByFirebaseUID(firebaseUID);
        if (elder == null) {
            throw new ResourceNotFoundException("Elder not found with firebaseUID: " + firebaseUID);
        }

        // Si antes no tenia direccion y esta agregando la primera, o si cambio la
        // direccion, actualizamos las coordenadas y los recommended buddies
        if (elder.getPersonalData() == null || elder.getPersonalData().getAddress() == null
                || !elder.getPersonalData().getAddress().equals(updatedPersonalData.getAddress())) {
            if (updatedPersonalData.getAddress() != null) {
                System.out.println("Updating address and getting coordinates for new address");

                Address address = addressHelper.processCoordinatesFromAddress(updatedPersonalData.getAddress());
                updatedPersonalData.setAddress(address);

                sqsService.updateRecommendedBuddies(firebaseUID); // Tambien actualizamos los recommended buddies porque cambio la ubicacion
            }
        }

        elder.setPersonalData(updatedPersonalData);

        

        return elderRepository.save(elder);
    }

    public List<BuddyWithinRange> getBuddiesWithinRange(String id) {
        // Obtenemos el rango en km de preferencia del elder
        Elder elder = elderRepository.findById(id).orElse(null);

        if (elder != null) {
            Double[] coords = elder.getPersonalData().getAddress().getCoordinates().getCoordinates().toArray(new Double[0]);
            int rangeInMeters = elder.getElderProfile().getConnectionPreferences().getMaxDistanceKM() * 1000;
            
            /*
             * Obtenemos un listado de buddies que cumplen con dos condiciones:
             * 1) que esten en el rango de preferencia de distancia del elder
             * 2) que el elder este dentro del rango de preferencia de los buddies que cumplen con 1)
             */
            List<BuddyWithinRange> buddiesInRange = buddyRepository.findBuddiesWithinRange(
                coords[0], coords[1],
                rangeInMeters
            );

            // Sacamos a los buddies con los que ya haya conectado el elder
            List<String> connectedBuddyIDs = connRepo.findConnectionsByElderID(id)
                .stream()
                .map(Connection::getBuddyID)
                .collect(Collectors.toList());

            buddiesInRange.removeIf(b -> connectedBuddyIDs.contains(b.getBuddy().getFirebaseUID()));

            return buddiesInRange;
        }

        return null;
    }

    public Elder updateRecommendedBuddies(String id, List<RecommendedBuddy> recommendedBuddies) {
        Elder elder = elderRepository.findById(id).orElse(null);

        if (elder != null) {
            elder.setRecommendedBuddies(recommendedBuddies);

            return elderRepository.save(elder);
        }
        return null;
    }

    public List<RecommendedBuddy> getRecommendedBuddies(String id) {
        Elder elder = elderRepository.findById(id).orElse(null);

        
        if (elder != null) {
            List<RecommendedBuddy> buddiesToRecomend;
            
            elder.getRecommendedBuddies().forEach(rb -> {
                Buddy buddy = buddyRepository.findBuddyByFirebaseUID(rb.getBuddyID()); 
                if (buddy != null) {
                    rb.setBuddy(buddy); // No vamos a guardarlo en la db, solo guardamos el ID
                }
            });

            // Sacamos a los buddies con los que ya haya conectado el elder
            List<String> connectedBuddyIDs = connRepo.findConnectionsByElderID(id)
            .stream()
            .map(Connection::getBuddyID)
            .collect(Collectors.toList());

            buddiesToRecomend = elder.getRecommendedBuddies();

            buddiesToRecomend.removeIf(b -> connectedBuddyIDs.contains(b.getBuddy().getFirebaseUID()));

            return buddiesToRecomend;
        }
        return null;
    }
}
