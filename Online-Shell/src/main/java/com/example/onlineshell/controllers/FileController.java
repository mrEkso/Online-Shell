package com.example.onlineshell.controllers;

import com.example.onlineshell.models.File;
import com.example.onlineshell.models.User;
import com.example.onlineshell.responses.BaseResponse;
import com.example.onlineshell.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("/my")
    @ResponseBody
    protected List<File> index(@AuthenticationPrincipal User user) {
        return fileService.findAllByUser(user);
    }

    @PostMapping("/upload")
    @ResponseBody
    public BaseResponse handleFileUpload(@RequestParam("file") MultipartFile file,
                                         @AuthenticationPrincipal User user) {
        if (file.isEmpty()) return new BaseResponse(400,
                "Не вдалося зберегти порожній файл.");
        try {
            fileService.upload(file, user);
        } catch (Exception e) {
            if (file.isEmpty()) return new BaseResponse(500,
                    "Не вдалося зберегти файл.");
        }
        return new BaseResponse(200,
                "Файл успішно завантажено.");
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> getDownloadUrl(@PathVariable UUID fileId,
                                                   @AuthenticationPrincipal User user) {
        System.out.println(fileId + " " + user.getId());
        File file = fileService.findByIdAndUser(fileId, user);
        if (file == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileService.loadAsResource(file.getName()));
    }
}
