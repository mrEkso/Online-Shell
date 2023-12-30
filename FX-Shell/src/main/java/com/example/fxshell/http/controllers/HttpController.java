package com.example.fxshell.http.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

import com.example.fxshell.Utils;
import com.example.fxshell.http.services.HttpService;
import com.example.fxshell.http.services.HttpServiceImpl;
import com.example.fxshell.models.MyFile;

public class HttpController extends BaseHttpController {
    HttpService httpService;

    public HttpController() {
        this.httpService = new HttpServiceImpl();
    }

    public String register(String email, String password) {
        return processAuthResponse(post("auth/register",
                Map.of("email", email, "password", password)));
    }

    public String login(String email, String password) {
        return processAuthResponse(post("auth/login",
                Map.of("email", email, "password", password)));
    }

    public MyFile[] getMyFiles() {
        return processFiles(get("files/my"));
    }

    public Path downloadFile(String fileId, String fileName, File directory) throws IOException {
        URL url = new URL(Utils.SERVER_URL + "files/download/" + fileId);
        Path targetPath = directory.toPath().resolve(fileName);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + token);

        try (InputStream in = connection.getInputStream()) {
            Files.copy(in, targetPath, StandardCopyOption.REPLACE_EXISTING);
        } finally {
            connection.disconnect();
        }

        return targetPath;
    }

    public String uploadFile(Path filePath) {
        try {
            String boundary = UUID.randomUUID().toString();
            byte[] body = httpService.prepareMultipartBody(filePath, boundary);
            HttpRequest request = buildMultipartRequest(boundary, body);
            return processResponse(HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString()));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Не вдалося зберегти файл.";
        }
    }

    private HttpRequest buildMultipartRequest(String boundary, byte[] body) throws IOException {
        return HttpRequest.newBuilder()
                .uri(URI.create(Utils.SERVER_URL + "files/upload"))
                .header("Content-Type", "multipart/form-data;boundary=" + boundary)
                .header("Authorization", "Bearer " + (token != null ? token : ""))
                .POST(HttpRequest.BodyPublishers.ofByteArray(body))
                .build();
    }

    protected MyFile[] processFiles(HttpResponse<String> response) {
        return response != null ? gson.fromJson(response.body(), MyFile[].class) : new MyFile[0];
    }
}