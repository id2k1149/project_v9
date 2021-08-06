package org.id2k1149.project_v9.model;

import javax.persistence.*;


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
        return "Description{" +
                "id=" + id +
                ", item='" + item + '\'' +
                '}';
    }
}
