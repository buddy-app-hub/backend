package org.buddy.backend.models;

import java.util.Objects;

public class Address {
    private String streetName;
    private Integer streetNumber;
    private String apartmentNumber;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Coordinates coordinates;

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                ", coordinates='" + coordinates + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        // Si son la misma referencia, directamente es true
        if (this == obj)
            return true;
        // Si son de distintas clases, directamente es false
        if (!(obj instanceof Address))
            return false;

        Address other = (Address) obj;
        return streetName.equals(other.streetName) &&
                streetNumber.equals(other.streetNumber) &&
                apartmentNumber.equals(other.apartmentNumber) &&
                city.equals(other.city) &&
                state.equals(other.state) &&
                postalCode.equals(other.postalCode) &&
                country.equals(other.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, streetNumber, apartmentNumber, city, state, postalCode, country);
    }
}
