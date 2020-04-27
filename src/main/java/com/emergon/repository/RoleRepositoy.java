package com.emergon.repository;

import com.emergon.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositoy extends JpaRepository<Role, Integer>{
    
}
