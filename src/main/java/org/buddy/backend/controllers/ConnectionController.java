package org.buddy.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;

import org.buddy.backend.models.Connection;
import org.buddy.backend.models.Meeting;
import org.buddy.backend.services.ConnectionService;

import java.util.List;

@RestController
@RequestMapping("connections")
@SecurityRequirement(name = "bearer-key")
// Para usarlo a nivel metodo: @Operation(security = { @SecurityRequirement(name = "bearer-key") })
public class ConnectionController {
    @Autowired
    private ConnectionService connectionService;

    @GetMapping
    public List<Connection> getAllConnections() {
        return connectionService.getAllConnections();
    }

    @GetMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Connection> getConnectionById(@PathVariable String id) {
        Connection connection = connectionService.getConnectionById(id);
        if (connection != null) {
            return ResponseEntity.ok(connection);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(produces = "application/json;charset=UTF-8")
    public Connection createConnection(@RequestBody Connection connection, HttpServletRequest request) {
        return connectionService.createConnection(connection);
    }

    @PutMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Connection> updateConnection(@PathVariable String id, @RequestBody Connection connection) {
        Connection updatedConnection = connectionService.updateConnection(id, connection);
        if (updatedConnection != null) {
            return ResponseEntity.ok(updatedConnection);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Void> deleteConnection(@PathVariable String id) {
        connectionService.deleteConnection(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/buddies/{buddyID}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Connection>> getConnectionsByBuddyID(@PathVariable String buddyID) {
        List<Connection> connections = connectionService.getConnectionsByBuddyID(buddyID);
        if (connections != null) {
            return ResponseEntity.ok(connections);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/elders/{elderID}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<List<Connection>> getConnectionsByElderID(@PathVariable String elderID) {
        List<Connection> connections = connectionService.getConnectionsByElderID(elderID);
        if (connections != null) {
            return ResponseEntity.ok(connections);
        }
        return ResponseEntity.notFound().build();
    }

    /* Si se califica la meeting además de crearse, esto se detecta y se re-calcula el rating global del usuario al que se calificó */
    @PostMapping(value = "/{connectionID}/meetings", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Meeting> createMeeting(@PathVariable String connectionID, @RequestBody Meeting newMeeting) {
        Meeting createdMeeting = connectionService.createMeeting(connectionID, newMeeting);
        if (createdMeeting != null) {
            return ResponseEntity.ok(createdMeeting);
        }

        return ResponseEntity.notFound().build();
    }

    /* Si se califica un meeting, esto se detecta y se re-calcula el rating global del usuario al que se calificó */
    @PutMapping(value = "/{connectionID}/meetings/{meetingID}", produces = "application/json;charset=UTF-8")
    public ResponseEntity<Meeting> updateMeeting(@PathVariable String connectionID, @PathVariable String meetingID, @RequestBody Meeting meeting) {
        Meeting updatedMeeting = connectionService.updateMeeting(connectionID, meetingID, meeting);
        if (updatedMeeting != null) {
            return ResponseEntity.ok(updatedMeeting);
        }

        return ResponseEntity.notFound().build();
    }
}
