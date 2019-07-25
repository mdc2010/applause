package com.applause.coding.test.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TESTER_DEVICE")
public class TesterDevice implements Serializable {
    private static final long serialVersionUID = -2198433812292829165L;
    @EmbeddedId
    private TesterDeviceId id;

}
