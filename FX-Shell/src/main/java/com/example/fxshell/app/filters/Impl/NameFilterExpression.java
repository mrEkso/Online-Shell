package com.example.fxshell.app.filters.Impl;

import com.example.fxshell.app.filters.FileFilterExpression;

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
