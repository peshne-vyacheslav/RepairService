package com.example.repair.controller;

import com.example.repair.entity.enums.Role;
import com.example.repair.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderViewController {

    @GetMapping("/")
    public String orderView(Authentication authentication) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        if (userPrincipal.getRoles().contains(Role.ADMIN)) {
            return "admin_orders";
        }
        return "customer_orders";
    }
}
