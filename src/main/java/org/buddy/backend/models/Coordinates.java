package org.buddy.backend.models;

import java.util.List;

// GeoJSON object to use geospatial index 2dsphere in MongoDB
public class Coordinates {
    private String type = "Point";
    private List<Double> coordinates; // [longitude, latitude]

    public String getType() {
        return type;
    }

    public List<Double> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "type='" + type + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
