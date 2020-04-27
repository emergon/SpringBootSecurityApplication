package com.emergon.repository;

import com.emergon.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Integer>{
    
    MyUser findByUsername(String username);
    
}
