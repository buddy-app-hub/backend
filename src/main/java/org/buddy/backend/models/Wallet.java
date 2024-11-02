package org.buddy.backend.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wallet {
    @JsonProperty("_id")
    private String id;

    public String getId() {
        return id;
    }
}
