package org.buddy.backend.models;

import java.util.Date;

import org.springframework.data.annotation.Id;

public class Meeting {
    private String meetingID;
    private MeetingSchedule schedule = new MeetingSchedule();
    private MeetingLocation location = new MeetingLocation();
    private boolean isCancelled = false;
    private boolean isConfirmedByBuddy = false;
    private boolean isConfirmedByElder = false;
    private boolean isRescheduled = false;
    private boolean isPaymentPending = true;
    private String activity = "";
    private Date dateLastModification = new Date();
    private Review elderReviewForBuddy; // Review that Elder made to Buddy
    private Review buddyReviewForElder; // Review that Buddy made to Elder

    public String getMeetingID() {
        return meetingID;
    }

    public void setMeetingID(String id) {
        this.meetingID = id;
    }

    public MeetingSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(MeetingSchedule schedule) {
        this.schedule = schedule;
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

    public boolean getIsPaymentPending() {
        return isPaymentPending;
    }

    public void setIsPaymentPending(boolean isPaymentPending) {
        this.isPaymentPending = isPaymentPending;
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

    public Review getElderReviewForBuddy() {
        return elderReviewForBuddy;
    }

    public void setElderReviewForBuddy(Review elderReviewForBuddy) {
        this.elderReviewForBuddy = elderReviewForBuddy;
    }

    public Review getBuddyReviewForElder() {
        return buddyReviewForElder;
    }

    public void setBuddyReviewForElder(Review buddyReviewForElder) {
        this.buddyReviewForElder = buddyReviewForElder;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "schedule=" + schedule +
                ", location=" + location +
                ", isCancelled=" + isCancelled +
                ", isConfirmedByBuddy=" + isConfirmedByBuddy +
                ", isConfirmedByElder=" + isConfirmedByElder +
                ", isRescheduled=" + isRescheduled +
                ", activity='" + activity + '\'' +
                ", dateLastModification='" + dateLastModification + '\'' +
                ", elderReviewForBuddy='" + elderReviewForBuddy + '\'' +
                ", buddyReviewForElder='" + buddyReviewForElder + '\'' +
                '}';
    }
}