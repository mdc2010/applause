package com.applause.coding.test.models;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class TesterDeviceId implements Serializable {
    private static final long serialVersionUID = 2095513621449949716L;
    @Column(name = "TESTER_ID")
    private int testerId;
    @Column(name = "DEVICE_ID")
    private int deviceId;
}
