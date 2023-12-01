package com.example.fxshell.files.views.Impl;

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
    public void addFiles(List<File> files) {
        node.getItems().clear();
        for (File file : files) {
            node.getItems().add(file.getName());
        }
    }

    public String getName() {
        return "List view";
    }
}
