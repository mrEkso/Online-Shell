package com.example.fxshell.files.views;


import com.example.fxshell.files.views.Impl.FilesView;

public abstract class FilesViewFactory {
    public FilesView create() {
        return createFilesView();
    }

    protected abstract FilesView createFilesView();
}
