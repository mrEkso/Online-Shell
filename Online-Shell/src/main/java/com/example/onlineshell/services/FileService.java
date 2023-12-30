package com.example.onlineshell.services;

import com.example.onlineshell.models.File;
import com.example.onlineshell.models.User;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Component
public interface FileService {
    List<File> findAllByUser(User user);

    File findByIdAndUser(UUID id, User user);

    void save(File file);

    void upload(MultipartFile file, User user) throws IOException;

    Resource loadAsResource(String fileName);
}