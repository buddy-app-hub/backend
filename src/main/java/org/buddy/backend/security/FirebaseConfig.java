package org.buddy.backend.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Configuration
@Profile("!test")
public class FirebaseConfig {

    @Value("${firebase.service-account}")
    private String firebaseKey;

    @Bean
    public FirebaseApp initializeFirebase() {
        try {
            byte[] decodedKey = Base64.getDecoder().decode(firebaseKey);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(new ByteArrayInputStream(decodedKey)))
                    .setStorageBucket("buddy-e29dc.appspot.com") // TODO: leer de env var
                    .build();

            return FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            System.err.println("Failed to initialize Firebase due to an IO exception" + e.getMessage());
            return FirebaseApp.initializeApp();
        }
    }
}