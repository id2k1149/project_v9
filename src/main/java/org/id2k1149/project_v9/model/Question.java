package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String questionTitle;
    private LocalDate datePublished;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Answer> answers;
    private String result;

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
}
