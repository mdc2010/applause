package com.applause.coding.test.models;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "TESTERS")
public class Testers implements Serializable {
    private static final long serialVersionUID = 3616466268938834208L;
    @Id
    @Column(name = "TESTER_ID")
    private int id;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "COUNTRY")
    private String country;
    @Column(name = "LAST_LOGIN")
    private Timestamp lastLogin;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "TESTER_DEVICE",
            joinColumns = @JoinColumn(name = "tester_id"),
            inverseJoinColumns = @JoinColumn(name = "device_id")
    )
    private List<Devices> devices;

    @OneToMany(mappedBy = "testers", cascade = CascadeType.ALL)
    private List<Bugs> bugs;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<Bugs> getBugs() {
        return bugs;
    }

    public void setBugs(List<Bugs> bugs) {
        this.bugs = bugs;
    }

    public List<Devices> getDevices() {
        return devices;
    }
}
