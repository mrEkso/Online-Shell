package com.example.shell.models.FileManager;

import com.example.shell.models.Disk;
import com.example.shell.models.FileManager.State.FileManagerState;

import java.io.File;
import java.util.Objects;

public class FileManager {
    private Disk currentDisk;
    private File source;
    private File destination;
    private FileManagerState state;

    public Disk getCurrentDisk() {
        return currentDisk;
    }

    public File getSource() {
        return source;
    }

    public File getDestination() {
        return destination;
    }

    public FileManagerState getState() {
        return state;
    }

    public void setCurrentDisk(Disk currentDisk) {
        this.currentDisk = currentDisk;
    }

    public void setDestination(File destination) {
        this.destination = destination;
    }

    public void setState(FileManagerState state) {
        this.state = state;
    }

    public void getUpInFileSystem() {
        String path = source.getPath();
        this.source = new File(path.substring(0, path.lastIndexOf(File.separator)));
    }

    public void getDownInFileSystem(String folderPath) {
        this.source = new File(source.getPath() + File.separator + folderPath);
    }

    public void perform() {
        state.perform(this);
    }

    @Override
    public String toString() {
        return "FileManager{" +
                "currentDisk=" + currentDisk +
                ", source='" + source + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileManager fileManager)) return false;
        return Objects.equals(getCurrentDisk(), fileManager.getCurrentDisk())
                && Objects.equals(getSource(),
                fileManager.getSource());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCurrentDisk(), getSource());
    }
}
