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

import org.buddy.backend.models.BuddyWithinRange;
import org.buddy.backend.models.Elder;
import org.buddy.backend.models.ElderProfile;
import org.buddy.backend.models.PersonalData;
import org.buddy.backend.models.RecommendedBuddy;
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

        Elder elderStored = elderService.createElder(elder);
        System.out.println("elder desde controller " + elderStored);
        return elderStored;
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

    @PatchMapping("/{id}/personaldata")
    public ResponseEntity<Elder> updateElderPersonalData(@PathVariable String id, @RequestBody PersonalData updatedPersonalData) {
        Elder updatedElder = elderService.updateElderPersonalData(id, updatedPersonalData);
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

    // Endpoint usando desde mobile para obtener los buddies recomendados previamente procesados por la buddy-recommender-lambda
    @GetMapping("/{id}/buddies/recommended")
    public ResponseEntity<List<RecommendedBuddy>> getRecommendedBuddies(@PathVariable String id) {
        List<RecommendedBuddy> recommendedBuddies = elderService.getRecommendedBuddies(id);
        if (recommendedBuddies != null) {
            return ResponseEntity.ok(recommendedBuddies);
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint usado desde la buddy-recommender-lambda para obtener buddies en el rango del Elder para rankearlos y luego guardarlos usando updateRecommendedBuddies
    @GetMapping("/{id}/buddies")
    public ResponseEntity<List<BuddyWithinRange>> getBuddiesWithinRange(@PathVariable String id) {
        List<BuddyWithinRange> buddiesWithinRange = elderService.getBuddiesWithinRange(id);
        if (buddiesWithinRange != null) {
            return ResponseEntity.ok(buddiesWithinRange);
        }
        return ResponseEntity.notFound().build();
    }
    
    // Endpoint usado desde la buddy-recommender-lambda para guardar a los buddies recomendados con los score resultantes del algoritmo de rankeo usado
    @PatchMapping("/{id}/buddies/recommended")
    public ResponseEntity<Elder> updateRecommendedBuddies(@PathVariable String id, @RequestBody List<RecommendedBuddy> recommendedBuddies) {
        Elder updatedElder = elderService.updateRecommendedBuddies(id, recommendedBuddies);
        if (updatedElder != null) {
            return ResponseEntity.ok(updatedElder);
        }
        return ResponseEntity.notFound().build();
    }
}
