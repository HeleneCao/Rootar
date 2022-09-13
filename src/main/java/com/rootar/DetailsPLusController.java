package com.rootar;

import com.rootar.metier.*;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class DetailsPLusController {

    private MenuApp menuApp;
    private ServiceRootar serviceRootar;
    private StringBuilder fieldArea;
    private Stage dialogStage;
    public Ville villeSelected;
    public Pays paysSelected;
    public Evenements eventSelected;

    private FenetreAlert fenetreAlert;


    @FXML
    private ListView<Region> region;
    @FXML
    private ListView<Ville> ville;
    @FXML
    private ListView<Evenements> event;
    @FXML
    private TextArea detailsEvent;
    @FXML
    private TextArea detailsDC;
    @FXML
    private TextArea detailsTypeC;
    @FXML
    private TextArea detailsRepEtr;
    @FXML
    private ListView listeThemes;
    @FXML
    private ListView<Objet> listeObjets;
    @FXML
    private ListView<RepresentationEtrangere> listeRepEtr;

    @FXML
    private TextArea detailsCategories;

    @FXML
    private void initialize() {
        serviceRootar = new ServiceRootar();
        fieldArea = new StringBuilder("");
        fenetreAlert = new FenetreAlert();
    }

    public void afficherRegion(Pays paysSelected) {

        region.setItems(FXCollections.observableArrayList(serviceRootar.getRegionFilterByPays(paysSelected)));
        region.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherVilles(newValue));
        region.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherTypeClimat(newValue));
        region.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherDonneesClimat(newValue));
        region.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherThemesByRegion(newValue));
        afficherObjet(paysSelected);

    }

    public void afficherVilles(Region regionSelected) {

        ville.setItems(FXCollections.observableArrayList(serviceRootar.getVilleFilterByRegion(regionSelected)));
        ville.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherEvent(newValue));
        ville.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherRepEtrByVille(newValue));
        ville.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setVilleSelected(newValue));

    }

    public void setVilleSelected(Ville ville) {
        villeSelected = ville;
    }

    public Ville getVilleSelected() {
        return villeSelected;
    }

    public void setEventSelected(Evenements eventSelected) {
        this.eventSelected = eventSelected;
    }

    public void afficherEvent(Ville villeSelected) {

        event.setItems(FXCollections.observableArrayList(serviceRootar.getEventFilterByVille(villeSelected)));
        event.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> detailsEvent(newValue));

    }

    public void detailsEvent(Evenements event) {
        setEventSelected(event);

        fieldArea.append(event.getDateDebutEvenements() + "\n");
        fieldArea.append(event.getDateFinEvenements() + "\n");
        fieldArea.append(event.getDescriptionEvenements() + "\n");
        detailsEvent.setText(fieldArea.toString());
    }

    public void afficherTypeClimat(Region region) {
        detailsTypeC.setText(serviceRootar.getTypeClimatFilterByRegion(region).getLibelleTypeClimat());
    }

    public void afficherDonneesClimat(Region region) {

        StringBuilder fieldArea = new StringBuilder("");
        for (int i = 0; i < serviceRootar.getDonneesClimatByRegion(region).size(); i++) {
            //fieldArea.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getMois()+ "\n");
            fieldArea.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getLibelleMois().toString() + "\n");
            fieldArea.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getTemperatureMin() + "\n");
            fieldArea.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getTemperatureMax() + "\n");
            fieldArea.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getTemperatureMoy() + "\n");
            fieldArea.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getTauxHumidite() + "\n");
        }
        detailsDC.setText(fieldArea.toString());
    }

    public void afficherObjet(Pays paysSelected) {
        listeObjets.setItems(FXCollections.observableArrayList(serviceRootar.getObjetFilterByPays(paysSelected)));
        listeObjets.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherCategories(newValue));
    }

    public void afficherCategories(Objet objet) {
        detailsCategories.setText(serviceRootar.getCategoriesFilterbyObject(objet).getLibelleCategories());
    }

    public void afficherThemesByRegion(Region region) {
        listeThemes.setItems(FXCollections.observableArrayList(serviceRootar.getThemesByRegion(region)));

    }

    public void afficherRepEtrByVille(Ville ville) {
        listeRepEtr.setItems(FXCollections.observableArrayList(serviceRootar.getRepEtrangeresByVille(ville)));
        listeRepEtr.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherDetailsRepEtr(newValue));
    }

    public void afficherDetailsRepEtr(RepresentationEtrangere representationEtrangere) {
        StringBuilder fieldArea = new StringBuilder("");
        fieldArea.append(representationEtrangere.getLibelleRepEtrangere() + "\n");
        fieldArea.append(representationEtrangere.getAdresse() + "\n");
        fieldArea.append(representationEtrangere.getTelephone() + "\n");
        detailsRepEtr.setText(fieldArea.toString());

    }


    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

    }

    @FXML
    public void ajouterVille() {

        menuApp.showEditVille(getPaysSelected(), villeSelected, "Ajouter ville");
    }

    @FXML
    public void modifierVille() {
        menuApp.showEditVille(getPaysSelected(), villeSelected, "Modifier ville");
    }

    @FXML
    public void supprimerVille(){
         if( serviceRootar.deleteVille(villeSelected)) {
        fenetreAlert.fenetreInformation("Suppression de la ville", "la ville "+villeSelected.getNomVille()+" est supprimée");
         }
    }

    public void setPaysSelected(Pays paysSelected) {
        this.paysSelected = paysSelected;
    }

    public Pays getPaysSelected() {
        return paysSelected;
    }


    @FXML
    public void ajouterEvent() {
        eventSelected = null;
        menuApp.showEditEvent(getVilleSelected(), eventSelected, "Ajouter evenement");
    }

    @FXML
    public void modifierEvent() {
        eventSelected.setVille(getVilleSelected());
        menuApp.showEditEvent(getVilleSelected(), eventSelected, "Modifier evenement");
    }

    @FXML
    public void supprimerEvent() {
        if( serviceRootar.deleteEvent(eventSelected)) {
            fenetreAlert.fenetreInformation("Suppression de l'évènement", "l'évènement "+eventSelected.getLibelleEvenements()+" est supprimé");
        }
    }
}
