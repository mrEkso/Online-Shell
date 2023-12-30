package com.example.fxshell.models.FileManager.State.States;

import com.example.fxshell.models.FileManager.FileManager;
import com.example.fxshell.models.FileManager.State.FileManagerState;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class CopyState extends FileManagerState {

    @Override
    public void perform(FileManager ctx) {
        try {
            copy(ctx.getSource(), ctx.getDestination());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void copy(File source, File destination) throws IOException {
        if (source == null) return;
        File destinationFile = new File(destination, source.getName());
        // якщо папка була створена
        if (source.exists() && !destinationFile.exists()) {
            // якщо файл - директорія, то копіюємо як директорію
            if (source.isDirectory()) FileUtils.copyDirectory(source, destinationFile);
                // якщо файл - просто файл, то копіюємо як файл
            else if (source.isFile()) FileUtils.copyFile(source, destinationFile);
        }
    }
}
