package com.example.fxshell.app.panes;

import com.example.fxshell.app.menus.FilePaneMenuManager;
import com.example.fxshell.app.panes.FilesPane.FilesPane;
import com.example.fxshell.http.controllers.HttpController;
import javafx.scene.control.SplitPane;

import java.io.File;

public class ContentPane {
    private FilesPane leftPane;
    private FilesPane rightPane;
    private final HttpController httpController;


    public ContentPane(HttpController httpController) {
        this.httpController = httpController;
        initPanes();
    }

    private void initPanes() {
        leftPane = new FilesPane(httpController);
        rightPane = leftPane.clone();
        new FilePaneMenuManager(leftPane, rightPane).initPanes();
    }

    public SplitPane init() {
        SplitPane splitPane = new SplitPane();
        splitPane.getItems().addAll(leftPane, rightPane);
        splitPane.setDividerPositions(0.5);
        return splitPane;
    }

    public File getCurrentDirectoryLeftPane() {
        return leftPane.getCurrentDirectory();
    }

    public void showUploadButtons() {
        leftPane.showUploadButton();
        rightPane.showUploadButton();
    }

    public void refreshFilesPane() {
        leftPane.refreshFilesList();
        rightPane.refreshFilesList();
    }
}
