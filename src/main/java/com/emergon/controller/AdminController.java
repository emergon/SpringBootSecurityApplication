package com.emergon.controller;

import com.emergon.entities.MyUser;
import com.emergon.entities.Role;
import com.emergon.service.MyUserService;
import com.emergon.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    
//    @Autowired
//    private JdbcUserDetailsManager jdbcUserDetailsManager;
    
    @Autowired
    private RoleService roleService;
    @Autowired
    private MyUserService userService;
    
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
        return "register";
    }
    
    @ModelAttribute("roloi")
    public List<Role> getListOfRoles(){
        return roleService.getAllRoles();
    }
//    POST: admin/register
    @PostMapping("/register")
    public String processRegistration(@ModelAttribute("myuser") MyUser myuser, Model m){
        MyUser existingUser = userService.findByUsername(myuser.getUsername());
        if(existingUser != null){
            m.addAttribute("userExistsError", "Username already exists!!");
            m.addAttribute("myuser", new MyUser());
            return "register";
        }
        userService.saveUser(myuser);
        return "redirect:/admin/home";
    }
    
}
