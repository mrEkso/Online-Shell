package com.example.fxshell;

import com.example.fxshell.app.ShellUI;
import com.example.fxshell.http.controllers.HttpController;
import javafx.stage.Stage;

public class Main extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        HttpController httpController = new HttpController();
        ShellUI shellUI = new ShellUI(primaryStage, httpController);
        shellUI.init();
    }
}