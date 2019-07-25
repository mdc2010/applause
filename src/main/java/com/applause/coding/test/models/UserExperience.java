package com.applause.coding.test.models;

public class UserExperience {
    private int testerId;
    private int testerExperience;

    public UserExperience(int testerId, int testerExperience) {
        this.testerId = testerId;
        this.testerExperience = testerExperience;
    }

    public int getTesterId() {
        return testerId;
    }

    public int getTesterExperience() {
        return testerExperience;
    }
}
