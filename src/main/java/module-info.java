module com.rootar.rootar {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.controlsfx.controls;


    opens com.rootar to javafx.fxml;
    exports com.rootar;
}