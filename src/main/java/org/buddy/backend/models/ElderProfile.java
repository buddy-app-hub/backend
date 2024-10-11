package org.buddy.backend.models;

import java.util.ArrayList;
import java.util.List;

public class ElderProfile {
    private String description = "";
    private List<Interest> interests = new ArrayList<>();
    private List<TimeOfDay> availability = new ArrayList<>();
    private List<String> photos = new ArrayList<>(); // Array ordenado con los nombres de las fotos cargadas en Firebase Storage
    private Double globalRating = 4.0; // Average rating of each of the meetings in which he participated (1 to 5)
    private ConnectionPreferences connectionPreferences = new ConnectionPreferences();

    // Getters and setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Interest> getInterests() {
        return interests;
    }

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
    }

    public List<TimeOfDay> getAvailability() {
        return availability;
    }

    public void setAvailability(List<TimeOfDay> availability) {
        this.availability = availability;
    }

    public List<String> getPhotos() {
        return photos;
    }

    public void setPhotos(List<String> photos) {
        if (photos == null || photos.size() > 6) {
            throw new IllegalArgumentException("La lista de fotos debe ser como mucho de 6 elementos.");
        }
        this.photos = photos;
    }

    public Double getGlobalRating() {
        return globalRating;
    }

    public void setGlobalRating(Double globalRating) {
        this.globalRating = globalRating;
    }

    public ConnectionPreferences getConnectionPreferences() {
        return connectionPreferences;
    }

    public void setConnectionPreferences(ConnectionPreferences connectionPreferences) {
        this.connectionPreferences = connectionPreferences;
    }

    @Override
    public String toString() {
        return "ElderProfile{" +
                "description='" + description + '\'' +
                ", interests=" + interests +
                ", availability=" + availability +
                ", photos=" + photos +
                ", globalRating=" + globalRating +
                ", connectionPreferences=" + connectionPreferences +
                '}';
    }
}
