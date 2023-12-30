package com.example.fxshell.models.FileManager;

import java.io.File;

public interface FileManagerOperations {
    void copy(File source, File destination);

    void move(File source, File destination);

    void delete(File source);
}
