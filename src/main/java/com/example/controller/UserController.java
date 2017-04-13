package com.example.controller;

import com.example.model.entity.Employee;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

//    @GetMapping("/me")
//    public User getMe(@AuthenticationPrincipal User user) {
//        return user;
//    }

    @GetMapping("/me")
    public Employee getMe(@AuthenticationPrincipal Employee employee) {
        return employee;
    }
}
