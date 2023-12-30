package com.example.fxshell.app.views.Impl;

import com.example.fxshell.app.views.FilesView;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.File;

public class ListFilesView implements FilesView<ListView<File>> {
    private final ListView<File> node;

    public ListFilesView() {
        node = new ListView<>();
        setCellFactory();
    }

    private void setCellFactory() {
        node.setCellFactory(lv -> new ListCell<File>() {
            @Override
            protected void updateItem(File file, boolean empty) {
                super.updateItem(file, empty);
                if (empty || file == null) {
                    setText(null);
                } else {
                    setText(file.getName());
                }
            }
        });
    }

    @Override
    public ListView<File> getNode() {
        return node;
    }

    @Override
    public void setFiles(File[] files) {
        node.getItems().setAll(files);
    }

    @Override
    public File getSelectedItem() {
        return node.getSelectionModel().getSelectedItem();
    }

    public String getName() {
        return "List view";
    }
}
