package com.example.fxshell.files.views;

import com.example.fxshell.files.views.Impl.FilesView;
import javafx.scene.control.Tab;

import java.io.File;
import java.util.List;

public class FilesViewTab extends Tab {
    private static FilesView filesView;
    private static List<File> files;

    public static FilesView getFilesView() {
        return filesView;
    }

    public static void setFilesView(FilesView filesView) {
        FilesViewTab.filesView = filesView;
    }

    public static void setFiles(List<File> files) {
        FilesViewTab.files = files;
        FilesViewTab.filesView.addFiles(files);
    }
}
