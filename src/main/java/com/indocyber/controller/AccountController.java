package com.indocyber.controller;

import com.indocyber.dto.RegisterDto;
import com.indocyber.service.AccountService;
import com.indocyber.utility.Dropdown;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/registerForm")
    public String registerForm(Model model){

        RegisterDto registerDto = new RegisterDto();

        List<Dropdown> roleDropdown = Dropdown.getRoleDropdown();

        model.addAttribute("roleDropdown", roleDropdown);
        model.addAttribute("account", registerDto);

        return "register-form";
    }

    @GetMapping("/registerForm2")
    public String registerForm2(Model model){

        RegisterDto registerDto = new RegisterDto();

        List<Dropdown> roleDropdown = Dropdown.getRoleDropdown();

        model.addAttribute("roleDropdown", roleDropdown);
        model.addAttribute("account", registerDto);

        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("account")RegisterDto registerDto, Model model){

        accountService.registerAccount(registerDto);

        return "redirect:/my-login/showMyLoginPage";

    }

}
