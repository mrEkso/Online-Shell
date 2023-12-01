package com.example.fxshell;

import com.example.fxshell.controllers.http.HttpController;
import com.example.fxshell.files.comboBoxes.ComboBoxTemplate;
import com.example.fxshell.files.comboBoxes.Impl.DiskComboBoxTemplate;
import com.example.fxshell.files.comboBoxes.Impl.ViewComboBoxTemplate;
import com.example.fxshell.files.filters.FileFilterExpression;
import com.example.fxshell.files.filters.Impl.ExtensionFilterExpression;
import com.example.fxshell.files.filters.Impl.FileFilter;
import com.example.fxshell.files.filters.Impl.NameFilterExpression;
import com.example.fxshell.files.views.FilesViewFactory;
import com.example.fxshell.files.views.FilesViewTab;
import com.example.fxshell.files.views.Impl.FilesView;
import com.example.fxshell.files.views.Impl.FilesViewType;
import com.example.fxshell.files.views.ListFilesViewFactory;
import com.example.fxshell.files.views.TableFilesViewFactory;
import com.example.fxshell.models.Disk;
import com.example.fxshell.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends javafx.application.Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("shell.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(BootstrapFX.bootstrapFXStylesheet());
        stage.setTitle("Shell");
        stage.setScene(scene);
        stage.show();

        // Test prototype pattern
        User user1 = new User("Davyd", "david@gmail.com", "123123");
        User user2 = user1.clone();
        user2.setName("Vitaliy");
        user2.setEmail("vitaliy@gmail.com");

        // Test Template and Factory method patterns
        List<File> testFiles = Arrays.asList(
                new File("Folder1"),
                new File("Folder2"),
                new File("File1.txt"),
                new File("File2.txt"),
                new File("File3.txt")
        );

        List<Disk> testDisks = Arrays.asList(
                new Disk("C"),
                new Disk("D"),
                new Disk("E")
        );

        ComboBoxTemplate diskComboBoxTemplate = new DiskComboBoxTemplate();

        ComboBox<String> diskComboBox = diskComboBoxTemplate.createMenu(
                testDisks.stream().map(Disk::getName).toList());

        Tab diskTab = new Tab("Disk Menu");
        diskTab.setContent(diskComboBox);


        ComboBoxTemplate viewComboBoxTemplate = new ViewComboBoxTemplate();

        ComboBox<String> viewComboBox = viewComboBoxTemplate.createMenu(
                Arrays.stream(FilesViewType.values())
                        .map(Enum::name)
                        .collect(Collectors.toList()));

        Tab viewTab = new Tab("View Menu");
        BorderPane pane = new BorderPane();
        pane.setTop(viewComboBox);

        viewComboBox.setOnAction(event -> {
            String selectedView = viewComboBox.getValue();
            FilesViewType viewType = FilesViewType.valueOf(selectedView.toUpperCase());

            FilesViewFactory filesViewFactory;

            if (viewType == FilesViewType.LIST) {
                filesViewFactory = new ListFilesViewFactory();
            } else {
                filesViewFactory = new TableFilesViewFactory();
            }

            FilesView filesView = filesViewFactory.create();
            FilesViewTab.setFilesView(filesView);
            pane.setCenter(FilesViewTab.getFilesView().getNode());
        });

        final FilesViewFactory filesViewFactory;

        filesViewFactory = new ListFilesViewFactory();
        FilesView listView = filesViewFactory.create();

        FilesViewTab.setFilesView(listView);
        FilesViewTab.setFiles(testFiles);

        pane.setCenter(FilesViewTab.getFilesView().getNode());
        viewTab.setContent(pane);

        // Test Interpreter pattern
        TextField extensionTextField = new TextField();
        extensionTextField.setPromptText("Enter Extension");

        // Кнопка для застосування фільтрації
        Button applyFilterButton = new Button("Apply Extension Filter");
        applyFilterButton.setOnAction(event -> {
            String extension = extensionTextField.getText();
            if (!extension.isEmpty()) {
                FileFilterExpression extensionFilter = new ExtensionFilterExpression(extension);
                FileFilter.applyFilter((ListView<String>) FilesViewTab.getFilesView().getNode(), testFiles, extensionFilter);
            } else {
                FilesViewTab.setFiles(testFiles);
            }
        });

        TextField nameTextField = new TextField();
        nameTextField.setPromptText("Enter File Name");

        // Кнопка для застосування фільтрації за іменем файлу
        Button applyNameFilterButton = new Button("Apply Name Filter");
        applyNameFilterButton.setOnAction(event -> {
            String targetName = nameTextField.getText();
            if (!targetName.isEmpty()) {
                FileFilterExpression nameFilter = new NameFilterExpression(targetName);
                FileFilter.applyFilter((ListView<String>) FilesViewTab.getFilesView().getNode(), testFiles, nameFilter);
            } else {
                FilesViewTab.setFiles(testFiles);
            }
        });

        VBox root = new VBox(extensionTextField, applyFilterButton, nameTextField, applyNameFilterButton, FilesViewTab.getFilesView().getNode());
        viewTab.setContent(root);


        // Test Client-Server architecture
        HttpController httpController = new HttpController();
        Tab serverTab = new Tab("Server Menu");

        TextField loginField = new TextField();
        PasswordField passwordField = new PasswordField();
        Label messageLabel = new Label();
        Button registerButton = new Button("Register");
        registerButton.setOnAction(event -> messageLabel.setText(httpController.register(loginField.getText(), passwordField.getText())));
        Button loginButton = new Button("Login");
        loginButton.setOnAction(event -> messageLabel.setText(httpController.login(loginField.getText(), passwordField.getText())));

        VBox serverBox = new VBox(
                new Label("login:"),
                loginField,
                new Label("Password:"),
                passwordField,
                registerButton,
                loginButton,
                messageLabel
        );
        serverTab.setContent(serverBox);

        TabPane tabPane = new TabPane(diskTab, viewTab, serverTab);
        Scene testScene = new Scene(tabPane, 640, 480);
        stage.setScene(testScene);
        stage.show();
    }
}