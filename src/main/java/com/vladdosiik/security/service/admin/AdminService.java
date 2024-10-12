package com.vladdosiik.security.service.admin;

import com.vladdosiik.security.model.User;
import com.vladdosiik.security.model.dto.NewUserForAdmin;

public interface AdminService {

    void createUser(NewUserForAdmin user);

    void setRoleToUser(String name, String role);

    void deleteUser(String name);
}
