package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @OneToMany(mappedBy = "answer")
    private Collection<Info> info;


    public Answer() {
    }

    public Answer(Long id) {
        this.id = id;
    }

    public Answer(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long answerId) {
        this.id = answerId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Collection<Info> getInfo() {
        return info;
    }

    public void setInfo(Collection<Info> info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return title;
    }
}
