package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.Answer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(false)
public class QuestionRepositoryTest {

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createAnswers() {
        for (int i = 0; i < 3; i++) {
            Answer diner = new Answer("Diner #" + (i + 1));
            entityManager.persist(diner);
        }
    }

    @Test
    public void deleteAllAnswers() {
        answerRepository.deleteAll();
    }















}
