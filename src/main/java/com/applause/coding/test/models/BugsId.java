package com.applause.coding.test.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BugsId implements Serializable {
    private static final long serialVersionUID = 206810060468530545L;
    @Column(name = "BUG_ID")
    private int bugId;
    @Column(name = "DEVICE_ID")
    private int deviceId;
    @Column(name = "TESTER_ID")
    private int testerId;

    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = deviceId;
    }

    public int getTesterId() {
        return testerId;
    }

    public void setTesterId(int testerId) {
        this.testerId = testerId;
    }
}
