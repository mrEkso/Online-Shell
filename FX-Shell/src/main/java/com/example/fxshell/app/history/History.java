package com.example.fxshell.app.history;

import java.io.File;
import java.util.ArrayList;

public class History {
    private final ArrayList<File> history;

    public History() {
        history = new ArrayList<>();
    }

    public File[] navigateBack() {
        if (history.size() > 1) history.remove(history.size() - 1);
        return getFilesCurrentDirectory();
    }

    public File[] navigateForward(File directory) {
        if (!directory.isDirectory())
            throw new IllegalArgumentException("File is not a directory");
        history.add(directory);
        return directory.listFiles();
    }

    public void clear() {
        history.clear();
    }

    public File getCurrentDirectory() {
        return history.isEmpty() ? null :
                history.get(history.size() - 1);
    }

    public File[] getFilesCurrentDirectory() {
        File current = getCurrentDirectory();
        return current == null ? null : current.listFiles();
    }
}
