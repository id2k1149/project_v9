package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class VotesCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate votesDate = LocalDate.now();

    @ManyToOne
    private Answer answer;

    private Integer votes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getVotesDate() {
        return votesDate;
    }

    public void setVotesDate(LocalDate votesDate) {
        this.votesDate = votesDate;
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




}
