package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.Answer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.github.javafaker.Faker;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(false)
public class AnswerRepositoryTest {

    private final Faker faker = new Faker();

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createAnswers() {
        for (int i = 0; i < 3; i++) {
            Answer answer = new Answer(faker.beer().name());
            entityManager.persist(answer);
        }
    }

    @Test
    public void deleteAllAnswers() {
        answerRepository.deleteAll();
    }















}
