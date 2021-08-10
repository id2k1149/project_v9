package org.id2k1149.project_v9.model;

import javax.persistence.*;

@Entity
public class Diner {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String dinerName;

    public Diner() {
    }

    public Diner(String dinerName) {
        this.dinerName = dinerName;
    }

    public Diner(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long answerId) {
        this.id = answerId;
    }

    public String getDinerName() {
        return dinerName;
    }

    public void setDinerName(String answerTitle) {
        this.dinerName = answerTitle;
    }

    @Override
    public String toString() {
        return dinerName;
    }

}
