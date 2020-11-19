package com.example.repair.rest;

import com.example.repair.dto.UserDto;
import com.example.repair.dto.converter.UserConverter;
import com.example.repair.entity.User;
import com.example.repair.entity.enums.Role;
import com.example.repair.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserConverter userConverter;

    @GetMapping("/users")
    public List<UserDto> getUsers() {
        return userService.getUsers().stream()
                .map(userConverter::toUserDto)
                .collect(Collectors.toList());
    }
}
