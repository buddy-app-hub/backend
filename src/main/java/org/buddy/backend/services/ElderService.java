package org.buddy.backend.services;

import org.buddy.backend.exceptions.ResourceNotFoundException;
import org.buddy.backend.helpers.AddressHelper;
import org.buddy.backend.models.Address;
import org.buddy.backend.models.PersonalData;
import org.buddy.backend.models.Elder;
import org.buddy.backend.models.ElderProfile;
import org.buddy.backend.repositories.ElderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElderService {
    @Autowired
    private ElderRepository elderRepository;
    @Autowired
    private AddressHelper addressHelper;

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

        elder.setElderProfile(updatedProfile);

        return elderRepository.save(elder);
    }

    public Elder updateElderPersonalData(String firebaseUID, PersonalData updatedPersonalData) {
        Elder elder = elderRepository.findElderByFirebaseUID(firebaseUID);
        if (elder == null) {
            throw new ResourceNotFoundException("Elder not found with firebaseUID: " + firebaseUID);
        }

        // Si antes no tenia direccion y esta agregando la primera, o si cambio la
        // direccion, actualizamos las coordenadas
        if (elder.getPersonalData().getAddress() == null
                || !elder.getPersonalData().getAddress().equals(updatedPersonalData.getAddress())) {
            if (updatedPersonalData.getAddress() != null) {
                Address address = addressHelper.processCoordinatesFromAddress(updatedPersonalData.getAddress());
                updatedPersonalData.setAddress(address);
            }
        }

        elder.setPersonalData(updatedPersonalData);

        return elderRepository.save(elder);
    }
}
