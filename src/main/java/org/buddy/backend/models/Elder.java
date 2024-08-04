package org.buddy.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="elders")
public class Elder {
    @Id
    private String firebaseUID;
    private boolean isBlocked = false;
    private String registrationMethod;
    private Date registrationDate;
    private boolean lovedOneMode;
    private LovedOne lovedOne;
    private String firstName;
    private String lastName;
    private Integer age;
    private String gender;
    private Date birthDate;
    private String nationality;
    private String maritalStatus;
    private String email;
    private PhoneNumber phoneNumber;
    private IdentityCard identityCard;
    private Address address;
    private ElderProfile elderProfile;

    // Getters and setters

    public String getFirebaseUID() {
        return firebaseUID;
    }

    public void setFirebaseUID(String firebaseUID) {
        this.firebaseUID = firebaseUID;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public String getRegistrationMethod() {
        return registrationMethod;
    }

    public void setRegistrationMethod(String registrationMethod) {
        this.registrationMethod = registrationMethod;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public boolean isLovedOneMode() {
        return lovedOneMode;
    }

    public void setLovedOneMode(boolean lovedOneMode) {
        this.lovedOneMode = lovedOneMode;
    }

    public LovedOne getLovedOne() {
        return lovedOne;
    }

    public void setLovedOne(LovedOne lovedOne) {
        this.lovedOne = lovedOne;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(PhoneNumber phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public IdentityCard getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(IdentityCard identityCard) {
        this.identityCard = identityCard;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ElderProfile getElderProfile() {
        return elderProfile;
    }

    public void setElderProfile(ElderProfile elderProfile) {
        this.elderProfile = elderProfile;
    }

    @Override
    public String toString() {
        return "Elder{" +
                "firebaseUID='" + firebaseUID + '\'' +
                ", isBlocked=" + isBlocked +
                ", registrationMethod='" + registrationMethod + '\'' +
                ", registrationDate=" + registrationDate +
                ", lovedOneMode=" + lovedOneMode +
                ", lovedOne=" + lovedOne +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", nationality='" + nationality + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", identityCard=" + identityCard +
                ", address=" + address +
                ", elderProfile=" + elderProfile +
                '}';
    }
}