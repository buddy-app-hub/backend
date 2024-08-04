package org.buddy.backend.models;

public class LovedOne {
    private String firstName;
    private String lastName;
    private Integer age;
    private String phoneNumber;
    private String email;
    private String relationshipToElder;

    // Getters and setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelationshipToElder() {
        return relationshipToElder;
    }

    public void setRelationshipToElder(String relationshipToElder) {
        this.relationshipToElder = relationshipToElder;
    }

    @Override
    public String toString() {
        return "LovedOne{" +
                "firstName='" + firstName + '\'' +
                ", lastName=" + lastName +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", relationshipToElder='" + relationshipToElder + '\'' +
                '}';
    }
}
