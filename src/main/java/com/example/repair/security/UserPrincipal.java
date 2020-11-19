package com.example.repair.security;

import com.example.repair.entity.UserRole;
import com.example.repair.entity.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserPrincipal extends User {

    private final com.example.repair.entity.User user;

    public UserPrincipal(com.example.repair.entity.User user) {
        super(user.getLogin(), user.getPassword(), convertToAuthorities(user.getUserRoles()));
        this.user = user;
    }

    private static Set<GrantedAuthority> convertToAuthorities(Set<UserRole> userRoles) {
        return userRoles.stream()
                .map(UserRole::getRole)
                .map(role -> new SimpleGrantedAuthority(role.name()))
                .collect(Collectors.toSet());
    }

    public Long getUserId() {
        return user.getId();
    }

    public Set<Role> getRoles() {
        return user.getRoles();
    }
}
