package com.my.article.controller;

import com.my.article.dto.CustomerDTO;
import com.my.article.entity.Customer;
import com.my.article.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
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
    public String signup(Model model) {
        model.addAttribute("customer", new Customer());
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String create(Customer customer, Model model) {

        try {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

            Customer customerDTO = new Customer();

            customerDTO.setEmail(customer.getEmail());
            customerDTO.setPassword(passwordEncoder.encode(customer.getPassword()));
            customerDTO.setName(customer.getName());
            customerDTO.setPhone(customer.getPhone());
            customerDTO.setAuth("USER");

            customerService.save(customerDTO);

            if(customerDTO.getEmail() != null) {
                log.info("회원 가입한 회원의 정보 : " + customerDTO);

                return "redirect:/login";
            } else {
                return "auth/signup";
            }
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            log.info("에러 내용 : " + e.getMessage());

            return "auth/error";
        }

    }

}
