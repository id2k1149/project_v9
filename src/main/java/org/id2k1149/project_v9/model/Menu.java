package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

@Entity
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dateOfInfo;
    @ManyToOne
    private Diner diner;

    @ElementCollection
    @CollectionTable(name = "menu_dish_mapping",
                    joinColumns = {@JoinColumn(name = "menu_id",
                    referencedColumnName = "id")})
    @MapKeyColumn(name = "dish_item")
    private Map<String, BigDecimal> dinerMenu;

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

    public Diner getDiner() {
        return diner;
    }

    public void setDiner(Diner diner) {
        this.diner = diner;
    }

    public Map<String, BigDecimal> getDinerMenu() {
        return dinerMenu;
    }

    public void setDinerMenu(Map<String, BigDecimal> dinerMenu) {
        this.dinerMenu = dinerMenu;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", dateOfInfo=" + dateOfInfo +
                ", diner=" + diner +
                ", infoMap=" + dinerMenu +
                '}';
    }


}
