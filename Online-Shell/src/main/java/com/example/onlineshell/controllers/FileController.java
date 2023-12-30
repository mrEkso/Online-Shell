package com.example.onlineshell.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@RestController
@RequestMapping("/files")
public class FileController {

    private final Path rootLocation;

    public FileController() {
        this.rootLocation = Paths.get("uploaded-files");
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Не вдалося ініціалізувати папку для завантаження!");
        }
    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "Не вдалося зберегти порожній файл.";
            }
            String filename = System.currentTimeMillis() + "-" + file.getOriginalFilename(); // Unique filename
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
            return "Файл завантажено успішно: " + file.getOriginalFilename();
        } catch (Exception e) {
            return "Не вдалося зберегти файл " + file.getOriginalFilename();
        }
    }
}
