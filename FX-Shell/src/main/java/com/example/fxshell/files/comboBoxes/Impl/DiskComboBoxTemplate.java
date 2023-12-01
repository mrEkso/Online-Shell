package com.example.fxshell.files.comboBoxes.Impl;

import com.example.fxshell.files.comboBoxes.ComboBoxTemplate;
import com.example.fxshell.models.FileManager.FileManager;
import javafx.scene.control.ComboBox;

import java.util.List;

public class DiskComboBoxTemplate extends ComboBoxTemplate {
    @Override
    protected ComboBox<String> createBaseMenu(List<String> disks) {
        ComboBox<String> diskMenu = new ComboBox<>();
        diskMenu.getItems().addAll(disks);
        diskMenu.setValue(disks.get(0));
        return diskMenu;
    }

    // Надаємо додаткові налаштування для меню вибору диска
    @Override
    protected void customizeMenu(ComboBox<String> menu) {
        menu.setPromptText("Select Disk");

        menu.setOnAction(event -> {
            String selectedDisk = menu.getValue();
            FileManager.setCurrentDisk(FileManager.findDiskByName(selectedDisk));
        });
    }
}
