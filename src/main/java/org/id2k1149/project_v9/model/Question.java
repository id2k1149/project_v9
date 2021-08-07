package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionTitle = "Where to have a lunch?";
    private LocalDate datePublished = LocalDate.now();

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Answer> answers = new HashSet<>();

    @OneToMany(mappedBy = "question")
    private Collection<VotesCounter> votesCounter;

    private String result;

    public Question() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long questionId) {
        this.id = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void addAnswer(Answer answer) {
        this.answers.add(answer);
    }

    public void removeAnswer(Answer answer) {
        this.answers.remove(answer);
    }

    public Collection<VotesCounter> getVotesCounter() {
        return votesCounter;
    }

    public void setVotesCounter(Collection<VotesCounter> votesCounter) {
        this.votesCounter = votesCounter;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", questionTitle='" + questionTitle + '\'' +
                ", datePublished=" + datePublished +
                ", result='" + result + '\'' +
                '}';
    }

}
