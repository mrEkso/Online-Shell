package com.example.fxshell.app.dialogs;

import com.example.fxshell.app.alerts.AlertUtils;
import com.example.fxshell.http.controllers.HttpController;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;

import java.util.function.Consumer;

public class AuthDialog {
    private Dialog<Boolean> dialog;
    private final TextField loginField;
    private final PasswordField passwordField;
    private final Button registerButton;
    private final Button loginButton;
    private final Consumer<Boolean> onAuthSuccess;
    private final HttpController httpController;

    public AuthDialog(HttpController httpController, Consumer<Boolean> onAuthSuccess) {
        this.loginField = new TextField();
        this.passwordField = new PasswordField();
        this.registerButton = new Button("Зареєструватися");
        this.loginButton = new Button("Ввійти");
        this.httpController = httpController;
        this.onAuthSuccess = onAuthSuccess;
        configure();
    }

    private void configure() {
        setupDialog();
        setActions();
    }

    private void setupDialog() {
        dialog = new Dialog<>();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Авторизація");

        loginField.setPromptText("Введіть email");
        passwordField.setPromptText("Введіть пароль");

        ButtonType cancelButtonType = new ButtonType("Скасувати", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(cancelButtonType);

        VBox content = new VBox(10, loginField, passwordField);
        dialog.getDialogPane().setContent(content);

        content.getChildren().addAll(loginButton, registerButton);
    }

    private void setActions() {
        registerButton.setOnAction(event -> processAuth(httpController.register(loginField.getText(), passwordField.getText())));
        loginButton.setOnAction(event -> processAuth(httpController.login(loginField.getText(), passwordField.getText())));
    }

    private void processAuth(String response) {
        if (response.contains("успішно")) { // Check for success in the response
            AlertUtils.showSuccessAlert(response);
            onAuthSuccess.accept(true);
            dialog.close();
        } else {
            AlertUtils.showErrorAlert(response);
        }
    }

    public void show() {
        dialog.showAndWait();
    }
}
