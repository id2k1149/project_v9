package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import com.github.javafaker.Faker;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(false)
public class DescriptionRepositoryTest {

    private final Faker faker = new Faker();

    @Autowired
    private DescriptionRepository descriptionRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createDescriptions() {
        for (int i = 0; i < 25; i++) {
            Description description = new Description(faker.food().dish());
            entityManager.persist(description);
        }
    }

    @Test
    public void makeMap() {
        Map<Description, BigDecimal> descriptionMap = new HashMap<>();
        List<Description> allDescription = descriptionRepository.findAll();

        Random random = new Random();
        int max = 4;
        int min = 2;
        int numberOfElements = random.nextInt((max - min) + 1) + min;

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = random.nextInt(allDescription.size());
            Description randomDescription = allDescription.get(randomIndex);
            allDescription.remove(randomIndex);

//            int maxPrice = 10000;
//            int minPrice = 100;
//            int r = random.nextInt((maxPrice - minPrice) + 1) + minPrice;
//            BigDecimal digitalInfo = BigDecimal.valueOf(r / 100.);

            BigDecimal digitalInfo = BigDecimal.valueOf(Double.valueOf(faker.commerce().price(10, 100)));
            descriptionMap.put(randomDescription, digitalInfo);
        }

        for (Map.Entry<Description, BigDecimal> pair : descriptionMap.entrySet()) {
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }

    }

//    Random random = new Random();
//    int leftLimit = 97; // letter 'a'
//    int rightLimit = 122; // letter 'z'
//    int targetStringLength = 6;
//
//
//    String testName = random.ints(leftLimit, rightLimit + 1)
//            .limit(targetStringLength)
//            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
//            .toString();

//
//    @Test
//    public void createNewQuestionWithOneAnswer() {
//        Answer answerDiner1 = entityManager.find(Answer.class, 3L);
//        Question newQuestion = new Question();
//        newQuestion.addAnswer(answerDiner1);
//
//        questionRepository.save(newQuestion);
//    }
//
//    @Test
//    public void createNewQuestionWithTwoAnswers() {
//        Answer answerDiner1 = entityManager.find(Answer.class, 3L);
//        Answer answerDiner2 = entityManager.find(Answer.class, 1L);
//        Question newQuestion = new Question();
//        newQuestion.addAnswer(answerDiner1);
//        newQuestion.addAnswer(answerDiner2);
//
//        questionRepository.save(newQuestion);
//    }
//
//    @Test
//    public void addAnswerToQuestion() {
//        Question question = questionRepository.findById(4L).get();
//        Answer answerDiner3 = entityManager.find(Answer.class, 1L);
//        question.addAnswer(answerDiner3);
//    }
//
//    @Test
//    public void removeAnswerFromQuestion() {
//        Question question = questionRepository.findById(6L).get();
//        Answer answer = answerRepository.getById(3L);
//        question.removeAnswer(answer);
//    }
//
//    @Test
//    public void createNewQuestionWithNewAnswer() {
//        Answer answer = new Answer("Diner #4");
//        Question question = new Question();
//        question.addAnswer(answer);
//        questionRepository.save(question);
//    }
//
//    @Test
//    public void getQuestion() {
//        Question question = questionRepository.findById(4L).get();
//        System.out.println(question.getAnswers());
//    }
//
//    @Test
//    public void deleteQuestion() {
//        questionRepository.deleteById(9L);
//    }

}
