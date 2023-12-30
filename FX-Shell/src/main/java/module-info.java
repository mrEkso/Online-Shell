module com.example.fxshell {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.desktop;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.io;
    requires java.net.http;
    requires com.google.gson;

    opens com.example.fxshell to javafx.fxml;
    exports com.example.fxshell;
    exports com.example.fxshell.app.history;
    opens com.example.fxshell.app.history to javafx.fxml;
    exports com.example.fxshell.app;
    opens com.example.fxshell.app to javafx.fxml;
    exports com.example.fxshell.http.controllers;
    opens com.example.fxshell.http.controllers to javafx.fxml;
    opens com.example.fxshell.models to com.google.gson;
}