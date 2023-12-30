package com.example.fxshell.app.filters;

public abstract class Expression<T> {
    public abstract boolean interpret(T context);
}
