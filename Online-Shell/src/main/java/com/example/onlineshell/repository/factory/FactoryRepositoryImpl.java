package com.example.onlineshell.repository.factory;

import com.example.onlineshell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryRepositoryImpl implements FactoryRepository {
    UserRepository userRepository;

    @Autowired
    public FactoryRepositoryImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }
}
