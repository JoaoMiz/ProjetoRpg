package com.example.Rpg_Marnes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.Rpg_Marnes.model.User.User;

public interface UserRepository extends JpaRepository<User , String>{
    
    UserDetails findByLogin(String login);
}