package org.buddy.backend.services;

import org.buddy.backend.models.Wallet;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WalletService {

    private final RestClient restClient;

    public WalletService(RestClient.Builder restClientBuilder, Environment environment) {
        this.restClient = restClientBuilder
                .baseUrl(environment.getProperty("PAYMENTS_URL", "http://host.docker.internal:8000"))
                .build();
    }

    public Wallet createWallet() {
        return new Wallet();
    }

    public void deleteWallet(String walletID) {
        try {
            ResponseEntity<String> result = this.restClient.delete()
                    .uri("/wallets/" + walletID)
                    .retrieve()
                    .toEntity(String.class);

            System.out.println("Response status: " + result.getStatusCode());
            System.out.println("Wallet eliminada: " + walletID);
        } catch (Exception e) {
            System.err.println("Error eliminando la wallet: " + e.getMessage());
            throw new RuntimeException("No se pudo eliminar la wallet");
        }
    }

}
