package com.example.fxshell.app.views.factory;

import com.example.fxshell.app.views.FilesView;
import com.example.fxshell.app.views.Impl.ListFilesView;
import javafx.scene.control.ListView;

import java.io.File;

public class ListFilesViewFactory extends FilesViewFactory<FilesView<ListView<File>>> {
    @Override
    public FilesView<ListView<File>> createFilesView() {
        return new ListFilesView();
    }
}
