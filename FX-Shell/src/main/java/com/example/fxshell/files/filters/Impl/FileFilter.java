package com.example.fxshell.files.filters.Impl;

import com.example.fxshell.files.filters.FileFilterExpression;
import javafx.scene.control.ListView;

import java.io.File;
import java.util.List;

public class FileFilter {
    public static void applyFilter(ListView<String> fileListView, List<File> files, FileFilterExpression filterExpression) {
        fileListView.getItems().clear();

        for (File file : files) {
            if (filterExpression.interpret(file)) {
                fileListView.getItems().add(file.getName());
            }
        }
    }
}
