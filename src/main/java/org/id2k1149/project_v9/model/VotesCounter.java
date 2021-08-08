package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class VotesCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Question question;

    @ManyToOne
    private Answer answer;

    private Integer votes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VotesCounter)) return false;
        VotesCounter that = (VotesCounter) o;
        return getId().equals(that.getId())
                && getQuestion().equals(that.getQuestion())
                && getAnswer().equals(that.getAnswer())
                && getVotes().equals(that.getVotes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getQuestion(),
                getAnswer(),
                getVotes());
    }
}
