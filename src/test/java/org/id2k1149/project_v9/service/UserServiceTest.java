package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.exception.BadRequestException;
import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private UserRepository testRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserService testUserService;

    @BeforeEach
    void setUp() {
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        testUserService = new UserService(testRepository, bCryptPasswordEncoder);
    }

    @AfterEach
    void tearDown() {
        testRepository.deleteAll();
    }

    @Test
    void findByUsername() {
    }

    @Test
    void getUsers() {
        //when
        testUserService.getUsers();
        //then
        verify(testRepository).findAll();
    }

    @Test
    void getUser() {
        //given
        User testUser1 = new User(
                "tester1",
                "test_password");
        testUserService.addUser(testUser1);

        User testUser2 = new User(
                "tester2",
                "test_password");
        testUserService.addUser(testUser2);

        //when
        User user = testUserService.getUser(testUser1.getId());

        //then
        assertThat(user).isEqualTo(testUser1);
    }

    @Test
    void addUser() {
        //given
        User testUser = new User(
                "tester",
                "test_password");

        //when
        testUserService.addUser(testUser);

        //then
        ArgumentCaptor<User> testUserArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(testRepository).save(testUserArgumentCaptor.capture());
        User captorUser = testUserArgumentCaptor.getValue();
        assertThat(captorUser).isEqualTo(testUser);
    }

    @Test
    void nameIsTaken() {
        //given
        User testUser = new User(
                "tester",
                "test_password");

        given(testRepository.usernameExists(anyString()))
                .willReturn(true);

        //when
        //then
        assertThatThrownBy(() -> testUserService.addUser(testUser))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("The name " + testUser.getUsername() + " is already used");

        verify(testRepository, never()).save(any());

    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }
}