package com.vladdosiik.security.model.dto.mapper;

import com.vladdosiik.security.model.User;
import com.vladdosiik.security.model.dto.UserPostAdminDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserPostAdminDto toUserPostAdminDto(User user);

    User toUser(UserPostAdminDto userPostAdminDto);
}
