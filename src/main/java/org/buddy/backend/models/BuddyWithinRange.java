package org.buddy.backend.models;

public class BuddyWithinRange {
    private Buddy buddy;
    private Double distanceToKM;

    public Buddy getBuddy() {
        return buddy;
    }

    public Double getDistanceToKM() {
        return distanceToKM;
    }

    @Override
    public String toString() {
        return "BuddyWithinRange{" +
                ", buddy=" + buddy +
                ", distanceToKM=" + distanceToKM +
                '}';
    }
}
