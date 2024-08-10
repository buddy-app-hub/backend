package org.buddy.backend.services;

import org.buddy.backend.exceptions.ResourceNotFoundException;
import org.buddy.backend.models.Buddy;
import org.buddy.backend.models.BuddyProfile;
import org.buddy.backend.models.PersonalData;
import org.buddy.backend.models.Elder;
import org.buddy.backend.models.ElderProfile;
import org.buddy.backend.models.PersonalData;
import org.buddy.backend.repositories.ElderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ElderService {
    @Autowired
    private ElderRepository elderRepository;

    public List<Elder> getAllElders() {
        return elderRepository.findAll();
    }

    public Elder getElderById(String id) {
        return elderRepository.findById(id).orElse(null);
    }

    public Elder createElder(Elder elder) {
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

        elder.setElderProfile(updatedProfile);

        return elderRepository.save(elder);
    }

    public Elder updateElderPersonalData(String firebaseUID, PersonalData updatedPersonalData) {
        Elder elder = elderRepository.findElderByFirebaseUID(firebaseUID);
        if (elder == null) {
            throw new ResourceNotFoundException("Elder not found with firebaseUID: " + firebaseUID);
        }

        elder.setPersonalData(updatedPersonalData);

        return elderRepository.save(elder);
    }

}
