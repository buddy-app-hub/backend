package org.buddy.backend.models;

public class IdentityCard {
    private String number = "";
    private String country = "";

    // Getters and setters
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "IdentityCard{" +
                "number='" + number + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}