module com.example.demo1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;

    requires java.sql;
    requires lombok;
    requires com.google.zxing;
    requires com.google.zxing.javase;
    requires kernel;
    requires io;
    requires layout;

    opens com.example.demo1 to javafx.fxml;

}