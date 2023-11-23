package com.example.shell.fx.layouts.files.views.Impl;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.io.File;
import java.util.List;
import java.util.stream.IntStream;

public class TableFilesView implements FilesView {
    private final VBox node = new VBox();
    private final int filesPerRow = 4;

    @Override
    public Node getNode() {
        return node;
    }

    @Override
    public void addFiles(List<File> files) {
        // Очищуємо дочірні елементи поточного вузла (VBox)
        node.getChildren().clear();

        // Створюємо вертикальний контейнер для групування рядків
        final HBox[] rowBox = {new HBox()};
        rowBox[0].setSpacing(10); // Відступ між рядками

        // Створюємо горизонтальний контейнер для групування блоків у кожному рядку
        VBox columnBox = new VBox();
        columnBox.setSpacing(10); // Відступ між блоками в рядку

        // Проходимо по списку файлів
        IntStream.range(0, files.size()).forEach(i -> {
            File file = files.get(i);

            // Створюємо блок для файлу і додаємо його в поточний рядок
            rowBox[0].getChildren().add(createFileBlockFromFile(file));

            // Якщо досягли кінця рядку або останнього елемента,
            // додаємо рядок у вертикальний контейнер
            if ((i + 1) % filesPerRow == 0 || i == files.size() - 1) {
                columnBox.getChildren().add(rowBox[0]); // Додаємо рядок у контейнер рядків
                rowBox[0] = new HBox(); // Створюємо новий рядок
                rowBox[0].setSpacing(10); // Встановлюємо відступ для нового рядка
            }
        });

        // Додаємо вертикальний контейнер у основний контейнер
        node.getChildren().add(columnBox);
    }

    private Region createFileBlockFromFile(File file) {
        // Створюємо горизонтальний контейнер для блоку файлу
        HBox fileBlock = new HBox();
        fileBlock.setMinSize(0, 0); // Встановлюємо мінімальний розмір блоку
        HBox.setHgrow(fileBlock, Priority.ALWAYS); // Встановлюємо горизонтальне розширення блоку

        // Створюємо напис з ім'ям файлу і додаємо його в блок
        Label label = new Label(file.getName());
        label.setPadding(new Insets(5)); // Встановлюємо внутрішній відступ для напису

        fileBlock.getChildren().add(label); // Додаємо напис в блок
        return fileBlock;  // Повертаємо створений блок
    }

    public String getName() {
        return "Table view";
    }
}
