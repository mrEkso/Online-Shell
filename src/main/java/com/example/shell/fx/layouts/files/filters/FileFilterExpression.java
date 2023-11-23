package com.example.shell.fx.layouts.files.filters;

import java.io.File;

public abstract class FileFilterExpression extends Expression<File> {
    public abstract boolean interpret(File file);
}
