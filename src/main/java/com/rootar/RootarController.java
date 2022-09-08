package com.rootar;

import com.rootar.MenuApp;
import com.rootar.metier.Continent;
import com.rootar.metier.Pays;
import com.rootar.metier.RepresentationEtrangere;
import com.rootar.metier.Themes;
import com.rootar.service.RootarSearch;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class RootarController {
    @FXML
    private ComboBox paysSearch;
    @FXML
    private ComboBox continentSearch;
    @FXML
    private ComboBox regionSearch;
    @FXML
    private ComboBox villeSearch;
    @FXML
    private ComboBox themesSearch;
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

    private MenuApp menuApp;
    @FXML
    private void initialize() {
        serviceRootar=new ServiceRootar();
        paysSelected= new Pays();
        tableRootar.setItems(FXCollections.observableArrayList(serviceRootar.getFilteredPays()));
        tableRootar.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> afficherDetails(newValue));
        colCodePays.setCellValueFactory(cellData -> cellData.getValue().codePaysProperty());
        colNomPays.setCellValueFactory(cellData -> cellData.getValue().nomPaysFrProperty());
        colNomContinent.setCellValueFactory(cellData -> cellData.getValue().nomContinentProperty());
        colLibelleMonnaie.setCellValueFactory(cellData -> cellData.getValue().libelleMonnaieProperty());
        colNationalite.setCellValueFactory(cellData -> cellData.getValue().nationaliteProperty());

        /*continentSearch.setItems(FXCollections.observableArrayList(serviceRootar.getContinentFiltre()));
        continentSearch.getItems().add(0,new Continent(0, "Choisir un continent"));
        continentSearch.valueProperty().addListener(observable -> filterContinent());

        paysSearch.setItems(FXCollections.observableArrayList(serviceRootar.getPaysFiltre()));
        paysSearch.getItems().add(0,new Pays(0,"choisir pays"));
        paysSearch.valueProperty().addListener(observable -> filtrePays());*/

    }
    private void afficherDetails(Pays pays){

        paysSelected=pays;
        System.out.println(pays);

        if(paysSelected != null) {
            System.out.println("afficher details?");
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
            //langue.setText(serviceRootar.getFilteredParler(paysSelected.getIdPays()).getIdLangues());
           // langue.setText(serviceRootar.getLanguesFilter("an"));
            langue.setText(serviceRootar.getLanguesFilter(serviceRootar.getFilteredParler(paysSelected.getIdPays()).getIdLangues()).getLibelleLangues());
            listeThemes.setItems(FXCollections.observableArrayList(serviceRootar.getThemesByPays(paysSelected)));
            listeRepEtrangeres.setItems(FXCollections.observableArrayList(serviceRootar.getRepEtrangeresByPays(paysSelected)));
        }


    }

    private void filtrePays() {
        RootarSearch rootarSearch = new RootarSearch();

        /*if (paysSearch.getSelectionModel().getSelectedItem() != null)
            rootarSearch.setPays(paysSearch.getSelectionModel().getSelectedItem().);
        if (continentSearch.getSelectionModel().getSelectedItem() != null)
            rootarSearch.setContinent(continentSearch.getSelectionModel().getSelectedItem());
*/
    }

    private void filterContinent() {
       /* if (continentSearch.getSelectionModel().getSelectedItem() != null
                && (continentSearch.getSelectionModel().getSelectedItem()).getIdContinent() != 0) {
            paysSearch.setItems(FXCollections.observableArrayList(
                    (continentSearch.getSelectionModel().getSelectedItem()).getPays()));

        } else {
            paysSearch.setItems(FXCollections.observableArrayList(serviceRootar.getPaysFiltre()));
        }
        paysSearch.getItems().add(0,new Pays(0, "Choisir un pays", new Continent()));
        paysSearch.getSelectionModel().select(0);*/
    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }
    public void afficherRegion(){
        menuApp.showDetailsPlus(paysSelected);
    }
    @FXML
    public void ajouter(){
        menuApp.showEdit();

    }
}
