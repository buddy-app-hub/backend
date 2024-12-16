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
    @Autowired
    private FirebaseService firebaseService;

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
        // Eliminamos las conexiones
        List<Connection> conns = connRepo.findConnectionsByElderID(id);
        conns.forEach(c -> connRepo.deleteById(c.getId()));

        // Eliminamos la carpeta del usuario de firebase storage
        firebaseService.deleteUserFromStorage(id);

        // Eliminamos los chats de firestore
        firebaseService.deleteUserChats(id);

        // Eliminamos al buddy
        elderRepository.deleteById(id);

        // Eliminamos el usuario de firebase auth
        firebaseService.deleteUserFromAuth(id);
    }

    public Elder findByFirebaseUID(String firebaseUID) {
        return elderRepository.findElderByFirebaseUID(firebaseUID);
    }

    public Elder updateElderProfile(String firebaseUID, ElderProfile updatedProfile, boolean recalcRecommendedBuddies) {
        Elder elder = elderRepository.findElderByFirebaseUID(firebaseUID);
        if (elder == null) {
            throw new ResourceNotFoundException("Elder not found with firebaseUID: " + firebaseUID);
        }

        System.out.println("Updating elder profile for " + firebaseUID);

        elder.setElderProfile(updatedProfile);

        if (recalcRecommendedBuddies) {
            this.recalculateRecommendedBuddies(firebaseUID); // Mandamos a actualizar los recommended buddies
        }

        return elderRepository.save(elder);
    }

    public Elder updateElderPersonalData(String firebaseUID, PersonalData updatedPersonalData, boolean recalcRecommendedBuddies) {
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

                if (recalcRecommendedBuddies) {
                    this.recalculateRecommendedBuddies(firebaseUID); // Tambien actualizamos los recommended buddies porque cambio la ubicacion
                }
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
             * Obtenemos un listado de buddies que cumplen con estas condiciones:
             * 1) que esten en el rango de preferencia de distancia del elder
             * 2) que el elder este dentro del rango de preferencia de los buddies que cumplen con 1)
             * 3) que el buddy este aprobado como tal
             * 4) que el buddy no este bloqueado
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
        System.out.println("Recalculating recommended buddies for elder " + id);

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

            // Obtenemos la lista de buddies ya conectados con este elder
            List<String> connectedBuddyIDs = connRepo.findConnectionsByElderID(id)
                    .stream()
                    .map(Connection::getBuddyID)
                    .collect(Collectors.toList());

            buddiesToRecomend = elder.getRecommendedBuddies();

            // Filtramos buddies ya conectados y bloqueados
            buddiesToRecomend.removeIf(rb
                    -> rb.getBuddy() == null
                    || connectedBuddyIDs.contains(rb.getBuddy().getFirebaseUID())
                    || rb.getBuddy().getIsBlocked()
            );

            return buddiesToRecomend;
        }

        return null;
    }

    public void recalculateRecommendedBuddies(String id) {
        if (elderRepository.existsById(id)) {
            sqsService.updateRecommendedBuddies(id);
        }
    }
}
