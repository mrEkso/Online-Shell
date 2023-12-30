package com.example.onlineshell.services;

import com.example.onlineshell.models.File;
import com.example.onlineshell.models.User;
import com.example.onlineshell.repository.factory.FactoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private final FactoryRepository fr;
    private final Path rootLocation;

    @Autowired
    public FileServiceImpl(FactoryRepository factoryRepository) throws IOException {
        this.fr = factoryRepository;
        rootLocation = Paths.get("uploaded-files");
        Files.createDirectories(rootLocation);
    }

    @Override
    public List<File> findAllByUser(User user) {
        return fr.getFileRepository().findAllByUserId(user.getId());
    }

    @Override
    public File findByIdAndUser(UUID id, User user) {
        return fr.getFileRepository().findByIdAndUserId(id, user.getId());
    }

    @Override
    public void save(File file) {
        fr.getFileRepository().save(file);
    }

    @Override
    public void upload(MultipartFile file, User user) throws IOException {
        String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename(); // Unique filename
        Files.copy(file.getInputStream(), this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
        save(new File(filename, file.getContentType(), file.getSize(), user));
    }

    public Resource loadAsResource(String fileName) {
        try {
            Path file = rootLocation.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Не вдалося прочитати файл: " + fileName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Не вдалося завантажити файл: " + fileName, e);
        }
    }
}
