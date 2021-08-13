package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.Answer;
import org.id2k1149.project_v9.model.Info;
import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.model.Voter;
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
    private InfoRepository infoRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private CounterRepository counterRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void deleteAllVotes() {
        counterRepository.deleteAll();
    }


    @Test
    public void createInfo() {
    }


    @Test
    public void findByUser() {
        Long id = 1L;
        User user = userRepository.getById(id);
        List<Voter> voterList = voterRepository.getByUser(user);
        System.out.println(voterList);
        for (int i = 0; i < voterList.size() ; i++) {
            System.out.println(voterList.get(i).toString());
        }
    }

}