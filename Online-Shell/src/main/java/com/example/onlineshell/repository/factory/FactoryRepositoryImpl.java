package com.example.onlineshell.repository.factory;

import com.example.onlineshell.repository.FileRepository;
import com.example.onlineshell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FactoryRepositoryImpl implements FactoryRepository {
    UserRepository userRepository;
    FileRepository fileRepository;

    @Autowired
    public FactoryRepositoryImpl(UserRepository userRepository, FileRepository fileRepository) {
        this.userRepository = userRepository;
        this.fileRepository = fileRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public FileRepository getFileRepository() {
        return fileRepository;
    }
}
