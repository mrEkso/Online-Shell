package com.example.onlineshell.controllers;

import com.example.onlineshell.models.User;
import com.example.onlineshell.responses.auth.LoginResponse;
import com.example.onlineshell.responses.auth.LogoutResponse;
import com.example.onlineshell.responses.auth.RegisterResponse;
import com.example.onlineshell.services.UserService;
import jakarta.security.auth.message.AuthException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthorizationController {
    UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseBody
    protected RegisterResponse register(@Valid @RequestBody User user) {
        User dbUser = userService.register(user);
        return new RegisterResponse(
                dbUser,
                dbUser.getToken()
        );
    }

    @PostMapping("/login")
    @ResponseBody
    public LoginResponse login(@Valid @RequestBody User user) throws AuthException {
        User dbUser = userService.loadUserByUsername(user.getEmail());

        if (dbUser == null || userService.checkPassword(dbUser, user.getPassword()))
            throw new AuthException();

        return new LoginResponse(
                dbUser,
                dbUser.getToken()
        );
    }

    @GetMapping("/logout")
    @ResponseBody
    protected LogoutResponse logout() {
        return new LogoutResponse();
    }
}
