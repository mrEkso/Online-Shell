package com.example.onlineshell.services;

import com.example.onlineshell.models.User;
import com.example.onlineshell.repository.factory.FactoryRepository;
import com.example.onlineshell.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Lazy)
public class UserServiceImpl implements UserService {
    private final FactoryRepository fr;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return fr.getUserRepository().findByEmail(email);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return !encoder().matches(password, loadUserByUsername(user.getEmail()).getPassword());
    }

    @Override
    public User register(User user) {
        user.setPassword(encoder().encode(user.getPassword()));
        user.setToken(jwtTokenProvider.createToken(user));
        return fr.getUserRepository().save(user);
    }

    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
