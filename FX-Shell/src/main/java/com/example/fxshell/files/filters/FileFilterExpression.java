package com.example.fxshell.files.filters;

import java.io.File;

public abstract class FileFilterExpression extends Expression<File> {
    public abstract boolean interpret(File file);
}
