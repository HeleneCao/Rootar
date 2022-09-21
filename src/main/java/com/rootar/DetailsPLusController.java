package com.rootar;

import com.rootar.metier.*;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;


public class DetailsPLusController {

    private MenuApp menuApp;
    private ServiceRootar serviceRootar;
    private StringBuilder fieldArea;
    private Categories categoriesSelected;
    private Ville villeSelected;
    private Pays paysSelected;
    private Evenements eventSelected;

    private Objet objetSelected;

    private FenetreAlert fenetreAlert;
    private Region regionSelected;
    private RepresentationEtrangere repEtrSelected;
    @FXML
    private ListView<Region> listeRegion;
    @FXML
    private ListView<Ville> listeVille;
    @FXML
    private ListView<Evenements> listeEvent;
    @FXML
    private TextArea detailsEvent;
    @FXML
    private TextArea detailsDC;
    @FXML
    private TextArea detailsTypeC;
    @FXML
    private TextArea detailsRepEtr;
    @FXML
    private ListView<Objet> listeObjets;
    @FXML
    private ListView<RepresentationEtrangere> listeRepEtr;
    @FXML
    private ListView<Sante> listeSante;
    @FXML
    private ListView<Categories> listeCategories;

    @FXML
    private ListView<Aeroports> listeAeroport;

    private Stage dialogStage;
    private Sante santeSelected;

    @FXML
    private void initialize() {
        serviceRootar = new ServiceRootar();
        fieldArea = new StringBuilder("");
        fenetreAlert = new FenetreAlert();

    }

    public void afficherRegion(Pays paysSelected) {

        listeRegion.setItems(FXCollections.observableArrayList(serviceRootar.getRegionFilterByPays(paysSelected)));
        listeRegion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherVilles(newValue));
        listeRegion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherTypeClimat(newValue));
        listeRegion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherDonneesClimat(newValue));

        listeAeroport.setItems(FXCollections.observableArrayList(serviceRootar.getAeroportByPays(paysSelected)));
        listeSante.setItems(FXCollections.observableArrayList(serviceRootar.getSantebyPays(paysSelected)));
        listeSante.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->setSanteSelected(newValue));
        afficherObjet(paysSelected);
    }

    public void afficherVilles(Region regionSelected) {

        listeVille.setItems(FXCollections.observableArrayList(serviceRootar.getVilleFilterByRegion(regionSelected)));
        listeVille.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherEvent(newValue));
        listeVille.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherRepEtrByVille(newValue));
        listeVille.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setVilleSelected(newValue));
        this.regionSelected=regionSelected;
    }


    public void afficherEvent(Ville villeSelected) {

        listeEvent.setItems(FXCollections.observableArrayList(serviceRootar.getEventFilterByVille(villeSelected)));
        listeEvent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> detailsEvent(newValue));
        listeEvent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setEventSelected(newValue));

    }

    public void afficherTypeClimat(Region region) {
        detailsTypeC.setText(serviceRootar.getTypeClimatFilterByRegion(region).getLibelleTypeClimat());
    }

    public void afficherDonneesClimat(Region region) {

        StringBuilder fieldDC = new StringBuilder("");
        for (int i = 0; i < serviceRootar.getDonneesClimatByRegion(region).size(); i++) {

            fieldDC.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getLibelleMois() + "\n");
            fieldDC.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getTemperatureMin() + "\n");
            fieldDC.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getTemperatureMax() + "\n");
            fieldDC.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getTemperatureMoy() + "\n");
            fieldDC.append(serviceRootar.getDonneesClimatByRegion(region).get(i).getTauxHumidite() + "\n");
        }
        detailsDC.setText(fieldDC.toString());
    }

    public void afficherObjet(Pays paysSelected) {
        listeObjets.setItems(FXCollections.observableArrayList(serviceRootar.getObjetFilterByPays(paysSelected)));
        listeObjets.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherCategories(newValue));
        listeObjets.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setObjetSelected(newValue));
    }

    public void afficherCategories(Objet objet) {
        listeCategories.setItems(FXCollections.observableArrayList(serviceRootar.getCategoriesFilterbyObjet(objet)));
        listeCategories.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setCategoriesSelected(newValue));
    }


    public void afficherRepEtrByVille(Ville ville) {
        listeRepEtr.setItems(FXCollections.observableArrayList(serviceRootar.getRepEtrangeresByVille(ville)));
        listeRepEtr.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherDetailsRepEtr(newValue));
        listeRepEtr.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setRepEtrSelected(newValue));
    }

    public void afficherDetailsRepEtr(RepresentationEtrangere representationEtrangere) {
        StringBuilder fieldRE = new StringBuilder("");
        fieldRE.append(representationEtrangere.getLibelleRepEtrangere() + "\n");
        fieldRE.append(representationEtrangere.getAdresse() + "\n");
        fieldRE.append(representationEtrangere.getTelephone() + "\n");
        detailsRepEtr.setText(fieldRE.toString());

    }

    public void detailsEvent(Evenements event) {


        fieldArea.append(event.getDateDebutEvenements() + "\n");
        fieldArea.append(event.getDateFinEvenements() + "\n");
        fieldArea.append(event.getDescriptionEvenements() + "\n");
        detailsEvent.setText(fieldArea.toString());
    }

