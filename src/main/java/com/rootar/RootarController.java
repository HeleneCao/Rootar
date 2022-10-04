package com.rootar;


import com.rootar.metier.*;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.RootarSearch;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private TableView <Pays> tableRootar;
    @FXML
    private TableColumn<Pays, String > colNomPays;
    @FXML
    private TableColumn<Pays,String> colNomContinent;
    @FXML
    private TableColumn<Pays,String> colNomVille;
    @FXML
    private ListView <Themes> listeThemes;
    @FXML
    private ListView<Langues> listeLangues;
    private ServiceRootar serviceRootar;
    private Pays paysSelected;
    private FenetreAlert fenetreAlert;
    private MenuApp menuApp;
    @FXML
    private void initialize() {

        gridpaneDetails.setVisible(false);
        fenetreAlert=new FenetreAlert();
        serviceRootar=new ServiceRootar();
        paysSelected= new Pays();
        tableRootar.setItems(FXCollections.observableArrayList(serviceRootar.getFilteredPays()));
        tableRootar.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> afficherDetails(newValue));

        colNomPays.setCellValueFactory(cellData -> cellData.getValue().nomPaysFrProperty());
        colNomContinent.setCellValueFactory(cellData -> cellData.getValue().nomContinentProperty());
       colNomVille.setCellValueFactory(cellData -> cellData.getValue().nomVilleProperty());

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
        filterContinent();

        /*RootarSearch rootarSearch = new RootarSearch();
        rootarSearch.setVille(villeSearch.getSelectionModel().getSelectedItem());
        tableRootar.setItems(FXCollections.observableArrayList(serviceRootar.getFilteredPays(rootarSearch)));*/

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
        paysSearch.getSelectionModel().select(0);


        filterPays();
    }
    @FXML
    private void filterPays() {
        RootarSearch rootarSearch = new RootarSearch();
        rootarSearch.setLibRecherche(rechercher.getText());

        if (continentSearch.getSelectionModel().getSelectedItem() != null) rootarSearch.setContinent(continentSearch.getSelectionModel().getSelectedItem());
        if (paysSearch.getSelectionModel().getSelectedItem() != null)
            rootarSearch.setPays(paysSearch.getSelectionModel().getSelectedItem());
            villeSearch.setItems(FXCollections.observableArrayList(serviceRootar.getLike(rootarSearch)));
        if(villeSearch.getSelectionModel().getSelectedItem() != null)
            rootarSearch.setVille(villeSearch.getSelectionModel().getSelectedItem());
        if(themesSearch.getSelectionModel().getSelectedItem() != null)
            rootarSearch.setTheme(themesSearch.getSelectionModel().getSelectedItem());
            tableRootar.setItems(FXCollections.observableArrayList(serviceRootar.getFilteredPays(rootarSearch)));

    }

    private void afficherDetails(Pays pays){

        paysSelected=pays;

        gridpaneDetails.setVisible(true);
        if(paysSelected != null) {

            codePays.setText(pays.getCodePays());
            nomPaysFr.setText(paysSelected.getNomPaysFr());
            nomPaysAng.setText(paysSelected.getNomPaysAng());
            nationalite.setText(paysSelected.getNationalite());
            capitale.setText(paysSelected.getVille().getNomVille());
            monnaie.setText(paysSelected.getMonnaie().getLibelleMonnaie());
            nbreHabitant.setText(String.valueOf(paysSelected.getNbreHabitant()));
            superficie.setText(String.valueOf(paysSelected.getSuperficie()));
            feteNat.setText(paysSelected.getFeteNationale());
            devise.setText(paysSelected.getDevise());
            indTel.setText(paysSelected.getIndicatifTel());
            listeLangues.setItems(FXCollections.observableArrayList(serviceRootar.getLanguesByPays(paysSelected)));
            listeThemes.setItems(FXCollections.observableArrayList(serviceRootar.getThemesByPays(paysSelected)));

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
        serviceRootar.deleteParler(parler);
       if( serviceRootar.deletePays(paysSelected)) {
           fenetreAlert.fenetreInformation("Suppression du pays", "le pays "+paysSelected.getNomPaysFr()+" est supprimé");
       }
    }
    @FXML
    public void reset(){
        rechercher.clear();
        continentSearch.getSelectionModel().selectFirst();
        paysSearch.getSelectionModel().selectFirst();
        themesSearch.getSelectionModel().selectFirst();
        villeSearch.getSelectionModel().selectFirst();
        tableRootar.setItems(FXCollections.observableArrayList(serviceRootar.getFilteredPays()));
        tableRootar.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> afficherDetails(newValue));
        tableRootar.refresh();
    }
}
