package org.buddy.backend.security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class FirebaseConfig {


    @Value("${firebase.service-account.path}")
    private String firebaseKeyPath;

    @Bean
    public FirebaseApp initializeFirebase() {
        try {
            FileInputStream serviceAccount =
                    new FileInputStream(firebaseKeyPath);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            return FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            System.err.println("Failed to initialize Firebase due to an IO exception" + e.getMessage());
            return FirebaseApp.initializeApp();
        }
    }
}