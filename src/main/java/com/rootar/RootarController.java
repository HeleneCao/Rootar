package com.rootar;


import com.rootar.metier.*;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.RootarSearch;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class RootarController {
    @FXML
    private GridPane gridpaneDetails;
    @FXML
    private ComboBox <Pays> paysSearch;
    @FXML
    private ComboBox <Continent>continentSearch;
    @FXML
    private ComboBox <Ville>villeSearch;
    @FXML
    private ComboBox <Themes>themesSearch;
    @FXML
    private ComboBox <TypeClimat>typeClimatSearch;
    @FXML
    private TextField rechercher;
    @FXML
    private Label codePays;
    @FXML
    private Label nomPaysFr;
    @FXML
    private Label nomPaysAng;
    @FXML
    private Label nationalite;
    @FXML
    private Label capitale;
    @FXML
    private Label nbreHabitant;
    @FXML
    private Label superficie;
    @FXML
    private Label devise;
    @FXML
    private Label feteNat;
    @FXML
    private Label indTel;

    @FXML
    private Label monnaie;
    @FXML
    private TextArea detailsEvent;
    @FXML
    private TableView <Pays> tableRootar;
    @FXML
    private TextArea detailsDC;
    @FXML
    private TextArea detailsTypeC;
    @FXML
    private TextArea detailsRepEtr;

    @FXML
    private TableColumn<Pays, String > colNomPays;
    @FXML
    private TableColumn<Pays,String> colNomContinent;
    @FXML
    private TableColumn<Region,String> colNomRegion;

    @FXML
    private ListView <Themes> listeThemes;
    @FXML
    private ListView <RepresentationEtrangere> listeRepEtrangeres;
    @FXML
    private ListView<Langues> listeLangues;
    @FXML
    private ListView<Region> listeRegion;
    @FXML
    private ListView<Ville> listeVille;
    @FXML
    private ListView<Objet> listeObjets;
    @FXML
    private ListView<Categories>listeCat;
    @FXML
    private ListView<Sante> listeSante;
    @FXML
    private ListView<Evenements> listeEvent;
    private ServiceRootar serviceRootar;
    private Pays paysSelected;
    private FenetreAlert fenetreAlert;
    private MenuApp menuApp;
    @FXML
    private void initialize() {
        //listeRepEtrangeres.setVisible(false);
        gridpaneDetails.setVisible(false);
        fenetreAlert=new FenetreAlert();
        serviceRootar=new ServiceRootar();
        paysSelected= new Pays();
        tableRootar.setItems(FXCollections.observableArrayList(serviceRootar.getFilteredPays()));
        tableRootar.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> afficherDetails(newValue));

        colNomPays.setCellValueFactory(cellData -> cellData.getValue().nomPaysFrProperty());
        colNomContinent.setCellValueFactory(cellData -> cellData.getValue().nomContinentProperty());
        //colNomRegion.setCellValueFactory(cellData -> cellData.getValue().nomRegionProperty());

        // Initialisation des comboBox
        continentSearch.setItems(FXCollections.observableArrayList(serviceRootar.getContinentFiltre()));
        continentSearch.getItems().add(0,new Continent(0,"Choisir un continent"));
        continentSearch.valueProperty().addListener(observable -> filterContinent());

        paysSearch.setItems(FXCollections.observableArrayList(serviceRootar.getPaysFiltre()));
        paysSearch.valueProperty().addListener(observable -> filterPays());
        paysSearch.getItems().add(0,new Pays(0, "Choisir un pays"));


       villeSearch.setItems(FXCollections.observableArrayList((serviceRootar.getVilleFiltre())));
        villeSearch.valueProperty().addListener(observable -> filterVille());
       villeSearch.getItems().add(0,new Ville(0, "Choisir une ville"));

        themesSearch.setItems(FXCollections.observableArrayList(serviceRootar.getThemeFiltre()));
        themesSearch.valueProperty().addListener(observable -> filterPays());
        themesSearch.getItems().add(0,new Themes(0, "Choisir un theme"));
        System.out.println(serviceRootar.getThemeFiltre().get(0).getLibelleThemes());




    }
    private void filterVille(){
        if(paysSearch.getSelectionModel().getSelectedItem() != null){
            villeSearch.setItems(FXCollections.observableArrayList(paysSearch.getSelectionModel().getSelectedItem().getVille()));
        }
        if (villeSearch.getSelectionModel().getSelectedItem() != null
                && (villeSearch.getSelectionModel().getSelectedItem()).getIdVille()!= 0) {
            paysSearch.setItems(FXCollections.observableArrayList(
                    (villeSearch.getSelectionModel().getSelectedItem()).getPays()));

        } else {
            paysSearch.setItems(FXCollections.observableArrayList(serviceRootar.getPaysFiltre()));
        }
        paysSearch.getItems().add(0,new Pays(0, "Choisir un pays", new Continent()));
        paysSearch.getSelectionModel().select(0);

        filterPays();

    }
    private void filterContinent() {
        if (continentSearch.getSelectionModel().getSelectedItem() != null
                && (continentSearch.getSelectionModel().getSelectedItem()).getIdContinent() != 0) {
            paysSearch.setItems(FXCollections.observableArrayList(
                    (continentSearch.getSelectionModel().getSelectedItem()).getPays()));

        } else {
            paysSearch.setItems(FXCollections.observableArrayList(serviceRootar.getPaysFiltre()));
        }
        paysSearch.getItems().add(0,new Pays(0, "Choisir un pays", new Continent()));
        paysSearch.getSelectionModel().select(1);
        filterPays();
    }
    @FXML
    private void filterPays() {
        RootarSearch rootarSearch = new RootarSearch();
        rootarSearch.setLibRecherche(rechercher.getText());

        if (continentSearch.getSelectionModel().getSelectedItem() != null)
          rootarSearch.setContinent(continentSearch.getSelectionModel().getSelectedItem());
        if (paysSearch.getSelectionModel().getSelectedItem() != null)
            rootarSearch.setPays(paysSearch.getSelectionModel().getSelectedItem());

            villeSearch.setItems(FXCollections.observableArrayList(serviceRootar.getLike(rootarSearch)));
        if(villeSearch.getSelectionModel().getSelectedItem() != null)
            rootarSearch.setVille(villeSearch.getSelectionModel().getSelectedItem());

        if(themesSearch.getSelectionModel().getSelectedItem() != null)
            rootarSearch.setTheme(themesSearch.getSelectionModel().getSelectedItem());

        tableRootar.setItems(FXCollections.observableArrayList(serviceRootar.getFilteredPays(rootarSearch)));





    }
    /*public void afficherRegion(Pays paysSelected) {

        listeRegion.setItems(FXCollections.observableArrayList(serviceRootar.getRegionFilterByPays(paysSelected)));
        listeRegion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherVilles(newValue));
        listeRegion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherTypeClimat(newValue));
        listeRegion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherDonneesClimat(newValue));
       // listeRegion.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherThemesByRegion(newValue));

       // listeSante.setItems(FXCollections.observableArrayList(serviceRootar.getSantebyPays(this.paysSelected)));
      //  listeSante.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) ->setSanteSelected(newValue));
        //System.out.println(santeSelected.getLibelleSante());
         afficherObjet(paysSelected);

    }
    public void afficherVilles(Region regionSelected) {

        listeVille.setItems(FXCollections.observableArrayList(serviceRootar.getVilleFilterByRegion(regionSelected)));
        listeVille.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherEvent(newValue));
       listeVille.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherRepEtrByVille(newValue));
        //ville.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setVilleSelected(newValue));
        //this.regionSelected=regionSelected;
    }
    public void afficherEvent(Ville villeSelected) {

        listeEvent.setItems(FXCollections.observableArrayList(serviceRootar.getEventFilterByVille(villeSelected)));
        listeEvent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> detailsEvent(newValue));
        //event.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setEventSelected(newValue));
        listeRepEtrangeres.setVisible(true);

    }
    public void detailsEvent(Evenements event) {

        StringBuilder fieldArea=new StringBuilder();
        fieldArea.append(event.getDateDebutEvenements() + "\n");
        fieldArea.append(event.getDateFinEvenements() + "\n");
        fieldArea.append(event.getDescriptionEvenements() + "\n");
        detailsEvent.setText(fieldArea.toString());
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
        //listeObjets.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setObjetSelected(newValue));
    }
    public void afficherCategories(Objet objet) {
       listeCat.setItems(FXCollections.observableArrayList(serviceRootar.getCategoriesFilterbyObjet(objet)));
       // listeCat.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setCategoriesSelected(newValue));
    }
    public void afficherRepEtrByVille(Ville ville) {
        listeRepEtrangeres.setItems(FXCollections.observableArrayList(serviceRootar.getRepEtrangeresByVille(ville)));
        listeRepEtrangeres.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> afficherDetailsRepEtr(newValue));
        //listeRepEtr.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> setRepEtrSelected(newValue));
    }

    public void afficherDetailsRepEtr(RepresentationEtrangere representationEtrangere) {
        StringBuilder fieldRE = new StringBuilder("");
        fieldRE.append(representationEtrangere.getLibelleRepEtrangere() + "\n");
        fieldRE.append(representationEtrangere.getAdresse() + "\n");
        fieldRE.append(representationEtrangere.getTelephone() + "\n");
        detailsRepEtr.setText(fieldRE.toString());

    }*/

    private void afficherDetails(Pays pays){

        paysSelected=pays;

        gridpaneDetails.setVisible(true);
        if(paysSelected != null) {


            codePays.setText(pays.getCodePays());
            nomPaysFr.setText(paysSelected.getNomPaysFr());
            nomPaysAng.setText(paysSelected.getNomPaysAng());
            nationalite.setText(paysSelected.getNationalite());
            nbreHabitant.setText(String.valueOf(paysSelected.getNbreHabitant()));
            superficie.setText(String.valueOf(paysSelected.getSuperficie()));
            devise.setText(paysSelected.getDevise());
            feteNat.setText(paysSelected.getFeteNationale());
            indTel.setText(paysSelected.getIndicatifTel());
            monnaie.setText(paysSelected.getMonnaie().getLibelleMonnaie());
            listeLangues.setItems(FXCollections.observableArrayList(serviceRootar.getLanguesByPays(paysSelected)));
            //afficherRegion(paysSelected);
             //langue.setText(serviceRootar.getLanguesFilter(serviceRootar.getFilteredParler(paysSelected.getIdPays()).getIdLangues()).getLibelleLangues());
            listeThemes.setItems(FXCollections.observableArrayList(serviceRootar.getThemesByPays(paysSelected)));
            //listeRepEtrangeres.setItems(FXCollections.observableArrayList(serviceRootar.getRepEtrangeresByPays(paysSelected)));
           // listeSante.setItems(FXCollections.observableArrayList(serviceRootar.getSantebyPays(pays)));

        }


    }


    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }
    public void afficherRegion(){
        menuApp.showDetailsPlus(paysSelected);

    }
    @FXML
    public void ajouterPays(){
        paysSelected=null;
        menuApp.showEdit(paysSelected,"Ajouter pays");

    }
    @FXML
    public void modifierPays(){
        menuApp.showEdit(paysSelected, "Modifier pays");
    }

    @FXML
    public void supprimerPays(){
        Parler parler= new Parler();

        parler.setIdPays(paysSelected.getIdPays());
        System.out.println(parler.getIdPays());
        serviceRootar.deleteParler(parler);
       if( serviceRootar.deletePays(paysSelected)) {

           fenetreAlert.fenetreInformation("Suppression du pays", "le pays "+paysSelected.getNomPaysFr()+" est supprimé");
       }

    }
    @FXML
    public void reset(){
        tableRootar.setItems(FXCollections.observableArrayList(serviceRootar.getFilteredPays()));
        tableRootar.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> afficherDetails(newValue));

        tableRootar.refresh();
    }
}
