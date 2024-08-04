package org.buddy.backend.models;

import java.util.Date;

public class Meeting {
    private TimeOfDay date;
    private MeetingLocation location;
    private boolean isCancelled;
    private boolean isConfirmedByBuddy;
    private boolean isConfirmedByElder;
    private boolean isRescheduled;
    private String activity;
    private Date dateLastModification;

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

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean isCancelled) {
        this.isCancelled = isCancelled;
    }

    public boolean isConfirmedByBuddy() {
        return isConfirmedByBuddy;
    }

    public void setConfirmedByBuddy(boolean isConfirmedByBuddy) {
        this.isConfirmedByBuddy = isConfirmedByBuddy;
    }

    public boolean isConfirmedByElder() {
        return isConfirmedByElder;
    }

    public void setConfirmedByElder(boolean isConfirmedByElder) {
        this.isConfirmedByElder = isConfirmedByElder;
    }

    public boolean isRescheduled() {
        return isRescheduled;
    }

    public void setRescheduled(boolean isRescheduled) {
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
                '}';
    }
}