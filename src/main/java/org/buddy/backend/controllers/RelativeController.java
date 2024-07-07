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

import jakarta.servlet.http.HttpServletRequest;

import org.buddy.backend.models.Relative;
import org.buddy.backend.services.RelativeService;

import java.util.List;

@RestController
@RequestMapping("relatives")
public class RelativeController {
    @Autowired
    private RelativeService relativeService;

    @GetMapping
    public List<Relative> getAllBuddies() {
        return relativeService.getAllBuddies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Relative> getRelativeById(@PathVariable String id) {
        Relative relative = relativeService.getRelativeById(id);
        if (relative != null) {
            return ResponseEntity.ok(relative);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public Relative createRelative(@RequestBody Relative relative, HttpServletRequest request) {
        System.out.println(relative);

        return relativeService.createRelative(relative);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Relative> updateRelative(@PathVariable String id, @RequestBody Relative relative) {
        Relative updatedRelative = relativeService.updateRelative(id, relative);
        if (updatedRelative != null) {
            return ResponseEntity.ok(updatedRelative);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelative(@PathVariable String id) {
        relativeService.deleteRelative(id);
        return ResponseEntity.noContent().build();
    }
}
