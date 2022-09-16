package com.rootar;


import com.rootar.metier.*;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class RootarController {
    @FXML
    private AnchorPane anchoDetails;
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
        listeRepEtrangeres.setVisible(false);
        anchoDetails.setVisible(false);
        fenetreAlert=new FenetreAlert();
        serviceRootar=new ServiceRootar();
        paysSelected= new Pays();
        tableRootar.setItems(FXCollections.observableArrayList(serviceRootar.getFilteredPays()));
        tableRootar.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) -> afficherDetails(newValue));

        colNomPays.setCellValueFactory(cellData -> cellData.getValue().nomPaysFrProperty());
        colNomContinent.setCellValueFactory(cellData -> cellData.getValue().nomContinentProperty());

        tableRootar.refresh();
        listeLangues.setOrientation(Orientation.HORIZONTAL);
        listeSante.setOrientation(Orientation.HORIZONTAL);


    }
    public void afficherRegion(Pays paysSelected) {

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

    }
    private void afficherDetails(Pays pays){

        paysSelected=pays;

        anchoDetails.setVisible(true);
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
            listeLangues.setItems(FXCollections.observableArrayList(serviceRootar.getLanguesByPays(paysSelected)));
            afficherRegion(paysSelected);
             //langue.setText(serviceRootar.getLanguesFilter(serviceRootar.getFilteredParler(paysSelected.getIdPays()).getIdLangues()).getLibelleLangues());
            listeThemes.setItems(FXCollections.observableArrayList(serviceRootar.getThemesByPays(paysSelected)));
            listeRepEtrangeres.setItems(FXCollections.observableArrayList(serviceRootar.getRepEtrangeresByPays(paysSelected)));
            listeSante.setItems(FXCollections.observableArrayList(serviceRootar.getSantebyPays(pays)));

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
