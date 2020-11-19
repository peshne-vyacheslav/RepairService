package com.example.repair.dto;

import com.example.repair.entity.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    private String name;
    private String created;
    private Set<Role> roles;
}
