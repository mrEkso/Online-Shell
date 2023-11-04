package com.example.shellfx.models.FileManager.State.Impl;

import com.example.shellfx.models.FileManager.FileManager;
import com.example.shellfx.models.FileManager.State.FileManagerState;
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
        // якщо папка була створена
        if (!destination.exists() && destination.mkdirs()) {
            // якщо файл - директорія, то копіюємо як директорію
            if (source.isDirectory()) FileUtils.copyDirectory(source, destination);
            // якщо файл - просто файл, то копіюємо як файл
            else if (source.isFile()) FileUtils.copyFile(source, destination);
        }
    }
}