/* =================================================================================================
                                        AJOUTER VILLE
   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */

    @FXML
    public void ajouterVille() {
        menuApp.showEditVille(getPaysSelected(), villeSelected, regionSelected,"Ajouter ville");
    }

    @FXML
    public void modifierVille() {
        menuApp.showEditVille(getPaysSelected(),villeSelected ,null, "Modifier ville");
    }

    @FXML
    public void supprimerVille() {
        if (serviceRootar.deleteVille(villeSelected)) {
            fenetreAlert.fenetreInformation("Suppression de la ville", "La ville " + villeSelected.getNomVille() + " est supprimée.");
        }
    }


/* =================================================================================================
                                        AJOUTER EVENEMENT
   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */


    @FXML
    public void ajouterEvent() {
        menuApp.showEditEvent(villeSelected, null, "Ajouter événement");
    }

    @FXML
    public void modifierEvent() {
        menuApp.showEditEvent(villeSelected, eventSelected, "Modifier événement");
    }

    @FXML
    public void supprimerEvent() {
        if (serviceRootar.deleteEvent(eventSelected)) {
            fenetreAlert.fenetreInformation("Suppression de l'événement", "l'événement " + eventSelected.getLibelleEvenements() + " est supprimé.");
        }
    }

    /* =================================================================================================
                                        AJOUTER REPRESENTATION ETRANGERE
      ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    @FXML
    public void ajouterRepEtrangere() {

        menuApp.showEditRepEtrangere(paysSelected, villeSelected, null, "Ajouter représentation étrangère");
    }

    @FXML
    public void modifierRepEtrangere() {

        menuApp.showEditRepEtrangere(paysSelected, villeSelected, repEtrSelected, "Modifier représentation étrangère");
    }

    @FXML
    public void supprimerRepEtrangere() {
        if (serviceRootar.deleteRepEtrangere(repEtrSelected)) {
            fenetreAlert.fenetreInformation("Suppression de la représentation étrangère", "La representation etrangere " + repEtrSelected.getLibelleRepEtrangere() + " est supprimée.");
        }
    }


    /* =================================================================================================
                                        AJOUTER CATEGORIES
   +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */
    @FXML
    public void ajouterCat() {
        menuApp.showEditCat(null, "Ajouter catégorie");
    }
    @FXML
    public void modifierCat(){
        menuApp.showEditCat(categoriesSelected, "Modifier catégorie");
    }
    @FXML
    public void supprimerCat(){
        if(serviceRootar.deleteCategories(categoriesSelected)){
            fenetreAlert.fenetreInformation("Suppression de la catégorie","La catégorie " + categoriesSelected.getLibelleCategories() + " est suprimée.");
        }


    }
    /* =================================================================================================
                                           AJOUTER SANTE
      ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */
    @FXML
    public void ajouterSante() {
        menuApp.showEditSante(santeSelected, "Ajouter santé");

    }
    @FXML
    public void modifierSante(){
        menuApp.showEditSante(santeSelected, "Modifier santé");
    }
    @FXML
    public void supprimerSante(){


        if(serviceRootar.deleteExiger(new Exiger(paysSelected,santeSelected))) {
            serviceRootar.deleteSante(santeSelected);
            fenetreAlert.fenetreInformation("Suppression de l'élément sante'", " "+santeSelected.getLibelleSante()+" est supprimé");
        }
    }
     /* =================================================================================================
                                        AJOUTER OBJET
      ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    @FXML
    public void ajouterObjet() {
        menuApp.showEditObjet(paysSelected, categoriesSelected, null, "Ajouter l'objet");
    }
    @FXML
    public void modifierObjet() {
        menuApp.showEditObjet(paysSelected, categoriesSelected, objetSelected, "Modifier l'objet");
    }
    @FXML
    public void supprimerObjet() {
        if (serviceRootar.deleteEmporter(new Emporter(objetSelected.getIdObjet(), paysSelected.getIdPays()))) {

            serviceRootar.deleteObjet(objetSelected);
            fenetreAlert.fenetreInformation("Suppression de l'objet", "l'objet " + objetSelected.getLibelleObjet() + " est supprimé");
        }
    }


      /* =================================================================================================
                                   GETTERS ET SETTERS
      ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    */

    public void setPaysSelected(Pays paysSelected){
        this.paysSelected = paysSelected;

    }

    public Pays getPaysSelected() {
        return paysSelected;
    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }

    public void setVilleSelected(Ville ville) {
        villeSelected = ville;
    }

    public void setEventSelected(Evenements eventSelected) {this.eventSelected = eventSelected;}

    public RepresentationEtrangere getRepEtrSelected() {
        return repEtrSelected;
    }

    public void setRepEtrSelected(RepresentationEtrangere repEtrSelected) {
        this.repEtrSelected = repEtrSelected;
    }

    public void setCategoriesSelected(Categories categoriesSelected) {
        this.categoriesSelected = categoriesSelected;
    }

    public void setSanteSelected(Sante santeSelected) {
        this.santeSelected = santeSelected;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage=dialogStage;
    }

    public void setObjetSelected(Objet objetSelected) {
        this.objetSelected = objetSelected;
    }
}
