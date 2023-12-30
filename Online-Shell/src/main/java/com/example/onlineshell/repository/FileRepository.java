package com.example.onlineshell.repository;

import com.example.onlineshell.models.File;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FileRepository extends JpaRepository<File, UUID> {
    List<File> findAllByUserId(UUID userId);

    File findByIdAndUserId(UUID id, UUID userId);
}
