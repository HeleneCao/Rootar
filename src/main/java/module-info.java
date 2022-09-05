module com.rootar.rootar {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.rootar to javafx.fxml;
    exports com.rootar;
}