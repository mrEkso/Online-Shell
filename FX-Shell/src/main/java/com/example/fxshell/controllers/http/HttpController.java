package com.example.fxshell.controllers.http;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import com.example.fxshell.Utils;
import com.google.gson.Gson;

public class HttpController {
    Gson gson = new Gson();

    public String register(String login, String password) {
        HttpResponse<String> response = post("register", Map.of(
                "login", login,
                "password", password
        ));
        return response != null ? response.body() : "Помилка на сервері";
    }

    public String login(String login, String password) {
        HttpResponse<String> response = post("login", Map.of(
                "login", login,
                "password", password
        ));
        return response != null ? response.body() : "Помилка на сервері";
    }

    private HttpResponse<String> post(String endpoint, Map<Object, Object> data) {
        try {
            return HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder()
                            .uri(URI.create(Utils.SERVER_URL + endpoint))
                            .header("Accept", "application/json")
                            .header("Content-Type", "application/json")
                            .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(data)))
                            .build(),
                    HttpResponse.BodyHandlers.ofString()
            );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }
}