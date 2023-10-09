package com.example.os.model;

public class Folder {
    private final int id;
    private final String name;
    private final String path;

    public Folder(int id, String name, String path) {
        this.id = id;
        this.name = name;
        this.path = path;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }
}
