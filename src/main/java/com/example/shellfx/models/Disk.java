package com.example.shellfx.models;

import java.io.File;
import java.util.Objects;

public class Disk extends File {
    public Disk(String pathname) {
        super(pathname);
    }

    public String getName() {
        return super.toString().substring(0, 1);
    }

    public double getFillPercentage() {
        return 100 - (((double) super.getFreeSpace() / super.getTotalSpace()) * 100);
    }

    @Override
    public String toString() {
        return "Disk{" +
                "name='" + getName() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disk disk)) return false;
        return Objects.equals(getName(), disk.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
