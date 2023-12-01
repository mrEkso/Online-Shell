package com.example.fxshell.files.filters.Impl;

import com.example.fxshell.files.filters.FileFilterExpression;

import java.io.File;

public class NameFilterExpression extends FileFilterExpression {
    private final String targetName;

    public NameFilterExpression(String targetName) {
        this.targetName = targetName;
    }

    @Override
    public boolean interpret(File file) {
        return file.getName().contains(targetName);
    }
}
