package com.example.fxshell.models;

public class MyFile {
    private final String id;
    private final String name;
    private final String type;
    private final Long size;

    public MyFile(String id, String name, String type, Long size) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public Long getSize() {
        return size;
    }

    @Override
    public String toString() {
        return name;
    }
}
