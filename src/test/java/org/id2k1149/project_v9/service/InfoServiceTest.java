package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.repository.AnswerRepository;
import org.id2k1149.project_v9.repository.InfoRepository;
import org.id2k1149.project_v9.to.AnswerTo;
import org.id2k1149.project_v9.util.AnswerUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.id2k1149.project_v9.exception.BadRequestException;
import org.id2k1149.project_v9.exception.NotFoundException;
import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.repository.UserRepository;
import org.id2k1149.project_v9.repository.VoterRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class InfoServiceTest {

    private InfoService testInfoService;

    @Autowired
    private InfoRepository infoRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @BeforeEach
    void setUp() {
        testInfoService = new InfoService(infoRepository, answerRepository);
    }



    @Test
    public void checkTime() {
        List<AnswerTo> answerToList = new ArrayList<>();
        if (LocalTime.now().getHour() < 23)
            answerToList = AnswerUtil.getAnswersTo(testInfoService.getTodayAnswersInfo(),
                    testInfoService.getByDate(LocalDate.now()));

        for (int i = 0; i < answerToList.size(); i++) {
            System.out.println(answerToList.get(i).getTitle());
            System.out.println(answerToList.get(i).getInfoTo());
            for (int j = 0; j < answerToList.get(i).getInfoTo().size(); j++) {
                System.out.println("j = " + j);
                System.out.println(answerToList.get(i).getInfoTo().get(j));
                System.out.println(answerToList.get(i).getInfoTo().get(j).getClass());
                System.out.println(answerToList.get(i).getInfoTo().get(j).getDetails());
                System.out.println(answerToList.get(i).getInfoTo().get(j).getDetails().getClass());
                System.out.println("------------");
                for (Map.Entry entry: answerToList.get(i).getInfoTo().get(j).getDetails().entrySet()) {
                    System.out.println(entry.toString());
                }
                System.out.println("------------");
                answerToList.get(i).getInfoTo().get(j).getDetails().forEach((key, value) -> System.out.println(key + " : " + value));
                System.out.println("------------");


            }


        }

    }
}
