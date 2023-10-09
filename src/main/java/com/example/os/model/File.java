package com.example.os.model;

public class File {
    private final int id;
    private final int folder_id;
    private final String name;
    private final String path;
    private final int size;

    public File(int id, int folder_id, String name, String path, int size) {
        this.id = id;
        this.folder_id = folder_id;
        this.name = name;
        this.path = path;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public int getFolder_id() {
        return folder_id;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getSize() {
        return size;
    }
}
