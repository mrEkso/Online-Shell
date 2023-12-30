package com.example.fxshell.app.panes.FilesPane;

import com.example.fxshell.app.alerts.AlertUtils;
import com.example.fxshell.app.filters.FileFilterExpression;
import com.example.fxshell.app.filters.Impl.ExtensionFilterExpression;
import com.example.fxshell.app.filters.Impl.FileFilter;
import com.example.fxshell.app.filters.Impl.NameFilterExpression;
import com.example.fxshell.app.history.History;
import com.example.fxshell.app.comboBoxes.ComboBoxTemplate;
import com.example.fxshell.app.comboBoxes.Impl.DiskComboBoxTemplate;
import com.example.fxshell.app.comboBoxes.Impl.ViewComboBoxTemplate;
import com.example.fxshell.app.menus.FileContextMenu;
import com.example.fxshell.app.views.factory.FilesViewFactory;
import com.example.fxshell.app.views.FilesView;
import com.example.fxshell.app.views.factory.ListFilesViewFactory;
import com.example.fxshell.app.views.factory.TableFilesViewFactory;
import com.example.fxshell.http.controllers.HttpController;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FilesPane extends BorderPane implements Prototype<FilesPane> {
    private final History history;
    private FilesView<?> filesView;
    private final Button backButton;
    private final Button refreshButton;
    private final ComboBox<String> viewComboBox;
    private final ComboBox<String> diskComboBox;
    private final CheckBox showHiddenFilesCheckbox;
    private final Button uploadToCloudButton;
    TextField extensionTextField;
    Button applyExtensionFilterButton;
    TextField nameTextField;
    Button applyNameFilterButton;
    private FileContextMenu fileContextMenu;
    private final HttpController httpController;

    public FilesPane(HttpController httpController) {
        history = new History();
        viewComboBox = createViewComboBox(List.of("List", "Table"));
        diskComboBox = createDiskComboBox(File.listRoots());
        backButton = new Button("Назад");
        refreshButton = new Button("Оновити");
        uploadToCloudButton = new Button("Завантажити файл в хмару");
        showHiddenFilesCheckbox = new CheckBox("Hidden Files");
        extensionTextField = new TextField();
        applyExtensionFilterButton = new Button("Пошук по розширенню");
        nameTextField = new TextField();
        applyNameFilterButton = new Button("Пошук по імені");
        this.httpController = httpController;
        configure();
    }

    private void configure() {
        initFilesView();
        initUploadToCloudButton();
        setActions();

        setTop(createTopPanel());
        setCenter(getFilesList());
    }

    private HBox createTopPanel() {
        return new HBox(10, new VBox(new HBox(backButton, refreshButton, viewComboBox),
                new VBox(diskComboBox, showHiddenFilesCheckbox, uploadToCloudButton)),
                new VBox(extensionTextField, applyExtensionFilterButton, nameTextField, applyNameFilterButton));
    }

    private void initFilesView() {
        filesView = new ListFilesViewFactory().create();
        File disk = File.listRoots()[0];
        updateFilesList(history.navigateForward(disk));
    }

    private void initUploadToCloudButton() {
        uploadToCloudButton.setVisible(false);
    }

    private ComboBox<String> createViewComboBox(List<String> views) {
        ComboBoxTemplate template = new ViewComboBoxTemplate();
        return template.createMenu(views);
    }

    private ComboBox<String> createDiskComboBox(File[] disks) {
        ComboBoxTemplate template = new DiskComboBoxTemplate();
        return template.createMenu(List.of(Arrays.toString(disks)));
    }

    private void setActions() {
        getFilesList().setOnMouseClicked(this::handleFileClick);
        backButton.setOnAction(e -> exitFolder());
        refreshButton.setOnAction(e -> refreshFilesList());
        viewComboBox.setOnAction(this::handleViewChange);
        diskComboBox.getSelectionModel().selectedItemProperty().addListener(this::changeDisk);
        showHiddenFilesCheckbox.setOnAction(e -> updateFilesList(history.getFilesCurrentDirectory()));
        uploadToCloudButton.setOnAction(e -> handleUploadButton());
        applyExtensionFilterButton.setOnAction(event -> handleApplyExtensionFilterButton());
        applyNameFilterButton.setOnAction(event -> handleApplyNameFilterButton());
    }

    private void handleFileClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
            File selectedFile = getSelectedItem();
            if (selectedFile != null) {
                if (selectedFile.isDirectory()) openFolder(selectedFile);
                else openFile(selectedFile);
            }
        } else if (event.getButton() == MouseButton.SECONDARY) {
            fileContextMenu.show(getFilesList(), event.getScreenX(), event.getScreenY());
        }
    }

    private void handleViewChange(ActionEvent event) {
        FilesViewFactory<?> factory = viewComboBox.getValue().equals("List") ?
                new ListFilesViewFactory() : new TableFilesViewFactory();
        filesView = factory.create();
        refreshFilesList();
        getFilesList().setOnMouseClicked(this::handleFileClick);
        setCenter(filesView.getNode());
    }

    private void handleUploadButton() {
        File selectedFile = getSelectedItem();
        if (selectedFile == null) {
            AlertUtils.showErrorAlert("Виберіть, будь ласка, файл для завантаження.");
            return;
        }
        if (!selectedFile.isFile()) {
            AlertUtils.showErrorAlert("На жаль, папки не можна завантажувати.");
            return;
        }
        String response = httpController.uploadFile(selectedFile.toPath());

        if (response.contains("успішно")) {
            AlertUtils.showSuccessAlert(response);
        } else {
            AlertUtils.showErrorAlert(response);
        }
    }

    private void handleApplyExtensionFilterButton() {
        String extension = extensionTextField.getText();
        if (!extension.isEmpty()) {
            FileFilterExpression extensionFilter = new ExtensionFilterExpression(extension);
            FileFilter.applyFilter(filesView, history.getFilesCurrentDirectory(), extensionFilter);
        } else {
            refreshFilesList();
        }
    }

    private void handleApplyNameFilterButton() {
        String targetName = nameTextField.getText();
        if (!targetName.isEmpty()) {
            FileFilterExpression nameFilter = new NameFilterExpression(targetName);
            FileFilter.applyFilter(filesView, history.getFilesCurrentDirectory(), nameFilter);
        } else {
            refreshFilesList();
        }
    }

    public void openFolder(File directory) {
        updateFilesList(history.navigateForward(directory));
    }

    public void exitFolder() {
        updateFilesList(history.navigateBack());
    }

    private void openFile(File file) {
        if (file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                AlertUtils.showErrorAlert("Не вдалося відкрити файл");
            }
        }
    }

    private void updateFilesList(File[] files) {
        if (files != null) {
            List<File> filteredFiles = Arrays.stream(files)
                    .filter(file -> showHiddenFilesCheckbox.isSelected() || !file.isHidden())
                    .sorted((f1, f2) -> f1.getName().compareToIgnoreCase(f2.getName()))
                    .toList();
            filesView.setFiles(filteredFiles.toArray(new File[0]));
        }
    }

    private void changeDisk(ObservableValue<? extends String> obs, String oldVal, String newVal) {
        if (newVal != null && !newVal.equals(oldVal)) {
            File newDisk = new File(newVal);
            if (newDisk.exists() && newDisk.isDirectory()) {
                updateFilesList(newDisk.listFiles());
                history.clear();
            }
        }
    }

    public void refreshFilesList() {
        updateFilesList(history.getFilesCurrentDirectory());
    }

    public void showUploadButton() {
        uploadToCloudButton.setVisible(true);
    }

    public Node getFilesList() {
        return filesView.getNode();
    }

    public File getSelectedItem() {
        return filesView.getSelectedItem();
    }

    public File getCurrentDirectory() {
        return history.getCurrentDirectory();
    }

    public void setFileContextMenu(FileContextMenu fileContextMenu) {
        this.fileContextMenu = fileContextMenu;
    }

    @Override
    public FilesPane clone() {
        return new FilesPane(httpController);
    }
}
