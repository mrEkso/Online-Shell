package com.example.onlineshell.responses.auth;

import com.example.onlineshell.models.User;
import com.example.onlineshell.responses.BaseAuthResponse;

public class LoginResponse extends BaseAuthResponse {
    public LoginResponse(User user, String token) {
        super(200, "Ви успішно ввійшли", user, token);
    }
}
