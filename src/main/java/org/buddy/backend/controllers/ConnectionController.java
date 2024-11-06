package org.buddy.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @SuppressWarnings("deprecation")
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Connection> getConnectionById(@PathVariable String id) {
        Connection connection = connectionService.getConnectionById(id);
        if (connection != null) {
            return ResponseEntity.ok(connection);
        }
        return ResponseEntity.notFound().build();
    }

    @SuppressWarnings("deprecation")
    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Connection createConnection(@RequestBody Connection connection, HttpServletRequest request) {
        return connectionService.createConnection(connection);
    }

    @SuppressWarnings("deprecation")
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Connection> updateConnection(@PathVariable String id, @RequestBody Connection connection) {
        Connection updatedConnection = connectionService.updateConnection(id, connection);
        if (updatedConnection != null) {
            return ResponseEntity.ok(updatedConnection);
        }
        return ResponseEntity.notFound().build();
    }

    @SuppressWarnings("deprecation")
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Void> deleteConnection(@PathVariable String id) {
        connectionService.deleteConnection(id);
        return ResponseEntity.noContent().build();
    }

    @SuppressWarnings("deprecation")
    @GetMapping(value = "/buddies/{buddyID}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Connection>> getConnectionsByBuddyID(@PathVariable String buddyID) {
        List<Connection> connections = connectionService.getConnectionsByBuddyID(buddyID);
        if (connections != null) {
            return ResponseEntity.ok(connections);
        }
        return ResponseEntity.notFound().build();
    }

    @SuppressWarnings("deprecation")
    @GetMapping(value = "/elders/{elderID}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Connection>> getConnectionsByElderID(@PathVariable String elderID) {
        List<Connection> connections = connectionService.getConnectionsByElderID(elderID);
        if (connections != null) {
            return ResponseEntity.ok(connections);
        }
        return ResponseEntity.notFound().build();
    }

    /* Si se califica la meeting además de crearse, esto se detecta y se re-calcula el rating global del usuario al que se calificó */
    @SuppressWarnings("deprecation")
    @PostMapping(value = "/{connectionID}/meetings", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Meeting> createMeeting(@PathVariable String connectionID, @RequestBody Meeting newMeeting) {
        Meeting createdMeeting = connectionService.createMeeting(connectionID, newMeeting);
        if (createdMeeting != null) {
            return ResponseEntity.ok(createdMeeting);
        }

        return ResponseEntity.notFound().build();
    }

    /* Si se califica un meeting, esto se detecta y se re-calcula el rating global del usuario al que se calificó */
    @SuppressWarnings("deprecation")
    @PutMapping(value = "/{connectionID}/meetings/{meetingID}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Meeting> updateMeeting(@PathVariable String connectionID, @PathVariable String meetingID, @RequestBody Meeting meeting) {
        Meeting updatedMeeting = connectionService.updateMeeting(connectionID, meetingID, meeting);
        if (updatedMeeting != null) {
            return ResponseEntity.ok(updatedMeeting);
        }

        return ResponseEntity.notFound().build();
    }
}
