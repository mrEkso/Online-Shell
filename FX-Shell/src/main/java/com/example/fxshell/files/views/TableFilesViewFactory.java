package com.example.fxshell.files.views;

import com.example.fxshell.files.views.Impl.FilesView;
import com.example.fxshell.files.views.Impl.TableFilesView;

public class TableFilesViewFactory extends FilesViewFactory {
    @Override
    public FilesView createFilesView() {
        return new TableFilesView();
    }
}
