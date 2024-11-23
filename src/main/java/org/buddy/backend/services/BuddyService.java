package org.buddy.backend.services;

import java.util.List;

import org.buddy.backend.exceptions.ResourceNotFoundException;
import org.buddy.backend.helpers.AddressHelper;
import org.buddy.backend.models.Address;
import org.buddy.backend.models.Buddy;
import org.buddy.backend.models.BuddyProfile;
import org.buddy.backend.models.Connection;
import org.buddy.backend.models.PersonalData;
import org.buddy.backend.models.Wallet;
import org.buddy.backend.repositories.BuddyRepository;
import org.buddy.backend.repositories.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuddyService {

    @Autowired
    private BuddyRepository buddyRepository;
    @Autowired
    private AddressHelper addressHelper;
    @Autowired
    private WalletService walletService;
    @Autowired
    private ConnectionRepository connectionRepo;
    @Autowired
    private FirebaseService firebaseService;

    public List<Buddy> getAllBuddies() {
        return buddyRepository.findAll();
    }

    public Buddy getBuddyById(String id) {
        return buddyRepository.findById(id).orElse(null);
    }

    public Buddy createBuddy(Buddy buddy) {
        if (buddy.getPersonalData() != null && buddy.getPersonalData().getAddress() != null) {
            Address address = addressHelper.processCoordinatesFromAddress(buddy.getPersonalData().getAddress());
            buddy.getPersonalData().setAddress(address);
        }

        try {
            Wallet wallet = walletService.createWallet();

            if (wallet != null) {
                buddy.setWalletId(wallet.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buddyRepository.save(buddy);
    }

    public Buddy updateBuddy(String id, Buddy buddy) {
        if (buddyRepository.existsById(id)) {
            buddy.setFirebaseUID(id);
            return buddyRepository.save(buddy);
        }
        return null;
    }

    public void deleteBuddy(String id) {
        // Eliminamos la wallet
        Buddy buddy = buddyRepository.findById(id).orElse(null);
        if (buddy != null) {
            walletService.deleteWallet(buddy.getWalletId());
        }

        // Eliminamos las conexiones
        List<Connection> conns = connectionRepo.findConnectionsByBuddyID(id);
        conns.forEach(c -> connectionRepo.deleteById(c.getId()));

        // Eliminamos la carpeta del usuario de firebase storage
        firebaseService.deleteUserFromStorage(id);

        // Eliminamos los chats de firestore
        firebaseService.deleteUserChats(id);

        // Eliminamos al buddy
        buddyRepository.deleteById(id);

        // Eliminamos el usuario de firebase auth
        firebaseService.deleteUserFromAuth(id);
    }

    public Buddy findByFirebaseUID(String firebaseUID) {
        return buddyRepository.findBuddyByFirebaseUID(firebaseUID);
    }

    public Buddy updateBuddyProfile(String firebaseUID, BuddyProfile updatedProfile) {
        Buddy buddy = buddyRepository.findBuddyByFirebaseUID(firebaseUID);
        if (buddy == null) {
            throw new ResourceNotFoundException("Buddy not found with firebaseUID: " + firebaseUID);
        }

        buddy.setBuddyProfile(updatedProfile);

        return buddyRepository.save(buddy);
    }

    public Buddy updateBuddyPersonalData(String firebaseUID, PersonalData updatedPersonalData) {
        Buddy buddy = buddyRepository.findBuddyByFirebaseUID(firebaseUID);
        if (buddy == null) {
            throw new ResourceNotFoundException("Buddy not found with firebaseUID: " + firebaseUID);
        }

        // Si antes no tenia direccion y esta agregando la primera, o si cambio la
        // direccion, actualizamos las coordenadas
        if (buddy.getPersonalData() == null || buddy.getPersonalData().getAddress() == null
                || !buddy.getPersonalData().getAddress().equals(updatedPersonalData.getAddress())) {
            if (updatedPersonalData.getAddress() != null) {
                Address address = addressHelper.processCoordinatesFromAddress(updatedPersonalData.getAddress());
                updatedPersonalData.setAddress(address);
            }
        }

        buddy.setPersonalData(updatedPersonalData);

        return buddyRepository.save(buddy);
    }

    public Buddy sendForApproval(String id) {
        Buddy buddy = buddyRepository.findById(id).orElse(null);

        if (buddy == null) {
            return null;
        }

        boolean isApproved = buddy.getIsApprovedBuddy();
        boolean isUnderReview = buddy.getIsApplicationToBeBuddyUnderReview();

        if (isApproved || isUnderReview) {
            return null;
        }

        buddy.setIsApplicationToBeBuddyUnderReview(true);
        return buddyRepository.save(buddy);
    }

    public Buddy updateApprove(String id, boolean approve) {
        Buddy buddy = buddyRepository.findById(id).orElse(null);

        if (buddy == null) {
            return null;
        }

        buddy.setIsApplicationToBeBuddyUnderReview(false);
        buddy.setIsApprovedBuddy(approve);
        return buddyRepository.save(buddy);
    }

    public Buddy updateIdentityValidated(String id, boolean approve) {
        Buddy buddy = buddyRepository.findById(id).orElse(null);

        if (buddy == null) {
            return null;
        }

        buddy.setIsIdentityValidated(approve);
        return buddyRepository.save(buddy);
    }
}
