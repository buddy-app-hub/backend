package org.buddy.backend.controllers;

import org.buddy.backend.models.Buddy;
import org.buddy.backend.models.Elder;
import org.buddy.backend.models.Relative;
import org.buddy.backend.services.BuddyService;
import org.buddy.backend.services.ElderService;
import org.buddy.backend.services.RelativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
// Para usarlo a nivel metodo: @Operation(security = { @SecurityRequirement(name = "bearer-key") })
public class UserController {

    @Autowired
    private BuddyService buddyService;

    @Autowired
    private ElderService elderService;

    @Autowired
    private RelativeService relativeService;

    @GetMapping("/me")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> getCurrentUser() {
        String firebaseUID = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Buddy buddy = buddyService.findByFirebaseUID(firebaseUID);
        if (buddy != null) {
            return ResponseEntity.ok(buddy);
        }
        Elder elder = elderService.findByFirebaseUID(firebaseUID);
        if (elder != null) {
            return ResponseEntity.ok(elder);
        }
        Relative relative = relativeService.findByFirebaseUID(firebaseUID);
        if (relative != null) {
            return ResponseEntity.ok(relative);
        }
        return ResponseEntity.notFound().build();
    }
}

