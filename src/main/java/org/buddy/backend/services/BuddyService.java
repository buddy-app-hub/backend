package org.buddy.backend.services;

import org.buddy.backend.models.Buddy;
import org.buddy.backend.repositories.BuddyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BuddyService {
    @Autowired
    private BuddyRepository buddyRepository;

    public Buddy getBuddyByFirstName(String firstName) {
        return buddyRepository.findBuddyByFirstName(firstName);
    }

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
            buddy.setId(id);
            return buddyRepository.save(buddy);
        }
        return null;
    }

    public void deleteBuddy(String id) {
        buddyRepository.deleteById(id);
    }
}
