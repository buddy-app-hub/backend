package org.buddy.backend.security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.io.ByteArrayInputStream;
import java.util.Base64;

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
                    .build();

            return FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            System.err.println("Failed to initialize Firebase due to an IO exception" + e.getMessage());
            return FirebaseApp.initializeApp();
        }
    }
}