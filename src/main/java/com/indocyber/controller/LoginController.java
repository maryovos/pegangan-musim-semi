package com.indocyber.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-login")
public class LoginController {

    @GetMapping("/index")
    public String showIndexPage(){

        return "index";
    }

    @GetMapping("/home")
    public String showHomePage(){

        return "dashboard";
    }

    @GetMapping("/loginPage")
    public String showLoginPage(){

        return "login";
    }

}
