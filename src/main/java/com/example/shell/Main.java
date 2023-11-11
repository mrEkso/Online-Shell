package com.example.shell;

import com.example.shell.fx.layouts.files.view.FilesViewFactory;
import com.example.shell.fx.layouts.files.view.views.Impl.FilesView;
import com.example.shell.fx.layouts.files.view.views.ListFilesViewFactory;
import com.example.shell.fx.layouts.files.view.views.TableFilesViewFactory;
import com.example.shell.models.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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

        // Test factory method
        FilesViewFactory filesViewFactory;

        List<File> testFiles = Arrays.asList(
                new File("Folder1"),
                new File("Folder2"),
                new File("File1.txt"),
                new File("File2.txt"),
                new File("File3.txt")
        );

        filesViewFactory = new ListFilesViewFactory();
        FilesView listView = filesViewFactory.create();
        Tab listViewTab = createFilesViewTab("List View", listView.getNode());
        listView.display(testFiles);

        filesViewFactory = new TableFilesViewFactory();
        FilesView tableView = filesViewFactory.create();
        Tab tableViewTab = createFilesViewTab("Table View", tableView.getNode());
        tableView.display(testFiles);

        TabPane tabPane = new TabPane(listViewTab, tableViewTab);
        Scene testScene = new Scene(tabPane, 640, 480);
        stage.setScene(testScene);
        stage.show();
    }

    private Tab createFilesViewTab(String title, Node node) {
        Tab tab = new Tab(title);
        BorderPane pane = new BorderPane();
        pane.setCenter(node);
        tab.setContent(pane);
        return tab;
    }
}