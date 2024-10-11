package org.buddy.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "connections")
public class Connection {
    @Id
    private String id;
    private String elderID;
    private String buddyID;
    private Date creationDate = new Date();
    private List<Meeting> meetings = new ArrayList<>();

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getElderID() {
        return elderID;
    }

    public void setElderID(String elderID) {
        this.elderID = elderID;
    }

    public String getBuddyID() {
        return buddyID;
    }

    public void setBuddyID(String buddyID) {
        this.buddyID = buddyID;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public List<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "id='" + id + '\'' +
                ", elderID='" + elderID + '\'' +
                ", buddyID='" + buddyID + '\'' +
                ", creationDate=" + creationDate +
                ", meetings=" + meetings +
                '}';
    }
}
