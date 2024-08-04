package org.buddy.backend.models;

public class PhoneNumber {
    private String number;
    private String countryCode;

    // Getters and setters

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "number='" + number + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
