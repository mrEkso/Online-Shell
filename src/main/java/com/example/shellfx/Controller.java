package com.example.shellfx;

import com.example.shellfx.models.Disk;
import com.example.shellfx.models.FileManager.FileManager;
import com.example.shellfx.models.FileManager.State.Impl.CopyState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class Controller {
    private final FileManager fileManager = new FileManager();

    @FXML
    private Button selectDestinationPathButton;
    @FXML
    private Button selectDriveButton;

    @FXML
    private Button copyButton;

    @FXML
    private void selectDriveButtonClicked() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Оберіть диск");
        Disk selectedDrive = new Disk(directoryChooser.showDialog(null).getPath());
        fileManager.setCurrentDisk(selectedDrive);
    }

    @FXML
    private void selectDestinationPathButtonClicked() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Оберіть пункт призначення");
        File selectedDestinationPath = directoryChooser.showDialog(null);
        if (selectedDestinationPath != null) {
            fileManager.setDestination(selectedDestinationPath);
        }
    }

    @FXML
    private void copyButtonClicked() {
        fileManager.setState(new CopyState());
        fileManager.perform();
    }
}