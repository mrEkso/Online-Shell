package com.example.fxshell.app.menus;

import com.example.fxshell.app.panes.FilesPane.FilesPane;
import com.example.fxshell.models.FileManager.FileManagerOperations;
import com.example.fxshell.models.FileManager.FileManagerOperationsImpl;

public class FilePaneMenuManager {
    private final FileManagerOperations fileManagerOperations;
    private final FilesPane leftPane, rightPane;

    public FilePaneMenuManager(FilesPane leftPane, FilesPane rightPane) {
        fileManagerOperations = new FileManagerOperationsImpl();
        this.leftPane = leftPane;
        this.rightPane = rightPane;
    }

    public void initPanes() {
        setupContextMenu(leftPane, rightPane);
        setupContextMenu(rightPane, leftPane);
    }

    private void setupContextMenu(FilesPane primaryPane, FilesPane secondaryPane) {
        FileContextMenu contextMenu = new FileContextMenu();
        contextMenu.setOnCopyAction(event -> copy(primaryPane, secondaryPane));
        contextMenu.setOnMoveAction(event -> move(primaryPane, secondaryPane));
        contextMenu.setOnDeleteAction(event -> delete(primaryPane));
        primaryPane.setFileContextMenu(contextMenu);
    }

    private void copy(FilesPane primaryPane, FilesPane secondaryPane) {
        fileManagerOperations.copy(primaryPane.getSelectedItem(), secondaryPane.getCurrentDirectory());
        primaryPane.refreshFilesList();
        secondaryPane.refreshFilesList();
    }

    private void move(FilesPane primaryPane, FilesPane secondaryPane) {
        fileManagerOperations.move(primaryPane.getSelectedItem(), secondaryPane.getCurrentDirectory());
        primaryPane.refreshFilesList();
        secondaryPane.refreshFilesList();
    }

    private void delete(FilesPane primaryPane) {
        fileManagerOperations.delete(primaryPane.getSelectedItem());
        primaryPane.refreshFilesList();
    }
}