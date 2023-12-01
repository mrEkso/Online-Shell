package com.example.onlineshell.services;

import com.example.onlineshell.models.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    User getByLogin(String login);

    boolean exists(String login);

    boolean checkPassword(User user, String password);

    void create(User user);
}
package com.example.onlineshell.services;

import com.example.onlineshell.models.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService {
    User getByLogin(String login);

    boolean exists(String login);

    boolean checkPassword(User user, String password);

    void create(User user);
}
