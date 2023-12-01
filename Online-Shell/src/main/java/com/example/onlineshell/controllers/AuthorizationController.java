package com.example.onlineshell.controllers;

import com.example.onlineshell.models.User;
import com.example.onlineshell.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class AuthorizationController {

    UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    protected ResponseEntity<?> register(@Valid @RequestBody User user,
                                         HttpServletRequest request) {
        request.getSession().invalidate();
        if (userService.exists(user.getLogin())) {
            return new ResponseEntity<>("Користувача з таким логіном вже було зареєстровано.",
                    HttpStatus.BAD_REQUEST);
        }

        userService.create(user);
        request.getSession().setAttribute("user", user);

        return new ResponseEntity<>("Успішно зареєстровано.",
                HttpStatus.OK);
    }

    @PostMapping(value = "/login")
    protected ResponseEntity<?> login(@Valid @RequestBody User user,
                                      HttpServletRequest request) {
        User dbUser = userService.getByLogin(user.getLogin());
        if (dbUser == null) {
            return new ResponseEntity<>("Користувача з логіном: " + user.getLogin() + ", не існує.",
                    HttpStatus.NOT_FOUND);
        }
        if (!userService.checkPassword(dbUser, user.getPassword())) {
            return new ResponseEntity<>("Неправильний пароль.", HttpStatus.UNAUTHORIZED);
        }

        request.getSession().setAttribute("user", user);
        return new ResponseEntity<>("Ви успішно ввійшли.", HttpStatus.OK);
    }

    @GetMapping("/logout")
    protected ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return ResponseEntity.ok().build();
    }
}
