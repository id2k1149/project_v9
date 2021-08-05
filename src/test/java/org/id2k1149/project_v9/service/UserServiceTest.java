package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.exception.BadRequestException;
import org.id2k1149.project_v9.exception.NotFoundException;
import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.repository.UserRepository;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import com.github.javafaker.Faker;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private final Faker faker = new Faker();

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
        //given
        User testUser1 = new User();
        String testName1 = faker.name().username();
        testUser1.setUsername(testName1);
        String testPassword1 = faker.internet().password();
        testUser1.setPassword(testPassword1);
        testUserService.addUser(testUser1);

        User testUser2 = new User();
        String testName2 = faker.name().username();
        testUser2.setUsername(testName2);
        String testPassword2 = faker.internet().password();
        testUser2.setPassword(testPassword2);
        testUserService.addUser(testUser2);

        //when
        testUserService.getUsers();

        //then
        verify(testRepository).findAll();

    }

    @Test
    void getUser() {
        //given
        User testUser1 = new User();
        String testName1 = faker.name().username();
        testUser1.setUsername(testName1);
        String testPassword1 = faker.internet().password();
        testUser1.setPassword(testPassword1);
        testUser1.setId(1L);
        testUserService.addUser(testUser1);
        System.out.println(testUser1);

        User testUser2 = new User();
        String testName2 = faker.name().username();
        testUser2.setUsername(testName2);
        String testPassword2 = faker.internet().password();
        testUser2.setPassword(testPassword2);
        testUser2.setId(2L);
        testUserService.addUser(testUser2);
        System.out.println(testUser2);

        System.out.println(testUserService.getUser(testUser2.getId()));

        //when
//        User user = testUserService.getUser(testUser2.getId());

        //then
//        assertThat(testUserService.getUser(testUser2.getId())).isEqualTo(testUser1);
    }

    @Test
    void addUser() {
        //given
        User testUser1 = new User();
        String testName1 = faker.name().username();
        testUser1.setUsername(testName1);
        String testPassword1 = faker.internet().password();
        testUser1.setPassword(testPassword1);

        //when
        testUserService.addUser(testUser1);

        //then
        ArgumentCaptor<User> testUserArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(testRepository).save(testUserArgumentCaptor.capture());
        User captorUser = testUserArgumentCaptor.getValue();
        assertThat(captorUser).isEqualTo(testUser1);
    }

    @Test
    void nameIsTaken() {
        //given
        User testUser1 = new User();
        String testName1 = faker.name().username();
        testUser1.setUsername(testName1);
        String testPassword1 = faker.internet().password();
        testUser1.setPassword(testPassword1);
        testUser1.setId(1L);
        testUserService.addUser(testUser1);


        User newUser = new User();
        newUser.setUsername(testName1);
        String testPassword2 = faker.internet().password();
        newUser.setPassword(testPassword2);
        newUser.setId(2L);
        testUserService.addUser(newUser);

        given(testRepository.usernameExists(anyString()))
                .willReturn(true);

        //when
        //then
        assertThatThrownBy(() -> testUserService.addUser(newUser))
                .isInstanceOf(BadRequestException.class);

        verify(testRepository, never()).save(newUser);

    }

    @Test
    void updateUser() {

    }

    @Test
    void deleteUser() {
        // given
        long id = 10;
        given(testRepository.existsById(id))
                .willReturn(true);
        // when
        testUserService.deleteUser(id);

        // then
        verify(testRepository).deleteById(id);
    }

    @Test
    void cantDeleteIfStudentNotFound() {
        // given
        long id = 10;
        given(testRepository.existsById(id))
                .willReturn(false);
        // when
        // then
        assertThatThrownBy(() -> testUserService.deleteUser(id))
                .isInstanceOf(NotFoundException.class)
                .hasMessageContaining("User with id " + id + " does not exists");

        verify(testRepository, never()).deleteById(any());
    }
}