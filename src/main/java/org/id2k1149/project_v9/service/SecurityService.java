package org.id2k1149.project_v9.service;

public interface SecurityService {
    String findLoggedInUsername();

    void autoLogin(String username, String password);
}
