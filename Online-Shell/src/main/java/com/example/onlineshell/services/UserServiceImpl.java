package com.example.onlineshell.services;

import com.example.onlineshell.models.User;
import com.example.onlineshell.repository.factory.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final FactoryRepository fr;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(FactoryRepository factoryRepository) {
        this.fr = factoryRepository;
    }

    @Override
    public User getByLogin(String login) {
        return fr.getUserRepository().getByLogin(login);
    }

    @Override
    public boolean exists(String login) {
        return getByLogin(login) != null;
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public void create(User user) {
        fr.getUserRepository().save(new User(
                user.getLogin(),
                passwordEncoder.encode(user.getPassword())
        ));
    }
}
