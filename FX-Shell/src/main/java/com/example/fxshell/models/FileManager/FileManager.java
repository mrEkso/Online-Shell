package com.example.fxshell.models.FileManager;

import com.example.fxshell.models.FileManager.State.FileManagerState;

import java.io.File;

public class FileManager {
    private File source;
    private File destination;
    private FileManagerState state;

    public File getSource() {
        return source;
    }

    public File getDestination() {
        return destination;
    }

    public void setSource(File source) {
        this.source = source;
    }

    public void setDestination(File destination) {
        this.destination = destination;
    }

    public void setState(FileManagerState state) {
        this.state = state;
    }

    public void perform() {
        state.perform(this);
    }
}
