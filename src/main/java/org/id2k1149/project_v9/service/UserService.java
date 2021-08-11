package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.util.exception.BadRequestException;
import org.id2k1149.project_v9.util.exception.NotFoundException;
import org.id2k1149.project_v9.model.Role;
import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.repository.UserRepository;
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
        if (userRepository.findById(id).isPresent()) {
            return userRepository.getById(id);
        } else {
            throw new NotFoundException(id + " does not exist");
        }
    }

    public void addUser(User newUser) {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByUsername(newUser.getUsername()));

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
        User userToUpdate;
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new NotFoundException(
                    "user with id " + id + " does not exist");
        } else {
            userToUpdate = optionalUser.get();
        }

        String newName = user.getUsername();
        if (newName != null && newName.length() > 0 && !Objects.equals(userToUpdate.getUsername(), newName)) {
            userToUpdate.setUsername(newName);
        }

        String newPassword = user.getPassword();
        if (newPassword != null && newPassword.length() > 5) {
            String encodedPassword = bCryptPasswordEncoder.encode(newPassword);
            userToUpdate.setPassword(encodedPassword);
        }

        Role newRole = user.getRole();
        if (newRole != null) {
            userToUpdate.setRole(newRole);
        }

        userRepository.save(userToUpdate);
    }

    public void deleteUser(Long id) {
        if(userRepository.findById(id).isEmpty()) {
            throw new NotFoundException(id + " does not exists");
        }
        userRepository.deleteById(id);
    }
}
