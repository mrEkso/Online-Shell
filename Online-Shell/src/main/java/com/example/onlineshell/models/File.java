package com.example.onlineshell.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "files")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;
    private String type;
    private Long size;

    @Setter
    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    public File(String name, String type, Long size, User user) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.user = user;
    }
}
