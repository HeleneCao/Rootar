package com.rootar;

import com.rootar.metier.Evenements;
import com.rootar.metier.Pays;
import com.rootar.metier.Region;
import com.rootar.metier.Ville;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

import java.util.ArrayList;

public class DetailsPLusController {

    private MenuApp menuApp;
    private ServiceRootar serviceRootar;
    private StringBuilder fieldArea;

    @FXML
    private ListView <Region> region;
    @FXML
    private ListView <Ville> ville;
    @FXML
    private ListView <Evenements> event;
    @FXML
    private TextArea detailsEvent;
    @FXML
    private TextArea detailsDC;
    @FXML
    private TextArea detailsTypeC;
    @FXML
    private void initialize(){
        serviceRootar = new ServiceRootar();
        fieldArea = new StringBuilder("");

    }
    public void afficherRegion(Pays paysSelected){

        region.setItems(FXCollections.observableArrayList(serviceRootar.getRegionFilterByPays(paysSelected)));
        region.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> afficherVilles( newValue));
        region.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> afficherTypeClimat(newValue));
        region.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> afficherDonneesClimat(newValue));

    }
    public void afficherVilles(Region regionSelected){

       ville.setItems(FXCollections.observableArrayList(serviceRootar.getVilleFilterByRegion(regionSelected)));
       ville.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> afficherEvent(newValue));
    }
    public void afficherEvent(Ville villeSelected){

        event.setItems(FXCollections.observableArrayList(serviceRootar.getEventFilterByVille(villeSelected)));
        event.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> detailsEvent(newValue));

    }
    public void detailsEvent(Evenements event){


        System.out.println(event.getDateDebutEvenements());
        fieldArea.append(event.getDateDebutEvenements()+"\n");
        fieldArea.append(event.getDateFinEvenements()+"\n");
        fieldArea.append(event.getDescriptionEvenements()+"\n");
        detailsEvent.setText(fieldArea.toString());
    }
    public void afficherTypeClimat(Region region){

        detailsTypeC.setText(serviceRootar.getTypeClimatFilterByRegion(region).getLibelleTypeClimat());

    }
    public void afficherDonneesClimat(Region region){

       StringBuilder fieldArea = new StringBuilder("");
        for(int i = 0 ;i<serviceRootar.getDonneesClimatByRegion(region).size();i++) {
            //fieldArea.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getMois()+ "\n");
            fieldArea.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getLibelleMois().toString()+ "\n");
            fieldArea.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getTemperatureMin()+ "\n");
            fieldArea.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getTemperatureMax()+ "\n");
            fieldArea.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getTemperatureMoy()+ "\n");
            fieldArea.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getTauxHumidite()+ "\n");
        }
        detailsDC.setText(fieldArea.toString());
    }
    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }
}
