package org.buddy.backend.models;

public class WorkerDetails {
    private String company;
    private String position;

    // Getters and setters

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "WorkerDetails{" +
                "company='" + company + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}