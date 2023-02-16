package com.my.article.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/web")
    public String WebPage() {

        return "view/web";
    }

    @GetMapping("/app")
    public String AppPage() {

        return "view/app";
    }

}
