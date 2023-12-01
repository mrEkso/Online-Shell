package com.example.onlineshell.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@ToString
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "login"
        })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Логін не може бути пустим")
    @Size(max = 100)
    @Email(message = "Некоректний логін")
    private String login;

    @NotBlank(message = "Пароль не може бути пустим")
    @Size(max = 100)
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
