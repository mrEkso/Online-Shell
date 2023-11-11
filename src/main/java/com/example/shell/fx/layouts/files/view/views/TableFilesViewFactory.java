package com.example.shell.fx.layouts.files.view.views;

import com.example.shell.fx.layouts.files.view.FilesViewFactory;
import com.example.shell.fx.layouts.files.view.views.Impl.TableFilesView;
import com.example.shell.fx.layouts.files.view.views.Impl.FilesView;

public class TableFilesViewFactory extends FilesViewFactory {
    @Override
    public FilesView createFilesView() {
        return new TableFilesView();
    }
}
