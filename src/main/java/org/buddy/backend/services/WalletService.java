package org.buddy.backend.services;

import java.util.List;
import java.util.Map;

import org.buddy.backend.models.Wallet;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class WalletService {

    // private final RestClient restClient;

    public WalletService(RestClient.Builder restClientBuilder, Environment environment) {
        // this.restClient = restClientBuilder
        //         .baseUrl(environment.getProperty("PAYMENTS_URL", "http://host.docker.internal:8000"))
        //         .build();
    }

    public Wallet createWallet() {
        return new Wallet();
        // return this.restClient.post()
        //         .uri("/wallets")
        //         .body(Map.of("transactions", List.of()))
        //         .retrieve()
        //         .body(Wallet.class);
    }

}
