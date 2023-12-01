module com.example.fxshell {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.io;
    requires java.net.http;
    requires com.google.gson;

    opens com.example.fxshell to javafx.fxml;
    exports com.example.fxshell;
    exports com.example.fxshell.controllers;
    opens com.example.fxshell.controllers to javafx.fxml;
    exports com.example.fxshell.controllers.http;
    opens com.example.fxshell.controllers.http to javafx.fxml;
}