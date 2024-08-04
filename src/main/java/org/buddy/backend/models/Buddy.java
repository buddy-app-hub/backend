package org.buddy.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="buddies")
public class Buddy {
    @Id
    private String firebaseUID;
    private boolean isBlocked = false;
    private boolean isApprovedBuddy = false;
    private boolean isApplicationToBeBuddyUnderReview = false;
    private String registrationMethod;
    private Date registrationDate;
    private boolean isIdentityValidated = false;
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
    private BankAccount bankAccount;
    private Address address;
    private BuddyProfile buddyProfile;

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

    public boolean isApprovedBuddy() {
        return isApprovedBuddy;
    }

    public void setApprovedBuddy(boolean isApprovedBuddy) {
        this.isApprovedBuddy = isApprovedBuddy;
    }

    public boolean isApplicationToBeBuddyUnderReview() {
        return isApplicationToBeBuddyUnderReview;
    }

    public void setApplicationToBeBuddyUnderReview(boolean isApplicationToBeBuddyUnderReview) {
        this.isApplicationToBeBuddyUnderReview = isApplicationToBeBuddyUnderReview;
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

    public boolean isIdentityValidated() {
        return isIdentityValidated;
    }

    public void setIdentityValidated(boolean isIdentityValidated) {
        this.isIdentityValidated = isIdentityValidated;
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

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public BuddyProfile getBuddyProfile() {
        return buddyProfile;
    }

    public void setBuddyProfile(BuddyProfile buddyProfile) {
        this.buddyProfile = buddyProfile;
    }

    @Override
    public String toString() {
        return "Buddy{" +
                "firebaseUID='" + firebaseUID + '\'' +
                ", isBlocked=" + isBlocked +
                ", isApprovedBuddy=" + isApprovedBuddy +
                ", isApplicationToBeBuddyUnderReview=" + isApplicationToBeBuddyUnderReview +
                ", registrationMethod='" + registrationMethod + '\'' +
                ", registrationDate=" + registrationDate +
                ", isIdentityValidated=" + isIdentityValidated +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", nationality='" + nationality + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", identityCard=" + identityCard +
                ", bankAccount=" + bankAccount +
                ", address=" + address +
                ", buddyProfile=" + buddyProfile +
                '}';
    }
}