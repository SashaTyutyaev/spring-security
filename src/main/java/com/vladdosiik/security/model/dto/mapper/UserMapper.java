package com.vladdosiik.security.model.dto.mapper;

import com.vladdosiik.security.model.User;
import com.vladdosiik.security.model.dto.NewUserForAdmin;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    NewUserForAdmin toNewUserForAdmin(User user);

    User toUser(NewUserForAdmin newUserForAdmin);
}
