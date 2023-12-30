package com.example.fxshell.app.dialogs;

import com.example.fxshell.app.alerts.AlertUtils;
import com.example.fxshell.http.controllers.HttpController;
import com.example.fxshell.models.MyFile;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MyFilesDialog {
    Dialog<File> dialog;
    private final ListView<String> filesListView;
    private final Button downloadButton;
    private final Supplier<File> currentDirectorySupplier;
    private final Consumer<Boolean> onFileDownload;
    private final HttpController httpController;

    public MyFilesDialog(HttpController httpController, Supplier<File> currentDirectorySupplier, Consumer<Boolean> onFileDownload) {
        filesListView = new ListView<>();
        downloadButton = new Button("Завантажити в директорію лівої панелі");
        this.httpController = httpController;
        this.currentDirectorySupplier = currentDirectorySupplier;
        this.onFileDownload = onFileDownload;
        configure();
    }

    private void configure() {
        setupDialog();
        setActions();
    }

    private void setupDialog() {
        dialog = new Dialog<>();
        dialog.setTitle("Мої файли в хмарі");
        dialog.getDialogPane().setContent(new HBox(filesListView, downloadButton));
        ButtonType cancelButtonType = new ButtonType("Скасувати", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(cancelButtonType);
    }

    private void setActions() {
        downloadButton.setOnAction(event -> handleDownloadAction());
    }

    private void handleDownloadAction() {
        try {
            String selectedFileId = findFileIdByName(httpController.getMyFiles(), getSelectedFile());
            if (selectedFileId == null) throw new IOException("Виберіть файл для завантаження.");

            Path filePath = httpController.downloadFile(selectedFileId, getSelectedFile(), currentDirectorySupplier.get());
            if (filePath == null) throw new IOException("Не вдалося завантажити файл.");

            onFileDownload.accept(true);
            AlertUtils.showSuccessAlert("Файл успішно завантажено.");
        } catch (IOException e) {
            onFileDownload.accept(false);
            AlertUtils.showErrorAlert(e.getMessage());
        }
    }

    private void updateFilesView() {
        filesListView.getItems().setAll(Arrays.stream(httpController.getMyFiles())
                .map(MyFile::getName).toList());
    }

    public void show() {
        updateFilesView();
        dialog.showAndWait();
    }

    private String getSelectedFile() {
        return filesListView.getSelectionModel().getSelectedItem();
    }

    public String findFileIdByName(MyFile[] files, String fileName) {
        return Arrays.stream(files)
                .filter(file -> file.getName().equals(fileName))
                .findFirst()
                .map(MyFile::getId)
                .orElse(null);
    }
}
