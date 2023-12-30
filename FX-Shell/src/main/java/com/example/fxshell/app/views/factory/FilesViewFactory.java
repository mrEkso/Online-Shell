package com.example.fxshell.app.views.factory;


import com.example.fxshell.app.views.FilesView;

public abstract class FilesViewFactory<T extends FilesView<?>> {
    public T create() {
        return createFilesView();
    }

    protected abstract T createFilesView();
}
