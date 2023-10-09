package com.example.os.model;

public class Disk {
    private final int id;
    private final String label;
    private final int size;

    public Disk(int id, String label, int size) {
        this.id = id;
        this.label = label;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public int getSize() {
        return size;
    }
}
