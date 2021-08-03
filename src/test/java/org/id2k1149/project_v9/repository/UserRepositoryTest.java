package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository testRepository;

    @AfterEach
    void tearDown() {
        testRepository.deleteAll();
    }

    @Test
    void usernameExists() {
        //given
        User testUser = new User(
                "tester",
                "test_password");
        testRepository.save(testUser);

        //when
        boolean expected = testRepository.usernameExists(testUser.getUsername());

        //then
        assertThat(expected).isTrue();
    }

    @Test
    void usernameDoesntExist() {
        //given
        User testUser = new User(
                "tester",
                "test_password");
        testRepository.save(testUser);

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 6;
        Random random = new Random();

        String testName = random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        //when
        boolean expected = testRepository.usernameExists(testName);

        //then
        assertThat(expected).isFalse();
    }
}