package com.example.shell.fx.layouts.files.views;

import com.example.shell.fx.layouts.files.views.Impl.TableFilesView;
import com.example.shell.fx.layouts.files.views.Impl.FilesView;

public class TableFilesViewFactory extends FilesViewFactory {
    @Override
    public FilesView createFilesView() {
        return new TableFilesView();
    }
}
