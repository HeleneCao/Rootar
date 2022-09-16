package com.rootar;

import com.rootar.metier.*;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
    private ListView <Themes>listeThemes;
    @FXML
    private ListView<Objet> listeObjets;
    @FXML
    private ListView<RepresentationEtrangere> listeRepEtr;
    @FXML
    private ListView<Sante> listeSante;
    private Region regionSelected;
    @FXML
    private ListView<Categories> listCategories;

    private Stage dialogStage;
    private Sante santeSelected;

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

        listeSante.setItems(FXCollections.observableArrayList(serviceRootar.getSantebyPays(this.paysSelected)));
        listeSante.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->setSanteSelected(newValue));
        //System.out.println(santeSelected.getLibelleSante());
        afficherObjet(paysSelected);

    }

    public void afficherVilles(Region regionSelected) {

        ville.setItems(FXCollections.observableArrayList(serviceRootar.getVilleFilterByRegion(regionSelected)));
        ville.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherEvent(newValue));
        ville.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherRepEtrByVille(newValue));
        ville.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setVilleSelected(newValue));
        this.regionSelected=regionSelected;
    }


    public void afficherEvent(Ville villeSelected) {

        event.setItems(FXCollections.observableArrayList(serviceRootar.getEventFilterByVille(villeSelected)));
        event.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> detailsEvent(newValue));
        event.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setEventSelected(newValue));

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
    }

    public void afficherCategories(Objet objet) {
       listCategories.setItems(FXCollections.observableArrayList(serviceRootar.getCategoriesFilterbyObject(objet)));
       listCategories.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setCategoriesSelected(newValue));
    }

    public void afficherThemesByRegion(Region region) {
        listeThemes.setItems(FXCollections.observableArrayList(serviceRootar.getThemesByRegion(region)));

    }

    public void afficherRepEtrByVille(Ville ville) {
        listeRepEtr.setItems(FXCollections.observableArrayList(serviceRootar.getRepEtrangeresByVille(ville)));
        listeRepEtr.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherDetailsRepEtr(newValue));
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
    public void supprimerVille(){
         if( serviceRootar.deleteVille(villeSelected)) {
            fenetreAlert.fenetreInformation("Suppression de la ville", "la ville "+villeSelected.getNomVille()+" est supprimée");
         }
    }


/* =================================================================================================
                                        AJOUTER EVENEMENTS
   ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
 */


    @FXML
    public void ajouterEvent() {

        menuApp.showEditEvent(villeSelected,null, "Ajouter evenement");
    }

    @FXML
    public void modifierEvent() {
        menuApp.showEditEvent( null ,eventSelected, "Modifier evenement");
    }

    @FXML
    public void supprimerEvent() {
        if( serviceRootar.deleteEvent(eventSelected)) {
            fenetreAlert.fenetreInformation("Suppression de l'évènement", "l'évènement "+eventSelected.getLibelleEvenements()+" est supprimé");
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
            fenetreAlert.fenetreInformation("Suppression de la catégorie","iuh");
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
    // GETTERS ET SETTERS
    public void setPaysSelected(Pays paysSelected) {
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

    public void setEventSelected(Evenements eventSelected) {

     this.eventSelected = eventSelected;

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
}
