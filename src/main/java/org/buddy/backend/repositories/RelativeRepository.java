package org.buddy.backend.repositories;

import org.buddy.backend.models.Relative;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface RelativeRepository extends MongoRepository<Relative, String> {
    Relative findRelativeByFirstName(String firstName);

    Relative findRelativeByFirebaseUID(String firebaseUID);
}
