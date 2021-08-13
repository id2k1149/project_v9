package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.repository.UserRepository;
import org.id2k1149.project_v9.repository.VoterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class UserServiceTest2 {

    private UserService testUserService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoterRepository voterRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @BeforeEach
    void setUp() {
        testUserService = new UserService(userRepository,
                voterRepository,
                bCryptPasswordEncoder);
    }

    @Test
    public void getUserAllVotes() {
//        Long id = 1L;
//        User user = testUserService.getUser(id);
//        List<Voter> voterList = voterRepository.findByUser(user);
//        UserTo userTo = new UserTo(id, user.getUsername(), voterList);
//        System.out.println(userTo);

    }
}
