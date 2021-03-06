package org.id2k1149.project_v9.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date = LocalDate.now();

    @ManyToOne
    private Answer answer;

    @ElementCollection
    private Map<String, BigDecimal> details;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Map<String, BigDecimal> getDetails() {
        return details;
    }

    public void setDetails(Map<String, BigDecimal> infoMap) {
        this.details = infoMap;
    }
}
