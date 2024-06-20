package org.buddy.backend.services;

import org.buddy.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public java.util.Optional<org.buddy.backend.models.User> getUserByName(String name) {
        return userRepository.findUserByName(name);
    }
}
