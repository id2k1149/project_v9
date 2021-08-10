package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.Diner;
import org.id2k1149.project_v9.model.Menu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(false)
public class VoterRepositoryTest {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private DinerRepository dinerRepository;

    @Autowired
    private VotesCounterRepository votesCounterRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void deleteAllVotes() {
        votesCounterRepository.deleteAll();
    }



    @Test
    public void createInfo() {
    }



    @Test
    public void findByDate() {
        LocalDate today = LocalDate.now();
        List<Menu> optionalMenu = menuRepository.findByDateOfInfo(today);
    }



    @Test
    public void getRandomInfo() {
        // today info check
        LocalDate today = LocalDate.now();
//        LocalDate today = LocalDate.now().minusDays(2);

        List<Menu> optionalMenu = menuRepository.findByDateOfInfo(today);


        if (optionalMenu.size() > 0) {
            return;
        } else {
            Random random = new Random();

            List<Diner> allDiners = dinerRepository.findAll();
            int max = allDiners.size();
            int min = 2;
            int numberOfElements = random.nextInt((max - min) + 1) + min;

            List<Diner> todayDiners = new ArrayList<>();
            for (int i = 0; i < numberOfElements; i++) {
                int randomIndex = random.nextInt(allDiners.size());
                Diner randomDiner = allDiners.get(randomIndex);
                allDiners.remove(randomIndex);
                todayDiners.add(randomDiner);
            }

            for (Diner todayDiner : todayDiners) {
                Menu newMenu = new Menu();
                newMenu.setDateOfInfo(today);
                newMenu.setDiner(todayDiner);
                menuRepository.save(newMenu);
            }
        }
    }










        /*
        Map<String, BigDecimal> descriptionMap = new HashMap<>();
        List<Dish> allDescriptions = descriptionRepository.findAll();
        List<String> items = new ArrayList<>();
        for (Dish allDescription : allDescriptions) {
            items.add(allDescription.getItem());
        }

        Random random = new Random();
        int max = 5;
        int min = 2;
        int numberOfElements = random.nextInt((max - min) + 1) + min;

        for (int i = 0; i < numberOfElements; i++) {
            int randomIndex = random.nextInt(items.size());
            String randomItem = items.get(randomIndex);
            items.remove(randomIndex);

            int maxPrice = 10000;
            int minPrice = 100;
            int r = random.nextInt((maxPrice - minPrice) + 1) + minPrice;

            BigDecimal digitalInfo = BigDecimal.valueOf(r / 100.);

            descriptionMap.put(randomItem, digitalInfo);
        }

        for (Map.Entry<String, BigDecimal> pair : descriptionMap.entrySet()) {
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }


        List<Diner> allAnswers = answerRepository.findAll();
        max = allAnswers.size();
        min = 1;
        int randomInt = random.nextInt((max - min) + 1) + min;
        Diner randomAnswer = answerRepository.findById(4L).get();

         */





//        List<String> allAnswers = new ArrayList<>();
//        for (int i = 0; i < answers.size(); i++) {
//            String answer = answers.get(i).getAnswerTitle();
//            items.add(i, answer);
//        }
//
//
//        max = allAnswers.size();
//        min = 2;
//        numberOfElements = random.nextInt((max - min) + 1) + min;
//
//        List<String> todayAnswers = new ArrayList<>();
//
//        for (int i = 0; i < numberOfElements; i++) {
//            int randomIndex = random.nextInt(allAnswers.size());
//            String randomAnswer = allAnswers.get(randomIndex);
//            allAnswers.remove(randomIndex);
//            todayAnswers.add(randomAnswer);








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


