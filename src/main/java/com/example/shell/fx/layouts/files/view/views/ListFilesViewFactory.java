package com.example.shell.fx.layouts.files.view.views;

import com.example.shell.fx.layouts.files.view.views.Impl.ListFilesView;
import com.example.shell.fx.layouts.files.view.views.Impl.FilesView;

public class ListFilesViewFactory extends FilesViewFactory {
    @Override
    public FilesView createFilesView() {
        return new ListFilesView();
    }
}
