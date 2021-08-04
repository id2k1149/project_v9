package org.id2k1149.project_v9.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;

@Entity
public class Answer {
    private @Id @GeneratedValue
    Long id;

    private String title;

    @ManyToOne
    private Question question;

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", question=" + question +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return getId().equals(answer.getId())
                && getTitle().equals(answer.getTitle())
                && getQuestion().equals(answer.getQuestion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getTitle(),
                getQuestion());
    }
}
