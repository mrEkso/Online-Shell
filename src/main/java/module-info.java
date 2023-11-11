module com.example.shellfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires org.apache.commons.io;

    opens com.example.shell to javafx.fxml;
    exports com.example.shell;
}