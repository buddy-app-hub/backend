package org.buddy.backend.repositories;
import org.buddy.backend.models.Buddy;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BuddyRepository extends MongoRepository<Buddy, String> {
    Buddy findBuddyByFirstName(String firstName);

    Buddy findBuddyByFirebaseUID(String firebaseUID);
}
