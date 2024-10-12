package com.vladdosiik.security.service.admin;

import com.vladdosiik.security.model.dto.UserPostAdminDto;

public interface AdminService {

    void createUser(UserPostAdminDto user);

    void setRoleToUser(String name, String role);

    void deleteUser(String name);
}
