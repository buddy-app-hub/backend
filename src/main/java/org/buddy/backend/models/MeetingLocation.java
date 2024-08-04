package org.buddy.backend.models;

public class MeetingLocation {
    private boolean isEldersHome = false;
    private String placeName;
    private String streetName;
    private String streetNumber;
    private String city;
    private String state;
    private String country;

    // Getters and setters

    public boolean isEldersHome() {
        return isEldersHome;
    }

    public void setEldersHome(boolean isEldersHome) {
        this.isEldersHome = isEldersHome;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "MeetingLocation{" +
                "isEldersHome=" + isEldersHome +
                ", placeName='" + placeName + '\'' +
                ", streetName='" + streetName + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
