package org.buddy.backend.models;

import java.util.ArrayList;
import java.util.List;

public class ElderProfile {
    private String description;
    private List<Interest> interests;
    private List<TimeOfDay> availability;
    private List<String> photos = new ArrayList<>(); // Array ordenado con los nombres de las fotos cargadas en Firebase Storage
    private int globalRating; // Average rating of each of the meetings in which he participated (1 to 5)

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

    public int getGlobalRating() {
        return globalRating;
    }

    public void setGlobalRating(int globalRating) {
        this.globalRating = globalRating;
    }

    @Override
    public String toString() {
        return "ElderProfile{" +
                "description='" + description + '\'' +
                ", interests=" + interests +
                ", availability=" + availability +
                ", photos=" + photos +
                ", globalRating=" + globalRating +
                '}';
    }
}
