package com.emergon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
public class HomeController {
    
    @GetMapping
    public String welcomePage(){
        return "welcome";
    } 
    
    @GetMapping("/access-denied")
    public String showAccessDenied(){
        return "access-denied";
    }
}
