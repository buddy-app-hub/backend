package org.buddy.backend.services;

import org.buddy.backend.models.Relative;
import org.buddy.backend.repositories.RelativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RelativeService {
    @Autowired
    private RelativeRepository relativeRepository;

    public Relative getRelativeByFirstName(String firstName) {
        return relativeRepository.findRelativeByFirstName(firstName);
    }

    public List<Relative> getAllBuddies() {
        return relativeRepository.findAll();
    }

    public Relative getRelativeById(String id) {
        return relativeRepository.findById(id).orElse(null);
    }

    public Relative createRelative(Relative relative) {
        return relativeRepository.save(relative);
    }

    public Relative updateRelative(String id, Relative relative) {
        if (relativeRepository.existsById(id)) {
            relative.setId(id);
            return relativeRepository.save(relative);
        }
        return null;
    }

    public void deleteRelative(String id) {
        relativeRepository.deleteById(id);
    }

    public Relative findByFirebaseUID(String firebaseUID) {
        return relativeRepository.findRelativeByFirebaseUID(firebaseUID);
    }
}
