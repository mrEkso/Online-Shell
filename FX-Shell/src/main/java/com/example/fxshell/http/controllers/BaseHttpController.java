package com.example.fxshell.http.controllers;

import com.example.fxshell.http.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

public class BaseHttpController {
    Gson gson = new Gson();
    String token;

    protected HttpResponse<String> get(String endpoint) {
        try {
            return HttpClient.newHttpClient().send(
                    HttpRequest.newBuilder()
                            .uri(URI.create(Utils.SERVER_URL + endpoint))
                            .header("Authorization", "Bearer " + token)
                            .GET()
                            .build(),
                    HttpResponse.BodyHandlers.ofString()
            );
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    protected HttpResponse<String> post(String endpoint, Map<Object, Object> data) {
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

    protected String processResponse(HttpResponse<String> response) {
        if (response == null) return "Помилка на сервері";
        return gson.fromJson(response.body(), JsonObject.class).get("message").getAsString();
    }

    protected String processAuthResponse(HttpResponse<String> response) {
        if (response == null) return "Помилка на сервері";
        JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
        if (jsonObject.has("token")) {
            token = jsonObject.get("token").getAsString();
        }
        return jsonObject.get("message").getAsString();
    }
}
