package com.example.shell.fx.layouts.files.views.Impl;

import javafx.scene.Node;

import java.io.File;
import java.util.List;

public interface FilesView {
    Node getNode();

    void addFiles(List<File> files);

    String getName();
}
