package com.example.fxshell.models.FileManager;

import com.example.fxshell.models.FileManager.State.States.CopyState;
import com.example.fxshell.models.FileManager.State.States.DeleteState;
import com.example.fxshell.models.FileManager.State.States.MoveState;

import java.io.File;

public class FileManagerOperationsImpl implements FileManagerOperations {
    FileManager fileManager;

    public FileManagerOperationsImpl() {
        this.fileManager = new FileManager();
    }

    public void copy(File source, File destination) {
        fileManager.setState(new CopyState());
        fileManager.setSource(source);
        fileManager.setDestination(destination);
        fileManager.perform();
    }

    public void move(File source, File destination) {
        fileManager.setState(new MoveState());
        fileManager.setSource(source);
        fileManager.setDestination(destination);
        fileManager.perform();
    }

    public void delete(File source) {
        fileManager.setState(new DeleteState());
        fileManager.setSource(source);
        fileManager.perform();
    }
}
