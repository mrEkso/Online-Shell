package com.example.shell.fx.layouts.files.filters.Impl;

import com.example.shell.fx.layouts.files.filters.FileFilterExpression;

import java.io.File;

public class ExtensionFilterExpression extends FileFilterExpression {
    private final String extension;

    public ExtensionFilterExpression(String extension) {
        this.extension = extension;
    }

    @Override
    public boolean interpret(File file) {
        return file.getName().endsWith("." + extension);
    }
}
