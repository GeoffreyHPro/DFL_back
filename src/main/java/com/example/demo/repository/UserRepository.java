package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, String>{
   UserDetails findByEmail(String email);
}
