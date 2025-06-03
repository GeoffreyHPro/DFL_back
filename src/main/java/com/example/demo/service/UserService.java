package com.example.demo.service;

import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.payload.EmailPasswordRequest;
import com.example.demo.reponses.TokenResponse;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JWTUtils jwtUtils;

  public User getUser(String userId) {
    return userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
  }

  public void save(User user) throws Exception {
    User userGet = null;
    userGet = (User) loadUserByUsername(user.getUsername());
    if (userGet == null) {
      userRepository.save(user);
    } else {
      throw new Exception();
    }
  }

  public TokenResponse getTokenResponse(EmailPasswordRequest content) {
    User user = (User) loadUserByUsername(content.getEmail());
    String jwt = jwtUtils.generateToken(user);
    return new TokenResponse(jwt, user.getRole());
  }

  public User getActiveUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      System.out.println("rien");
    }

    Object principal = authentication.getPrincipal();

    if (principal instanceof User) {
      return (User) principal;
    } else {
      return null;
    }
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    return (UserDetails) userRepository.findByEmail(username);
  }
}
