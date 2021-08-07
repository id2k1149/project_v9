package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Entity
public class Info {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate dateOfInfo = LocalDate.now();
    @ManyToOne
    private Answer answer;

    @ElementCollection
    @CollectionTable(name = "info_description_mapping",
                    joinColumns = {@JoinColumn(name = "info_id",
                    referencedColumnName = "id")})
    @MapKeyColumn(name = "description_item")
    private Map<String, BigDecimal> infoMap;

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

    public Map<String, BigDecimal> getInfoMap() {
        return infoMap;
    }

    public void setInfoMap(Map<String, BigDecimal> infoMap) {
        this.infoMap = infoMap;
    }
}
