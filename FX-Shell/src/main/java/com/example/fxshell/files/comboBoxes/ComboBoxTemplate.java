package com.example.fxshell.files.comboBoxes;

import javafx.scene.control.ComboBox;

import java.util.List;

public abstract class ComboBoxTemplate {
    // Шаблонний метод
    public ComboBox<String> createMenu(List<String> menuItems) {
        ComboBox<String> menu = createBaseMenu(menuItems);
        customizeMenu(menu);
        return menu;
    }

    // Абстрактний метод, який потрібно реалізувати в підкласах
    protected abstract ComboBox<String> createBaseMenu(List<String> menuItems);

    // Абстрактний метод для налаштування меню
    protected abstract void customizeMenu(ComboBox<String> menu);
}
