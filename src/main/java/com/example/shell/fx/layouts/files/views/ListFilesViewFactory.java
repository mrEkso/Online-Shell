package com.example.shell.fx.layouts.files.views;

import com.example.shell.fx.layouts.files.views.Impl.ListFilesView;
import com.example.shell.fx.layouts.files.views.Impl.FilesView;

public class ListFilesViewFactory extends FilesViewFactory {
    @Override
    public FilesView createFilesView() {
        return new ListFilesView();
    }
}
