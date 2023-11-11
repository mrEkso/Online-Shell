package com.example.shell.models.FileManager.State.States;

import com.example.shell.models.FileManager.FileManager;
import com.example.shell.models.FileManager.State.FileManagerState;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class MoveState extends FileManagerState {

    @Override
    public void perform(FileManager ctx) {
        try {
            move(ctx.getSource(), ctx.getDestination());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void move(File source, File destination) throws IOException {
        if (source.exists() && !destination.exists()) {
            if (source.isDirectory()) FileUtils.moveDirectory(source, destination);
            else if (source.isFile()) FileUtils.moveFile(source, destination);
        }
    }
}
