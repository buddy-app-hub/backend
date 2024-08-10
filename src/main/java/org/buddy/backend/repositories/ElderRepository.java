package org.buddy.backend.repositories;

import org.buddy.backend.models.Elder;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface ElderRepository extends MongoRepository<Elder, String> {
    Elder findElderByFirebaseUID(String firebaseUID);
}
