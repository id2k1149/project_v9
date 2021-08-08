package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String item;

    public Description() {
    }

    public Description(String item) {
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Description)) return false;
        Description that = (Description) o;
        return getId().equals(that.getId()) && getItem().equals(that.getItem());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(),
                getItem());
    }
}
