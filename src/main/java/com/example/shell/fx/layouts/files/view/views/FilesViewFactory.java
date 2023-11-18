package com.example.shell.fx.layouts.files.view.views;

import com.example.shell.fx.layouts.files.view.views.Impl.FilesView;

public abstract class FilesViewFactory {
    public FilesView create() {
        return createFilesView();
    }

    protected abstract FilesView createFilesView();
}
