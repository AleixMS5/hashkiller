module com.example.hashkiller {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.hashkiller to javafx.fxml;
    exports com.example.hashkiller;
}