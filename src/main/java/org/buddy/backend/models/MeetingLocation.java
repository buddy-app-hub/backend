package org.buddy.backend.models;

public class MeetingLocation {
    private boolean isEldersHome = false;
    private String placeName;
    private String streetName;
    private Integer streetNumber;
    private String city;
    private String state;
    private String country;
    private Coordinates coordinates;

    // Getters and setters

    public boolean getIsEldersHome() {
        return isEldersHome;
    }

    public void setIsEldersHome(boolean isEldersHome) {
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

    public Integer getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
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

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
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
                ", coordinates='" + coordinates + '\'' +
                '}';
    }
}
