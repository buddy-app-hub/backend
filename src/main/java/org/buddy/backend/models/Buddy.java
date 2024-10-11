package org.buddy.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "buddies")
public class Buddy {
    @Id
    private String firebaseUID;
    private boolean isBlocked = false;
    private boolean isApprovedBuddy = false;
    private boolean isApplicationToBeBuddyUnderReview = false;
    private String registrationMethod;
    private Date registrationDate = new Date();
    private boolean isIdentityValidated = false;
    private PersonalData personalData = new PersonalData();
    private String email;
    private PhoneNumber phoneNumber = new PhoneNumber();
    private IdentityCard identityCard = new IdentityCard();
    private BankAccount bankAccount = new BankAccount();
    private BuddyProfile buddyProfile = new BuddyProfile();

    private String userType = "buddy";

    public String getUserType() {
        return userType;
    }

    public String getFirebaseUID() {
        return firebaseUID;
    }

    public void setFirebaseUID(String firebaseUID) {
        this.firebaseUID = firebaseUID;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked = isBlocked;
    }

    public boolean getIsApprovedBuddy() {
        return isApprovedBuddy;
    }

    public void setIsApprovedBuddy(boolean isApprovedBuddy) {
        this.isApprovedBuddy = isApprovedBuddy;
    }

    public boolean getIsApplicationToBeBuddyUnderReview() {
        return isApplicationToBeBuddyUnderReview;
    }

    public void setIsApplicationToBeBuddyUnderReview(boolean isApplicationToBeBuddyUnderReview) {
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

    public boolean getIsIdentityValidated() {
        return isIdentityValidated;
    }

    public void setIsIdentityValidated(boolean isIdentityValidated) {
        this.isIdentityValidated = isIdentityValidated;
    }

    public PersonalData getPersonalData() {
        return personalData;
    }

    public void setPersonalData(PersonalData personalData) {
        this.personalData = personalData;
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
                ", personalData='" + personalData + '\'' +                
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", identityCard=" + identityCard +
                ", bankAccount=" + bankAccount +
                ", buddyProfile=" + buddyProfile +
                '}';
    }
}