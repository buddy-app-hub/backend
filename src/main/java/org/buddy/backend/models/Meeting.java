package org.buddy.backend.models;

import java.util.Date;

public class Meeting {
    private TimeOfDay date;
    private MeetingLocation location;
    private boolean isCancelled = false;
    private boolean isConfirmedByBuddy = false;
    private boolean isConfirmedByElder = false;
    private boolean isRescheduled = false;
    private String activity;
    private Date dateLastModification;
    private int elderRatingForBuddy; // Rating that Elder made to Buddy
    private int buddyRatingForElder; // Rating that Buddy made to Elder

    // Getters and setters

    public TimeOfDay getDate() {
        return date;
    }

    public void setDate(TimeOfDay date) {
        this.date = date;
    }

    public MeetingLocation getLocation() {
        return location;
    }

    public void setLocation(MeetingLocation location) {
        this.location = location;
    }

    public boolean getIsCancelled() {
        return isCancelled;
    }

    public void setIsCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public boolean getIsConfirmedByBuddy() {
        return isConfirmedByBuddy;
    }

    public void setIsConfirmedByBuddy(boolean isConfirmedByBuddy) {
        this.isConfirmedByBuddy = isConfirmedByBuddy;
    }

    public boolean getIsConfirmedByElder() {
        return isConfirmedByElder;
    }

    public void setIsConfirmedByElder(boolean isConfirmedByElder) {
        this.isConfirmedByElder = isConfirmedByElder;
    }

    public boolean getIsRescheduled() {
        return isRescheduled;
    }

    public void setIsRescheduled(boolean isRescheduled) {
        this.isRescheduled = isRescheduled;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public Date getDateLastModification() {
        return dateLastModification;
    }

    public void setDateLastModification(Date dateLastModification) {
        this.dateLastModification = dateLastModification;
    }

    public int getElderRatingForBuddy() {
        return elderRatingForBuddy;
    }

    public void setElderRatingForBuddy(int elderRatingForBuddy) {
        this.elderRatingForBuddy = elderRatingForBuddy;
    }

    public int getBuddyRatingForElder() {
        return buddyRatingForElder;
    }

    public void setBuddyRatingForElder(int buddyRatingForElder) {
        this.buddyRatingForElder = buddyRatingForElder;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "date=" + date +
                ", location=" + location +
                ", isCancelled=" + isCancelled +
                ", isConfirmedByBuddy=" + isConfirmedByBuddy +
                ", isConfirmedByElder=" + isConfirmedByElder +
                ", isRescheduled=" + isRescheduled +
                ", activity='" + activity + '\'' +
                ", dateLastModification='" + dateLastModification + '\'' +
                ", elderRatingForBuddy='" + elderRatingForBuddy + '\'' +
                ", buddyRatingForElder='" + buddyRatingForElder + '\'' +
                '}';
    }
}