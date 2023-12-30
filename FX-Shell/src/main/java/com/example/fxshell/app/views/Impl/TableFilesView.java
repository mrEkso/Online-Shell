package com.example.fxshell.app.views.Impl;

import com.example.fxshell.app.views.FilesView;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.File;

public class TableFilesView implements FilesView<ScrollPane> {
    private static final double TILE_SPACING = 10.0;
    private static final double MAX_LABEL_WIDTH = 150;

    private final ScrollPane scrollPane;
    private final TilePane tilePane;
    private final ObjectProperty<File> selectedFile = new SimpleObjectProperty<>();

    public TableFilesView() {
        tilePane = new TilePane(TILE_SPACING, TILE_SPACING);
        tilePane.setPadding(new Insets(TILE_SPACING));

        scrollPane = new ScrollPane(tilePane);
        configure();
        scrollPane.setFitToWidth(true);
    }

    private void configure() {
        tilePane.setPrefColumns(3);
        tilePane.setPrefRows(3);
        tilePane.setTileAlignment(Pos.CENTER);
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        tilePane.setPadding(new Insets(10));
        tilePane.setStyle("-fx-background-color: #D0D0D0;");

        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setStyle("-fx-background-color: #D0D0D0;");
    }


    @Override
    public ScrollPane getNode() {
        return scrollPane;
    }

    @Override
    public void setFiles(File[] files) {
        tilePane.getChildren().clear();
        for (File file : files) {
            Label label = createStyledLabel(file);
            label.setOnMouseClicked(event -> selectedFile.set(file));
            tilePane.getChildren().add(label);
        }
    }

    private Label createStyledLabel(File file) {
        Label label = new Label(file.getName());
        label.setPadding(new Insets(8));
        label.setMaxWidth(MAX_LABEL_WIDTH);
        label.setAlignment(Pos.CENTER);

        // Initial styling
        label.setStyle("-fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: #F0F0F0;");

        // Hover effect
        label.setOnMouseEntered(e -> label.setStyle("-fx-border-color: darkgray; -fx-border-width: 2; -fx-background-color: #E8E8E8; -fx-padding: 7;"));
        label.setOnMouseExited(e -> label.setStyle("-fx-border-color: lightgray; -fx-border-width: 1; -fx-background-color: #F0F0F0; -fx-padding: 8;"));

        return label;
    }

    @Override
    public File getSelectedItem() {
        return selectedFile.get();
    }

    public String getName() {
        return "Table view";
    }
}
