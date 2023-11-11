package com.example.shell.fx.layouts.files.view.views.Impl;

import javafx.scene.Node;

import java.io.File;
import java.util.List;

public interface FilesView {
    Node getNode();

    void display(List<File> files);
}
