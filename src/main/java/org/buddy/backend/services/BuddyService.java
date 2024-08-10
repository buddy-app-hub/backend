package org.buddy.backend.services;

import org.buddy.backend.exceptions.ResourceNotFoundException;
import org.buddy.backend.models.Buddy;
import org.buddy.backend.models.BuddyProfile;
import org.buddy.backend.repositories.BuddyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BuddyService {
    @Autowired
    private BuddyRepository buddyRepository;

    public List<Buddy> getAllBuddies() {
        return buddyRepository.findAll();
    }

    public Buddy getBuddyById(String id) {
        return buddyRepository.findById(id).orElse(null);
    }

    public Buddy createBuddy(Buddy buddy) {
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
        buddyRepository.deleteById(id);
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
}
