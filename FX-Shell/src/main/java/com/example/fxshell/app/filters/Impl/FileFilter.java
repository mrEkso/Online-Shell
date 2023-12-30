package com.example.fxshell.app.filters.Impl;

import com.example.fxshell.app.filters.FileFilterExpression;
import com.example.fxshell.app.views.FilesView;


import java.io.File;
import java.util.Arrays;

public class FileFilter {
    public static void applyFilter(FilesView<?> filesView, File[] files, FileFilterExpression filterExpression) {
        filesView.setFiles(Arrays.stream(files)
                .filter(filterExpression::interpret)
                .toArray(File[]::new));
    }
}
