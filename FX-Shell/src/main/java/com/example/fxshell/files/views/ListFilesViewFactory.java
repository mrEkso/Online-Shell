package com.example.fxshell.files.views;

import com.example.fxshell.files.views.Impl.FilesView;
import com.example.fxshell.files.views.Impl.ListFilesView;

public class ListFilesViewFactory extends FilesViewFactory {
    @Override
    public FilesView createFilesView() {
        return new ListFilesView();
    }
}
