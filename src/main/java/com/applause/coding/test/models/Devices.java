package com.applause.coding.test.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "DEVICES")
public class Devices implements Serializable {
    private static final long serialVersionUID = 3938914427174848130L;
    @Id
    @Column(name = "DEVICE_ID")
    private int id;
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany(mappedBy = "devices")
    private List<Testers> testers;

    public void setTesters(List<Testers> testers) {
        this.testers = testers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Testers> getTesters() {
        return testers;
    }
}
