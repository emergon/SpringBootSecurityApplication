package com.emergon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthenticationController {
    
    @RequestMapping("/loginPage")
    public String showLoginPage(){
        return "login-form";
    }
}
