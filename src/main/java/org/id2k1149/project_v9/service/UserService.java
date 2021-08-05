package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.exception.BadRequestException;
import org.id2k1149.project_v9.exception.NotFoundException;
import org.id2k1149.project_v9.model.Role;
import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return userRepository.findUserById(id);
        } else {
            throw new IllegalStateException(
                    "user with id " + id + " does not exist");
        }
    }

    public void addUser(User newUser) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByUsername(newUser.getUsername()));

//        Boolean usernameExists = userRepository.usernameExists(newUser.getUsername());

        if (optionalUser.isPresent()) {
            throw new BadRequestException("The name " + newUser.getUsername() + " is already used");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        newUser.setRole(Role.USER);

        userRepository.save(newUser);
    }

    @Transactional
    public void updateUser(User user,
                           Long id
    ) {
        User userToUpdate = userRepository.findUserById(id);

        if (userToUpdate == null) {
            throw new IllegalStateException(
                    "user with id " + id + " does not exist");
        }

        String newName = user.getUsername();
        if (newName != null && newName.length() > 0 && !Objects.equals(userToUpdate.getUsername(), newName)) {
            userToUpdate.setUsername(newName);
        }

        String newPassword = user.getPassword();
        if (newPassword != null && newName.length() > 7 && !Objects.equals(userToUpdate.getPassword(), newPassword)) {
            String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
            userToUpdate.setPassword(encodedPassword);
        }

        userRepository.save(userToUpdate);
    }

    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)) {
            throw new NotFoundException(
                    "User with id " + id + " does not exists");
        }
        userRepository.deleteById(id);
    }
}
