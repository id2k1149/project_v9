package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

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

    @Override
    public String toString() {
        return title;
    }
}
