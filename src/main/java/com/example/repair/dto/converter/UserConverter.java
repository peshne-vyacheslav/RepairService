package com.example.repair.dto.converter;

import com.example.repair.dto.UserDto;
import com.example.repair.entity.User;
import com.example.repair.entity.UserRole;
import com.example.repair.entity.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserConverter {

    @Mapping(source = "user.userRoles", target = "roles")
    UserDto toUserDto(User user);

    default Role toRoleEnum(UserRole userRole) {
        return userRole.getRole();
    }
}
