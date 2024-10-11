package org.buddy.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;

import org.buddy.backend.models.Buddy;
import org.buddy.backend.models.BuddyProfile;
import org.buddy.backend.models.PersonalData;
import org.buddy.backend.services.BuddyService;

import java.util.List;

@RestController
@RequestMapping("buddies")
// 
@SecurityRequirement(name = "bearer-key")
// Para usarlo a nivel metodo: @Operation(security = { @SecurityRequirement(name = "bearer-key") })
public class BuddyController {
    @Autowired
    private BuddyService buddyService;

    @GetMapping
    public List<Buddy> getAllBuddies() {
        return buddyService.getAllBuddies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Buddy> getBuddyById(@PathVariable String id) {
        Buddy buddy = buddyService.getBuddyById(id);
        if (buddy != null) {
            return ResponseEntity.ok(buddy);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Buddy createBuddy(@RequestBody Buddy buddy, HttpServletRequest request) {
        System.out.println(buddy);

        return buddyService.createBuddy(buddy);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Buddy> updateBuddy(@PathVariable String id, @RequestBody Buddy buddy) {
        Buddy updatedBuddy = buddyService.updateBuddy(id, buddy);
        if (updatedBuddy != null) {
            return ResponseEntity.ok(updatedBuddy);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/profile")
    public ResponseEntity<Buddy> updateBuddyProfile(@PathVariable String id, @RequestBody BuddyProfile updatedProfile) {
        Buddy updatedBuddy = buddyService.updateBuddyProfile(id, updatedProfile);
        if (updatedBuddy != null) {
            return ResponseEntity.ok(updatedBuddy);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/personaldata")
    public ResponseEntity<Buddy> updateBuddyPersonalData(@PathVariable String id, @RequestBody PersonalData updatedPersonalData) {
        Buddy updatedBuddy = buddyService.updateBuddyPersonalData(id, updatedPersonalData);
        if (updatedBuddy != null) {
            return ResponseEntity.ok(updatedBuddy);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuddy(@PathVariable String id) {
        buddyService.deleteBuddy(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/send-approval")
    public ResponseEntity<Void> sendForApproval(@PathVariable String id) {
        Buddy updatedBuddy = buddyService.sendForApproval(id);

        if (updatedBuddy == null) return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/approve")
    public ResponseEntity<Void> approve(@PathVariable String id) {
        return updateApprove(id, true);
    }

    @PostMapping("/{id}/reject")
    public ResponseEntity<Void> reject(@PathVariable String id) {
        return updateApprove(id, false);
    }

    private ResponseEntity<Void> updateApprove(String id, boolean approve) {
        Buddy updatedBuddy = buddyService.updateApprove(id, approve);

        if (updatedBuddy == null) return ResponseEntity.notFound().build();

        return ResponseEntity.noContent().build();
    }
}
