package com.example.shell;

import com.example.shell.models.Disk;
import com.example.shell.models.FileManager.FileManager;
import com.example.shell.models.FileManager.State.States.CopyState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;

import java.io.File;

public class AppController {
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
        Disk selectedDrive = new Disk(directoryChooser.showDialog(null));
        FileManager.setCurrentDisk(selectedDrive);
    }

    @FXML
    private void selectDestinationPathButtonClicked() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Оберіть пункт призначення");
        File selectedDestinationPath = directoryChooser.showDialog(null);
        if (selectedDestinationPath != null) {
            FileManager.setDestination(selectedDestinationPath);
        }
    }

    @FXML
    private void copyButtonClicked() {
        fileManager.setState(new CopyState());
        fileManager.perform();
    }
}