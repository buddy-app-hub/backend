package org.buddy.backend.services;

import org.buddy.backend.models.Connection;
import org.buddy.backend.repositories.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConnectionService {
    @Autowired
    private ConnectionRepository connectionRepository;

    public List<Connection> getAllConnections() {
        return connectionRepository.findAll();
    }

    public Connection getConnectionById(String id) {
        return connectionRepository.findById(id).orElse(null);
    }

    public Connection createConnection(Connection connection) {
        return connectionRepository.save(connection);
    }

    public Connection updateConnection(String id, Connection connection) {
        if (connectionRepository.existsById(id)) {
            connection.setId(id);
            return connectionRepository.save(connection);
        }
        return null;
    }

    public void deleteConnection(String id) {
        connectionRepository.deleteById(id);
    }

    public List<Connection> getConnectionsByBuddyID(String buddyID) {
        return connectionRepository.findConnectionsByBuddyID(buddyID);
    }

    public List<Connection> getConnectionsByElderID(String connectionId) {
        return connectionRepository.findConnectionsByElderID(connectionId);
    }
}
