package com.example.fxshell.app.views.factory;

import com.example.fxshell.app.views.Impl.TableFilesView;

public class TableFilesViewFactory extends FilesViewFactory<TableFilesView> {
    @Override
    public TableFilesView createFilesView() {
        return new TableFilesView();
    }
}
