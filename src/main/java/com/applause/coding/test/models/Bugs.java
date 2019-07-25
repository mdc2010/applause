package com.applause.coding.test.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BUGS")
public class Bugs implements Serializable {
    private static final long serialVersionUID = -5249363760753342204L;
    @EmbeddedId
    private BugsId id;

    @ManyToOne()
    @JoinColumn(name = "TESTER_ID", referencedColumnName = "TESTER_ID", insertable = false, updatable = false)
    private Testers testers;

    public BugsId getId() {
        return id;
    }

    public void setId(BugsId id) {
        this.id = id;
    }

    public Testers getTesters() {
        return testers;
    }

    public void setTesters(Testers testers) {
        this.testers = testers;
    }
}
