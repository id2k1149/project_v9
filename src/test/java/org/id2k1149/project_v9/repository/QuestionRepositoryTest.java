package org.id2k1149.project_v9.repository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;


@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(false)
public class QuestionRepositoryTest {
    /*

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createAnswers() {
        for (int i = 0; i < 5; i++) {
            Diner diner = new Diner("Diner #" + (i + 1));
            entityManager.persist(diner);
        }
    }

    @Test
    public void deleteAllAnswers() {
        answerRepository.deleteAll();
    }

    @Test
    public void createNewQuestionWithOneAnswer() {
        Diner dinerDiner1 = entityManager.find(Diner.class, 3L);
        Question newQuestion = new Question();
        newQuestion.addAnswer(dinerDiner1);

        questionRepository.save(newQuestion);
    }

    @Test
    public void createNewQuestionWithTwoAnswers() {
        Diner dinerDiner1 = entityManager.find(Diner.class, 189L);
        Diner dinerDiner2 = entityManager.find(Diner.class, 190L);
        Question newQuestion = new Question();
        newQuestion.addAnswer(dinerDiner1);
        newQuestion.addAnswer(dinerDiner2);
        newQuestion.setActive(true);
        newQuestion.setDatePublished(LocalDate.now().minusDays(1));

        questionRepository.save(newQuestion);
    }

    @Test
    public void addAnswerToQuestion() {
        Question question = questionRepository.findById(4L).get();
        Diner dinerDiner3 = entityManager.find(Diner.class, 1L);
        question.addAnswer(dinerDiner3);
    }

    @Test
    public void removeAnswerFromQuestion() {
        Question question = questionRepository.findById(6L).get();
        Diner diner = answerRepository.getById(3L);
        question.removeAnswer(diner);
    }

    @Test
    public void createNewQuestionWithNewAnswer() {
        Diner diner = new Diner("Diner #4");
        Question question = new Question();
        question.addAnswer(diner);
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
        Diner diner = answerRepository.findById(190L).get();
        diner.getQuestions().remove(question);
        question.getAnswers().remove(this);

    }

    @Test
    public void remove() {
        Question question = questionRepository.findById(210L).get();
        question.getAnswers().removeAll(question.getAnswers());



        // Other business logic

        questionRepository.delete(question);
    }

 */

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
