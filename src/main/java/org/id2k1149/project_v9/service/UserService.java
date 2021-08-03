package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.exception.BadRequestException;
import org.id2k1149.project_v9.exception.UserNotFoundException;
import org.id2k1149.project_v9.model.Role;
import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final static String NOT_FOUND = "user with name %s is not found";

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userId) {
        User user = userRepository.findUserById(userId);
        if (user == null) {
            throw new IllegalStateException(
                    "user with id " + userId + " does not exist");
        } else return user;
    }

    public void addUser(User newUser) {
        Boolean usernameExists = userRepository.usernameExists(newUser.getUsername());

        if (usernameExists) {
            throw new BadRequestException("The name " + newUser.getUsername() + " is already used");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        newUser.setRole(Role.USER);

        userRepository.save(newUser);
    }

    @Transactional
    public User updateUser(Long userId,
                           String username,
                           String password
    ) {
        User userToUpdate = userRepository.findUserById(userId);

        System.out.println(userToUpdate);
        System.out.println(username + " " + password);

        if (userToUpdate == null) {
            throw new IllegalStateException(
                    "appUser with id " + userId + " does not exist");
        }

//        if (username != null && username.length() > 0 && !Objects.equals(userToUpdate.getUsername(), username)) {
//            userToUpdate.setUsername(username);
//        }
//
//        if (password != null && password.length() > 0 && !Objects.equals(userToUpdate.getPassword(), password)) {
//            String encodedPassword = bCryptPasswordEncoder.encode(password);
//            userToUpdate.setPassword(encodedPassword);
//        }

        userToUpdate.setUsername(username);

        String encodedPassword = bCryptPasswordEncoder.encode(password);
        userToUpdate.setPassword(encodedPassword);

        System.out.println(userToUpdate);

        userRepository.save(userToUpdate);

        return userToUpdate;
    }

    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)) {
            throw new UserNotFoundException(
                    "User with id " + userId + " does not exists");
        }
        userRepository.deleteById(userId);
    }
}
