package com.example.shell.models.FileManager;

import com.example.shell.models.Disk;
import com.example.shell.models.FileManager.State.FileManagerState;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class FileManager {
    private static Disk currentDisk;
    private static File source;
    private static File destination;
    private static FileManagerState state;

    public static Disk getCurrentDisk() {
        return currentDisk;
    }

    public static File getSource() {
        return source;
    }

    public static File getDestination() {
        return destination;
    }

    public static FileManagerState getState() {
        return state;
    }

    public static void setCurrentDisk(Disk currentDisk) {
        FileManager.currentDisk = currentDisk;
    }

    public static void setDestination(File destination) {
        FileManager.destination = destination;
    }

    public void setState(FileManagerState state) {
        FileManager.state = state;
    }

    public static void getUpInFileSystem() {
        String path = source.getPath();
        source = new File(path.substring(0, path.lastIndexOf(File.separator)));
    }

    public static Disk findDiskByName(String diskName) {
        return Arrays.stream(File.listRoots())
                .filter(root -> root.getAbsolutePath().startsWith(diskName))
                .findFirst()
                .map(Disk::new)
                .orElse(null);
    }

    public static void getDownInFileSystem(String folderPath) {
        source = new File(source.getPath() + File.separator + folderPath);
    }

    public void perform() {
        state.perform(this);
    }
}
