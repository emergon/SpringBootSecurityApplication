package com.emergon.service;

import com.emergon.entities.MyUser;
import com.emergon.entities.Role;
import com.emergon.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class MyUserServiceImpl implements MyUserService{

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myuser = userRepo.findByUsername(username);
        if(myuser == null){
            throw new UsernameNotFoundException("Invalid username");
        }
        //user with this username exists
        User securityUser = 
                new User(myuser.getUsername(), myuser.getPassword(), mapRolesToAuthorities(myuser.getRoles()));
        return securityUser;
    }
    
    //helper method to transform Roles to Authorities
    private List<GrantedAuthority> mapRolesToAuthorities(List<Role> roles){
        List<GrantedAuthority> authorities = new ArrayList();
        for(Role r:roles){
            GrantedAuthority authority = new SimpleGrantedAuthority(r.getRname());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public void saveUser(MyUser myuser) {
        String encodedPassword = passwordEncoder.encode(myuser.getPassword());
        myuser.setPassword(encodedPassword);
        userRepo.save(myuser);
    }

    @Override
    public MyUser findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    
    
}
