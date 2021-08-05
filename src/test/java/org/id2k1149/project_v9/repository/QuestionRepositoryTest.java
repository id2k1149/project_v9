package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Question;
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
    private QuestionRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createAnswers() {
        Answer diner_1 = new Answer("Diner #1");
        Answer diner_2 = new Answer("Diner #2");
        Answer diner_3 = new Answer("Diner #3");

        entityManager.persist(diner_1);
        entityManager.persist(diner_2);
        entityManager.persist(diner_3);
    }

    @Test
    public void createNewQuestionWithOneAnswer() {
        Answer answerDiner1 = entityManager.find(Answer.class, 3L);
        Question newQuestion = new Question();
        newQuestion.addAnswer(answerDiner1);

        repo.save(newQuestion);
    }

    @Test
    public void createNewQuestionWithTwoAnswers() {
        Answer answerDiner1 = entityManager.find(Answer.class, 3L);
        Answer answerDiner2 = entityManager.find(Answer.class, 4L);
        Question newQuestion = new Question();
        newQuestion.addAnswer(answerDiner1);
        newQuestion.addAnswer(answerDiner2);

        repo.save(newQuestion);
    }

}
