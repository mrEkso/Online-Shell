package com.example.fxshell.app;

import com.example.fxshell.app.panels.TopPanel;
import com.example.fxshell.app.panes.ContentPane;
import com.example.fxshell.http.controllers.HttpController;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;

public class ShellUI {
    private final Stage stage;
    private final ContentPane contentPane;
    private final TopPanel topPanel;

    public ShellUI(Stage stage, HttpController httpController) {
        this.stage = stage;
        contentPane = new ContentPane(httpController);
        topPanel = new TopPanel(httpController, this::getCurrentDirectory);
        setupTopPanel();
    }

    private void setupTopPanel() {
        topPanel.setOnAuthSuccess(this::showAuthButtons);
        topPanel.setOnFileDownload(this::refreshContent);
    }

    private void showAuthButtons() {
        contentPane.showUploadButtons();
        topPanel.showMyFilesButton();
    }

    private void refreshContent() {
        contentPane.refreshFilesPane();
    }

    private File getCurrentDirectory() {
        return contentPane.getCurrentDirectoryLeftPane();
    }

    public void init() {
        BorderPane root = new BorderPane();

        root.setTop(topPanel.init());
        root.setCenter(contentPane.init());

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Shell");
        stage.setScene(scene);
        stage.show();
    }
}
