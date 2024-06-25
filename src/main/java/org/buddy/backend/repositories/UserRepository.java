package org.buddy.backend.repositories;

import org.buddy.backend.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByName(String name);
}
