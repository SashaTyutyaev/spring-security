package com.vladdosiik.security.dao;

import com.vladdosiik.security.model.User;

public interface UserDao {
    User getUserByName(String name);
}
