package org.buddy.backend.models;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Meeting {
    @Id
    private String meetingID;
    private TimeOfDay date = new TimeOfDay();
    private MeetingLocation location = new MeetingLocation();
    private boolean isCancelled = false;
    private boolean isConfirmedByBuddy = false;
    private boolean isConfirmedByElder = false;
    private boolean isRescheduled = false;
    private String activity = "";
    private Date dateLastModification = new Date();
    private Float elderRatingForBuddy; // Rating that Elder made to Buddy
    private Float buddyRatingForElder; // Rating that Buddy made to Elder

    // Getters and setters
    public String getMeetingID() {
        return meetingID;
    }

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

    public Float getElderRatingForBuddy() {
        return elderRatingForBuddy;
    }

    public void setElderRatingForBuddy(Float elderRatingForBuddy) {
        this.elderRatingForBuddy = elderRatingForBuddy;
    }

    public Float getBuddyRatingForElder() {
        return buddyRatingForElder;
    }

    public void setBuddyRatingForElder(Float buddyRatingForElder) {
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