package com.example.fxshell.app.views;

import javafx.scene.Node;

import java.io.File;

public interface FilesView<T extends Node> {
    T getNode();

    void setFiles(File[] files);

    File getSelectedItem();

    String getName();
}
