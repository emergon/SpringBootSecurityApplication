package com.emergon.service;

import com.emergon.entities.MyUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MyUserService extends UserDetailsService{

    public void saveUser(MyUser myuser);

    public MyUser findByUsername(String username);

    
    
}
