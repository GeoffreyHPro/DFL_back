package com.example.demo.service;

import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.payload.EmailPasswordRequest;
import com.example.demo.reponses.TokenResponse;
import com.example.demo.repository.userRepository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JWTUtils jwtUtils;

  public User getUser(int userId) {
    return userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
  }

  public User save(User user) {
    userRepository.findByEmail(user.getUsername())
        .ifPresent(existingUser -> {
          throw new IllegalArgumentException("Email déjà utilisé");
        });

    return userRepository.save(user);
  }

  public TokenResponse getTokenResponse(EmailPasswordRequest content) {
    User user = (User) loadUserByUsername(content.getEmail());
    String jwt = jwtUtils.generateToken(user);
    return new TokenResponse(jwt, user.getRole());
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmail(username).orElseThrow(ResourceNotFoundException::new);
  }
}
