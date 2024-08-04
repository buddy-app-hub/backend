package org.buddy.backend.models;

public class BankAccount {
    private String bankAccountNumber;
    private String bankName;

    // Getters and setters

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "bankAccountNumber='" + bankAccountNumber + '\'' +
                ", bankName='" + bankName + '\'' +
                '}';
    }
}