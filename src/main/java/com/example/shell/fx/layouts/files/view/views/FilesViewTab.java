package com.example.shell.fx.layouts.files.view.views;

import com.example.shell.fx.layouts.files.view.views.Impl.FilesView;
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
        FilesViewTab.filesView.addFiles(files);
    }

    public static void setFiles(List<File> files) {
        FilesViewTab.files = files;
    }
}
