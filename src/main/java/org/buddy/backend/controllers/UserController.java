package org.buddy.backend.controllers;

import org.buddy.backend.models.Buddy;
import org.buddy.backend.models.Elder;
import org.buddy.backend.models.UserWithPendingSignUp;
import org.buddy.backend.services.BuddyService;
import org.buddy.backend.services.ElderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

    @SuppressWarnings("deprecation")
    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public ResponseEntity<?> getCurrentUser() {
        String firebaseUID = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(firebaseUID);
        Buddy buddy = buddyService.findByFirebaseUID(firebaseUID);
        if (buddy != null) {
            return ResponseEntity.ok(buddy);
        }
        Elder elder = elderService.findByFirebaseUID(firebaseUID);
        if (elder != null) {
            return ResponseEntity.ok(elder);
        }
        if(firebaseUID != null){
            return ResponseEntity.ok(new UserWithPendingSignUp());
        }
        return ResponseEntity.notFound().build();
    }
}

