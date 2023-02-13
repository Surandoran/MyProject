package com.my.article.controller;

import com.my.article.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    private final CustomerService customerService;

    public AuthController(@Autowired CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "/";
    }

    @GetMapping("/signup")
    public String signup() {
        return "auth/signup";
    }

}
