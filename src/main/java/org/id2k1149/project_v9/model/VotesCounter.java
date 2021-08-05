package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class VotesCounter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Answer answer;

    @ManyToOne
    private Question question;

    private Integer votes;

}
