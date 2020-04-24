package com.emergon.controller;

import com.emergon.entities.MyUser;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private JdbcUserDetailsManager jdbcUserDetailsManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/home")//    admin/home
    public String adminHome(){
        return "admin-home";
    }
    
    //ModelMap
    //ModelAndView
    //Model
//   GET: admin/register
    @RequestMapping(value = "/register", method = RequestMethod.GET)//@GetMapping("/register")
    public String showRegisterForm(Model model){
        model.addAttribute("myuser", new MyUser());
        List<String> roloi = new ArrayList();
        roloi.add("USER");
        roloi.add("ADMIN");
        roloi.add("TEACHER");
        model.addAttribute("roloi", roloi);
        return "register";
    }
//    POST: admin/register
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("myuser") MyUser myuser){
        System.out.println(myuser.getUsername());
        System.out.println(myuser.getPassword());
        System.out.println(myuser.getRoles());
        List<GrantedAuthority> authorities = new ArrayList();
        for(String role: myuser.getRoles()){
            String tmp = "ROLE_"+role;
            authorities.add(new SimpleGrantedAuthority(tmp));
        }
        String encodedPassword = passwordEncoder.encode(myuser.getPassword());
        User user = new User(myuser.getUsername(), encodedPassword, authorities);
        jdbcUserDetailsManager.createUser(user);
        
        return "redirect:/admin/home";
    }
    
}
