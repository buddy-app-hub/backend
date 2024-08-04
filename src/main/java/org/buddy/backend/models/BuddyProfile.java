package org.buddy.backend.models;

import java.util.List;

public class BuddyProfile {
    private boolean isOnPause = false;
    private String description;
    private StudentDetails studentDetails;
    private WorkerDetails workerDetails;
    private List<Interest> interests;
    private List<TimeOfDay> availability;

    // Getters and setters

    public boolean isOnPause() {
        return isOnPause;
    }

    public void setOnPause(boolean isOnPause) {
        this.isOnPause = isOnPause;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StudentDetails getStudentDetails() {
        return studentDetails;
    }

    public void setStudentDetails(StudentDetails studentDetails) {
        this.studentDetails = studentDetails;
    }

    public WorkerDetails getWorkerDetails() {
        return workerDetails;
    }

    public void setWorkerDetails(WorkerDetails workerDetails) {
        this.workerDetails = workerDetails;
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

    @Override
    public String toString() {
        return "BuddyProfile{" +
                "isOnPause=" + isOnPause +
                ", description='" + description + '\'' +
                ", studentDetails=" + studentDetails +
                ", workerDetails=" + workerDetails +
                ", interests=" + interests +
                ", availability=" + availability +
                '}';
    }
}