package com.example.shell.fx.layouts.files.filters;

public abstract class Expression<T> {
    public abstract boolean interpret(T context);
}
