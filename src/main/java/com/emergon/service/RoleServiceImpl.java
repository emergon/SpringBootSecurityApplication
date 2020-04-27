package com.emergon.service;

import com.emergon.entities.Role;
import com.emergon.repository.RoleRepositoy;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepositoy roleRepo;
    
    @Override
    public List<Role> getAllRoles() {
        List<Role> roles = roleRepo.findAll();
        return roles;
    }
    
}
