package com.rootar;


import com.rootar.metier.*;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RootarController {
    @FXML
    private ComboBox <Pays> paysSearch;
    @FXML
    private ComboBox <Continent>continentSearch;
    @FXML
    private ComboBox <Region>regionSearch;
    @FXML
    private ComboBox <Ville>villeSearch;
    @FXML
    private ComboBox <Themes>themesSearch;
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
    private Label langue;
    @FXML
    private Label monnaie;
    @FXML
    private TableView <Pays> tableRootar;
    @FXML
    private TableColumn<Pays, String> colCodePays;
    @FXML
    private TableColumn<Pays, String > colNomPays;
    @FXML
    private TableColumn<Pays,String> colNomContinent;
    @FXML
    private TableColumn<Pays,String> colLibelleMonnaie;
    @FXML
    private TableColumn<Pays,String> colNationalite;
    @FXML
    private ListView <Themes> listeThemes;
    @FXML
    private ListView <RepresentationEtrangere> listeRepEtrangeres;
    private ServiceRootar serviceRootar;
    private Pays paysSelected;
    private FenetreAlert fenetreAlert;
    private MenuApp menuApp;
    @FXML
    private void initialize() {
        fenetreAlert=new FenetreAlert();
        serviceRootar=new ServiceRootar();
        paysSelected= new Pays();
        tableRootar.setItems(FXCollections.observableArrayList(serviceRootar.getFilteredPays()));
        tableRootar.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> afficherDetails(newValue));
        colCodePays.setCellValueFactory(cellData -> cellData.getValue().codePaysProperty());
        colNomPays.setCellValueFactory(cellData -> cellData.getValue().nomPaysFrProperty());
        colNomContinent.setCellValueFactory(cellData -> cellData.getValue().nomContinentProperty());
        colLibelleMonnaie.setCellValueFactory(cellData -> cellData.getValue().libelleMonnaieProperty());
        colNationalite.setCellValueFactory(cellData -> cellData.getValue().nationaliteProperty());
        tableRootar.refresh();


    }
    private void afficherDetails(Pays pays){

        paysSelected=pays;


        if(paysSelected != null) {

            codePays.setText(pays.getCodePays());
            nomPaysFr.setText(paysSelected.getNomPaysFr());
            nomPaysAng.setText(paysSelected.getNomPaysAng());
            nationalite.setText(paysSelected.getNationalite());
            capitale.setText(paysSelected.getCapitale());
            nbreHabitant.setText(String.valueOf(paysSelected.getNbreHabitant()));
            superficie.setText(String.valueOf(paysSelected.getSuperficie()));
            devise.setText(paysSelected.getDevise());
            feteNat.setText(paysSelected.getFeteNationale());
            indTel.setText(paysSelected.getIndicatifTel());
            monnaie.setText(paysSelected.getMonnaie().getLibelleMonnaie());


             langue.setText(serviceRootar.getLanguesFilter(serviceRootar.getFilteredParler(paysSelected.getIdPays()).getIdLangues()).getLibelleLangues());
            listeThemes.setItems(FXCollections.observableArrayList(serviceRootar.getThemesByPays(paysSelected)));
            listeRepEtrangeres.setItems(FXCollections.observableArrayList(serviceRootar.getRepEtrangeresByPays(paysSelected)));
        }


    }


    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }
    public void afficherRegion(){
        menuApp.showDetailsPlus(paysSelected);

    }
    @FXML
    public void ajouter(){
        paysSelected=null;
        menuApp.showEdit(paysSelected,"Ajouter pays");

    }
    @FXML
    public void modifier(){
        menuApp.showEdit(paysSelected, "Modifier pays");
    }

    @FXML
    public void supprimer(){
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
