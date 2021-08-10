package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.Dish;
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

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(false)
public class DishRepositoryTest {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void createDescriptions() {
        for (int i = 0; i < 25; i++) {
            String food = "Food #" + (i + 1);
            Dish dish = new Dish(food);
            entityManager.persist(dish);
        }
    }


    @Test
    public void makeMap() {
        Map<Dish, BigDecimal> descriptionMap = new HashMap<>();
        List<Dish> allDish = dishRepository.findAll();

        Random random = new Random();
        int max = 5;
        int min = 2;
        int numberOfElements = random.nextInt((max - min) + 1) + min;

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = random.nextInt(allDish.size());
            Dish randomDish = allDish.get(randomIndex);
            allDish.remove(randomIndex);

            int maxPrice = 100;
            int minPrice = 1;
            int r = random.nextInt((maxPrice - minPrice) + 1) + minPrice;

            BigDecimal digitalInfo = BigDecimal.valueOf(r / 1.);

            descriptionMap.put(randomDish, digitalInfo);
        }

        for (Map.Entry<Dish, BigDecimal> pair : descriptionMap.entrySet()) {
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
//        Diner answerDiner1 = entityManager.find(Diner.class, 3L);
//        Question newQuestion = new Question();
//        newQuestion.addAnswer(answerDiner1);
//
//        questionRepository.save(newQuestion);
//    }
//
//    @Test
//    public void createNewQuestionWithTwoAnswers() {
//        Diner answerDiner1 = entityManager.find(Diner.class, 3L);
//        Diner answerDiner2 = entityManager.find(Diner.class, 1L);
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
//        Diner answerDiner3 = entityManager.find(Diner.class, 1L);
//        question.addAnswer(answerDiner3);
//    }
//
//    @Test
//    public void removeAnswerFromQuestion() {
//        Question question = questionRepository.findById(6L).get();
//        Diner answer = answerRepository.getById(3L);
//        question.removeAnswer(answer);
//    }
//
//    @Test
//    public void createNewQuestionWithNewAnswer() {
//        Diner answer = new Diner("Diner #4");
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
