package org.buddy.backend.models;

public class ConnectionPreferences {
    private int maxDistanceKM = 10;

    public int getMaxDistanceKM() {
        return maxDistanceKM;
    }

    public void setMaxDistanceKM(int maxDistanceKM) {
        this.maxDistanceKM = maxDistanceKM;
    }

    @Override
    public String toString() {
        return "ConnectionPreferences{" +
                "maxDistanceKM=" + maxDistanceKM +
                '}';
    }
}
