package org.id2k1149.project_v9.repository;

import org.id2k1149.project_v9.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
