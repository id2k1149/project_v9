package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String answerTitle;

    public Answer() {
    }

    public Answer(Long id) {
        this.id = id;
    }

    public Answer(String answerTitle) {
        this.answerTitle = answerTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long answerId) {
        this.id = answerId;
    }

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }


    @Override
    public String toString() {
        return answerTitle;
    }
}
