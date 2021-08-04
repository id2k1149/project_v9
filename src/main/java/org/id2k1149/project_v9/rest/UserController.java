package org.id2k1149.project_v9.rest;

import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{userId}")
    public User getUser(@PathVariable("userId") Long userId) {
        return userService.getUser(userId);
    }

    @PostMapping
    public void addUser(@RequestBody User newUser) {
        userService.addUser(newUser);
    }

    @PutMapping(path = "{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(
            @RequestBody User user,
            @PathVariable("userId") Long userId
    ) {
        userService.updateUser(user, userId);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }

}
