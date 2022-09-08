module com.rootar.rootar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.rootar to javafx.fxml;
    exports com.rootar;
}