package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Question;
import org.id2k1149.project_v9.model.Voter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(false)
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createAnswers() {
        for (int i = 0; i < 5; i++) {
            Answer diner = new Answer("Diner #" + (i + 1));
            entityManager.persist(diner);
        }
    }

    @Test
    public void deleteAllAnswers() {
        answerRepository.deleteAll();
    }

    @Test
    public void createNewQuestionWithOneAnswer() {
        Answer answerDiner1 = entityManager.find(Answer.class, 3L);
        Question newQuestion = new Question();
        newQuestion.addAnswer(answerDiner1);

        questionRepository.save(newQuestion);
    }

    @Test
    public void createNewQuestionWithTwoAnswers() {
        Answer answerDiner1 = entityManager.find(Answer.class, 189L);
        Answer answerDiner2 = entityManager.find(Answer.class, 190L);
        Question newQuestion = new Question();
        newQuestion.addAnswer(answerDiner1);
        newQuestion.addAnswer(answerDiner2);
        newQuestion.setActive(true);
        newQuestion.setDatePublished(LocalDate.now().minusDays(1));

        questionRepository.save(newQuestion);
    }

    @Test
    public void addAnswerToQuestion() {
        Question question = questionRepository.findById(4L).get();
        Answer answerDiner3 = entityManager.find(Answer.class, 1L);
        question.addAnswer(answerDiner3);
    }

    @Test
    public void removeAnswerFromQuestion() {
        Question question = questionRepository.findById(6L).get();
        Answer answer = answerRepository.getById(3L);
        question.removeAnswer(answer);
    }

    @Test
    public void createNewQuestionWithNewAnswer() {
        Answer answer = new Answer("Diner #4");
        Question question = new Question();
        question.addAnswer(answer);
        questionRepository.save(question);
    }

    @Test
    public void getQuestion() {
        Question question = questionRepository.findById(111L).get();
        System.out.println(question.getAnswers());
    }

    @Test
    public void deleteQuestion() {
        questionRepository.deleteById(210L);
    }

    @Test
    public void deleteAll() {
        questionRepository.deleteAll();
    }

    @Test
    public void removeQuestion() {
        Question question = questionRepository.findById(210L).get();
        Answer answer = answerRepository.findById(190L).get();
        answer.getQuestions().remove(question);
        question.getAnswers().remove(this);

    }

    @Test
    public void remove() {
        Question question = questionRepository.findById(210L).get();
        question.getAnswers().removeAll(question.getAnswers());



        // Other business logic

        questionRepository.delete(question);
    }

    /*
    @Transactional
    public void remove(Integer groupId) {
        Group group = groupRepository.findOne(groupId);
        group.getUsers().removeAll(group.getUsers());

        // Other business logic

        groupRepository.delete(group);
    }

     */



}
