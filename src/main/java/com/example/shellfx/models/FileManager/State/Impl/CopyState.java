package com.example.shellfx.models.FileManager.State.Impl;

import com.example.shellfx.models.FileManager.FileManager;
import com.example.shellfx.models.FileManager.State.FileManagerState;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CopyState implements FileManagerState {

    @Override
    public void perform(FileManager ctx) {
        try {
            copy(ctx.getSource(), ctx.getDestination());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copy(File source, File destination) throws IOException {
        if (!destination.exists() && destination.mkdirs()) {
            if (source.isDirectory()) FileUtils.copyDirectory(source, destination);
            if (source.isFile()) FileUtils.copyFile(source, destination);
        }
    }
}
