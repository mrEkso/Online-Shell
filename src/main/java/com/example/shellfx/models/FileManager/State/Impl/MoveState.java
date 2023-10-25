package com.example.shellfx.models.FileManager.State.Impl;

import com.example.shellfx.models.FileManager.FileManager;
import com.example.shellfx.models.FileManager.State.FileManagerState;

import java.io.File;

public class MoveState implements FileManagerState {

    @Override
    public void perform(FileManager ctx) {
        move(ctx.getSource(), ctx.getDestination());
    }

    private void move(File source, File destination) {
        if (source.exists() && !destination.exists()) {
            File newFile = new File(destination, source.getName());
            source.renameTo(newFile);
        }
    }
}
