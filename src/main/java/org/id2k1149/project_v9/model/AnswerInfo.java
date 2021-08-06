package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class AnswerInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dateOfInfo = LocalDate.now();
    @ManyToOne
    private Answer answer;
    @ManyToOne
    private Description description;
    private BigDecimal digital;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateOfInfo() {
        return dateOfInfo;
    }

    public void setDateOfInfo(LocalDate dateOfInfo) {
        this.dateOfInfo = dateOfInfo;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public BigDecimal getDigital() {
        return digital;
    }

    public void setDigital(BigDecimal digital) {
        this.digital = digital;
    }
}
