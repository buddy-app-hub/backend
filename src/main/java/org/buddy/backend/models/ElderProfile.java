package org.buddy.backend.models;

import java.util.List;

public class ElderProfile {
    private String description;
    private List<Interest> interests;
    private List<Availability> availability;

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

    public List<Availability> getAvailability() {
        return availability;
    }

    public void setAvailability(List<Availability> availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "ElderProfile{" +
                "description='" + description + '\'' +
                ", interests=" + interests +
                ", availability=" + availability +
                '}';
    }
}
