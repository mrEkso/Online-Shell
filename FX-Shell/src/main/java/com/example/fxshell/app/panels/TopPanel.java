package com.example.fxshell.app.panels;

import com.example.fxshell.app.dialogs.AuthDialog;
import com.example.fxshell.app.dialogs.MyFilesDialog;
import com.example.fxshell.http.controllers.HttpController;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.io.File;
import java.util.function.Supplier;

public class TopPanel {
    private final HttpController httpController;
    private final Button authButton;
    private final Button myFilesButton;
    private AuthDialog authDialog;
    private MyFilesDialog myFilesDialog;
    private Runnable onAuthSuccess;
    private Runnable onFileDownload;
    private final Supplier<File> currentDirectorySupplier;

    public TopPanel(HttpController httpController, Supplier<File> currentDirectorySupplier) {
        this.httpController = httpController;
        this.currentDirectorySupplier = currentDirectorySupplier;
        authButton = new Button("Авторизуватися");
        myFilesButton = new Button("Мої файли");
        configure();
    }

    private void configure() {
        initDialogs();
        initMyFilesButton();
        setActions();
    }

    private void initDialogs() {
        authDialog = new AuthDialog(httpController, success -> {
            if (success && onAuthSuccess != null) {
                onAuthSuccess.run();
            }
        });
        myFilesDialog = new MyFilesDialog(httpController, currentDirectorySupplier, download -> {
            if (download && onFileDownload != null) {
                onFileDownload.run();
            }
        });
    }

    private void initMyFilesButton() {
        myFilesButton.setVisible(false);
    }

    private void setActions() {
        authButton.setOnAction(e -> authDialog.show());
    }

    public HBox init() {
        return new HBox(authButton, myFilesButton);
    }

    public void showMyFilesButton() {
        myFilesButton.setVisible(true);
        myFilesButton.setOnAction(e -> myFilesDialog.show());
    }

    public void setOnAuthSuccess(Runnable onAuthSuccess) {
        this.onAuthSuccess = onAuthSuccess;
    }

    public void setOnFileDownload(Runnable onFileDownload) {
        this.onFileDownload = onFileDownload;
    }
}
