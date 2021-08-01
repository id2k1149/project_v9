package org.id2k1149.project_v9.service;

import org.id2k1149.project_v9.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
