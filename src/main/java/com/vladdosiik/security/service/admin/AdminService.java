package com.vladdosiik.security.service.admin;

import com.vladdosiik.security.model.User;

public interface AdminService {

    void createUser(User user);

    void setRoleToUser(String name, String role);

    void deleteUser(String name);
}
