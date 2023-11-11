package com.example.shell.fx.layouts.files.view.views.Impl;

import javafx.scene.Node;
import javafx.scene.control.ListView;

import java.io.File;
import java.util.List;

public class ListFilesView implements FilesView {
    private final ListView<String> node = new ListView<>();

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public void display(List<File> files) {
        node.getItems().clear();
        for (File file : files) {
            node.getItems().add(file.getName());
        }
    }
}
