package org.buddy.backend.services;

import org.buddy.backend.models.Elder;
import org.buddy.backend.repositories.ElderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ElderService {
    @Autowired
    private ElderRepository elderRepository;

    public Elder getElderByFirstName(String firstName) {
        return elderRepository.findElderByFirstName(firstName);
    }

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
}
