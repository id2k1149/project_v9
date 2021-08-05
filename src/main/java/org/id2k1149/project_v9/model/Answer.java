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
    private boolean isActive = true;

    @ManyToMany(mappedBy = "answers", fetch = FetchType.EAGER)
    private Set<Question> questions = new HashSet<>();

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", answerTitle='" + answerTitle + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
