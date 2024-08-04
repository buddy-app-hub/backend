package org.buddy.backend.repositories;
import java.util.List;

import org.buddy.backend.models.Connection;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConnectionRepository extends MongoRepository<Connection, String> {
    List<Connection> findConnectionsByBuddyID(String buddyID);

    List<Connection> findConnectionsByElderID(String elderID);
}
