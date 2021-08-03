package org.id2k1149.project_v9.integration;

import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.repository.UserRepository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository testRepository;

    private final Faker faker = new Faker();

    @Test
    void canRegisterNewUser() throws Exception {
        // given
        String testName = faker.name().username();
        String testPassword = faker.internet().password();

        User testUser = new User(testName, testPassword);

        // when
        ResultActions resultActions = mockMvc
                .perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testUser)));
        // then
        resultActions.andExpect(status().isOk());
        List<User> users = testRepository.findAll();
        assertThat(users)
                .usingElementComparatorIgnoringFields("id")
                .contains(testUser);
    }

    @Test
    void canDeleteUser() throws Exception {
        // given
        String testName = faker.name().username();
        String testPassword = faker.internet().password();

        User testUser = new User(testName, testPassword);

        mockMvc.perform(post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testUser)))
                .andExpect(status().isOk());

        MvcResult getStudentsResult = mockMvc.perform(get("/api/v1/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = getStudentsResult
                .getResponse()
                .getContentAsString();

        List<User> users = objectMapper.readValue(
                contentAsString,
                new TypeReference<>() {
                }
        );

        long id = users.stream()
                .filter(u -> u.getUsername().equals(testUser.getUsername()))
                .map(User::getId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "User with name " + testName + " not found"));

        // when
        ResultActions resultActions = mockMvc
                .perform(delete("/api/v1/users/" + id));

        // then
        resultActions.andExpect(status().isOk());
        boolean exists = testRepository.existsById(id);
        assertThat(exists).isFalse();
    }
}
