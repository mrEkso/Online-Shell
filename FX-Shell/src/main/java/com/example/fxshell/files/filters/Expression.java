package com.example.fxshell.files.filters;

public abstract class Expression<T> {
    public abstract boolean interpret(T context);
}
