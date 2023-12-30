package com.example.fxshell.app.menus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

public class FileContextMenu extends ContextMenu {
    MenuItem copyMenuItem;
    MenuItem moveMenuItem;
    MenuItem deleteMenuItem;

    public FileContextMenu() {
        this.copyMenuItem = new MenuItem("Копіювати");
        this.moveMenuItem = new MenuItem("Перемістити");
        this.deleteMenuItem = new MenuItem("Видалити");
        addItems();
    }

    private void addItems() {
        this.getItems().addAll(copyMenuItem, moveMenuItem, deleteMenuItem);
    }

    public void setOnCopyAction(EventHandler<ActionEvent> action) {
        copyMenuItem.setOnAction(action);
    }

    public void setOnMoveAction(EventHandler<ActionEvent> action) {
        moveMenuItem.setOnAction(action);
    }

    public void setOnDeleteAction(EventHandler<ActionEvent> action) {
        deleteMenuItem.setOnAction(action);
    }
}
