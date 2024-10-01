package org.buddy.backend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "elders")
public class Elder {
    @Id
    private String firebaseUID;
    private boolean isBlocked = false;
    private String registrationMethod;
    private Date registrationDate;
    private boolean onLovedOneMode;
    private LovedOne lovedOne;
    private PersonalData personalData;
    private String email;
    private PhoneNumber phoneNumber;
    private IdentityCard identityCard;
    private ElderProfile elderProfile;
    private List<RecommendedBuddy> recommendedBuddies;

    private String userType = "elder";

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

    public boolean getOnLovedOneMode() {
        return onLovedOneMode;
    }

    public void setOnLovedOneMode(boolean onLovedOneMode) {
        this.onLovedOneMode = onLovedOneMode;
    }

    public LovedOne getLovedOne() {
        return lovedOne;
    }

    public void setLovedOne(LovedOne lovedOne) {
        this.lovedOne = lovedOne;
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

    public ElderProfile getElderProfile() {
        return elderProfile;
    }

    public void setElderProfile(ElderProfile elderProfile) {
        this.elderProfile = elderProfile;
    }

    public List<RecommendedBuddy> getRecommendedBuddies() {
        return recommendedBuddies;
    }

    public void setRecommendedBuddies(List<RecommendedBuddy> recommendedBuddies) {
        this.recommendedBuddies = recommendedBuddies;
    }

    @Override
    public String toString() {
        return "Elder{" +
                "firebaseUID='" + firebaseUID + '\'' +
                ", isBlocked=" + isBlocked +
                ", registrationMethod='" + registrationMethod + '\'' +
                ", registrationDate=" + registrationDate +
                ", onLovedOneMode=" + onLovedOneMode +
                ", lovedOne=" + lovedOne +
                ", personalData='" + personalData + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", identityCard=" + identityCard +
                ", elderProfile=" + elderProfile +
                ", elderProfile=" + recommendedBuddies +
                '}';
    }
}