package org.buddy.backend.models;

public class UserWithPendingSignUp {

    private String userType = "userWithPendingSignUp";

    public String getUserType() {
        return userType;
    }

    @Override
    public String toString() {
        return "UserWithPendingSignUp{" +
                "userType=" + userType +
                '}';
    }
}
