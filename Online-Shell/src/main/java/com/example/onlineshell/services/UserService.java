package com.example.onlineshell.services;


import com.example.onlineshell.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public interface UserService extends UserDetailsService {
    User loadUserByUsername(String email);

    boolean checkPassword(User user, String password);

    User register(User user);
}
