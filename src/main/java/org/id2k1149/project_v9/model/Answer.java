package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String answerTitle;

    @ManyToMany(mappedBy = "answers", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
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

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return answerTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return getId().equals(answer.getId())
                && getAnswerTitle().equals(answer.getAnswerTitle())
                && Objects.equals(getQuestions(),
                answer.getQuestions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getAnswerTitle(),
                getQuestions());
    }
}
