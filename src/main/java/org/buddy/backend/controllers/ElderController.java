package org.buddy.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;

import org.buddy.backend.models.Buddy;
import org.buddy.backend.models.BuddyProfile;
import org.buddy.backend.models.Elder;
import org.buddy.backend.models.ElderProfile;
import org.buddy.backend.services.ElderService;

import java.util.List;

@RestController
@RequestMapping("elders")
@SecurityRequirement(name = "bearer-key")
// Para usarlo a nivel metodo: @Operation(security = { @SecurityRequirement(name = "bearer-key") })
public class ElderController {
    @Autowired
    private ElderService elderService;

    @GetMapping
    public List<Elder> getAllElders() {
        return elderService.getAllElders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Elder> getElderById(@PathVariable String id) {
        Elder elder = elderService.getElderById(id);
        if (elder != null) {
            return ResponseEntity.ok(elder);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Elder createElder(@RequestBody Elder elder, HttpServletRequest request) {
        System.out.println(elder);

        return elderService.createElder(elder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Elder> updateElder(@PathVariable String id, @RequestBody Elder elder) {
        Elder updatedElder = elderService.updateElder(id, elder);
        if (updatedElder != null) {
            return ResponseEntity.ok(updatedElder);
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/profile")
    public ResponseEntity<Elder> updateElderProfile(@PathVariable String id, @RequestBody ElderProfile updatedProfile) {
        Elder updatedElder = elderService.updateElderProfile(id, updatedProfile);
        if (updatedElder != null) {
            return ResponseEntity.ok(updatedElder);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteElder(@PathVariable String id) {
        elderService.deleteElder(id);
        return ResponseEntity.noContent().build();
    }
}
