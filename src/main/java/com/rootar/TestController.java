package com.rootar;


import com.rootar.metier.Pays;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import org.controlsfx.control.WorldMapView;

public class TestController {
    private Stage dialogStage;
    private ServiceRootar serviceRootar;
    private WorldMapView mapView;
    @FXML
    public void initialize(){
        serviceRootar= new ServiceRootar();


    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage=dialogStage;
    }
}
