package com.example.onlineshell.responses.auth;


import com.example.onlineshell.models.User;
import com.example.onlineshell.responses.BaseAuthResponse;

public class RegisterResponse extends BaseAuthResponse {
    public RegisterResponse(User user, String token) {
        super(201, "Ви успішно зареєструвалися", user, token);
    }
}
