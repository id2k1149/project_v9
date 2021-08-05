package org.id2k1149.project_v9.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 25, nullable = false, unique = true)
    private String username;

    @Column(length = 10, nullable = false)
    private String password;

    @Transient
    private String passwordConfirm;

    @Enumerated(EnumType.STRING)
    private Role role;

    public Long getUId() {
        return id;
    }

    public void setUId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getUId().equals(
                user.getUId())
                && getUsername().equals(user.getUsername())
                && getPassword().equals(user.getPassword())
                && Objects.equals(getPasswordConfirm(),
                user.getPasswordConfirm())
                && getRole() == user.getRole();
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getUId(),
                getUsername(),
                getPassword(),
                getPasswordConfirm(),
                getRole());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
