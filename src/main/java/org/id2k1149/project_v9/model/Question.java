package org.id2k1149.project_v9.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
public class Question {
    private @Id @GeneratedValue
    Long id;

    private String title;
    private Date datePublished;
    private String result;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", datePublished=" + datePublished +
                ", result='" + result + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Question)) return false;
        Question question = (Question) o;
        return getId().equals(
                question.getId())
                && getTitle().equals(question.getTitle())
                && getDatePublished().equals(question.getDatePublished())
                && Objects.equals(getResult(),
                question.getResult());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                getTitle(),
                getDatePublished(),
                getResult());
    }
}
