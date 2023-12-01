package com.example.onlineshell.repository.factory;


import com.example.onlineshell.repository.UserRepository;

public interface FactoryRepository {
    UserRepository getUserRepository();
}
