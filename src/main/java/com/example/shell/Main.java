package com.example.shell;

import com.example.shell.fx.layouts.files.view.views.FilesViewTab;
import com.example.shell.fx.layouts.files.view.views.FilesViewFactory;
import com.example.shell.fx.layouts.files.view.comboBoxes.Impl.ViewComboBoxTemplate;
import com.example.shell.fx.layouts.files.view.views.Impl.FilesView;
import com.example.shell.fx.layouts.files.view.views.ListFilesViewFactory;
import com.example.shell.fx.layouts.files.view.views.TableFilesViewFactory;
import com.example.shell.fx.layouts.files.view.comboBoxes.ComboBoxTemplate;
import com.example.shell.fx.layouts.files.view.comboBoxes.Impl.DiskComboBoxTemplate;
import com.example.shell.fx.layouts.files.view.views.Impl.FilesViewType;
import com.example.shell.models.Disk;
import com.example.shell.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
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

        // Test Template and Factory method
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

        FilesViewTab.setFiles(testFiles);
        FilesViewTab.setFilesView(listView);

        pane.setCenter(FilesViewTab.getFilesView().getNode());
        viewTab.setContent(pane);

        TabPane tabPane = new TabPane(diskTab, viewTab);
        Scene testScene = new Scene(tabPane, 640, 480);
        stage.setScene(testScene);
        stage.show();
    }
}