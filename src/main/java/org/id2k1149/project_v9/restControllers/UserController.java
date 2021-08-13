package org.id2k1149.project_v9.restControllers;

import org.id2k1149.project_v9.model.User;
import org.id2k1149.project_v9.service.UserService;
import org.id2k1149.project_v9.to.UserTo;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping(path = "{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    public void addUser(@RequestBody User newUser) {
        userService.addUser(newUser);
    }

    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateUser(
            @RequestBody User user,
            @PathVariable("id") Long id
    ) {
        userService.updateUser(user, id);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @GetMapping(path = "{id}/votes")
    public UserTo getUserVotes(@PathVariable("id") Long id) {
        return userService.getUserVotes(id);
    }

}