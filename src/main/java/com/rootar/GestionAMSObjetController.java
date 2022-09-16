package com.rootar;

import com.rootar.metier.*;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class GestionAMSObjetController {

    private Stage dialogStage;
    private boolean confirmed;
    private Objet objetSelected;

    private Categories categoriesSelected;

    private Pays paysSelected;
    private ServiceRootar serviceRootar;
    private FenetreAlert fenetreAlert;
    private MenuApp menuApp;

    ArrayList<Pays> listePays;
    @FXML
    private TextField idObjet;
    @FXML
    private TextField libelleObjet;
    @FXML
    private Label titres;
    @FXML
    private ComboBox<Categories> comboCatégorie;
    @FXML
    private ComboBox<Pays> comboPays;

    @FXML
    private ListView <Pays>listViewPays;

    @FXML
    private Button buttonAddPays;

    @FXML
    private void initialize() {
        serviceRootar = new ServiceRootar();
        fenetreAlert = new FenetreAlert();
        idObjet.setDisable(true);
        comboPays.setItems(FXCollections.observableArrayList(serviceRootar.getAllPays()));
        comboCatégorie.setItems(FXCollections.observableArrayList(serviceRootar.getAllCategories()));
        listePays = new ArrayList<>();
        System.out.println();

    }

    @FXML
    public void annuler() {
        confirmed = false;
        dialogStage.close();}

    @FXML
    public void buttonAjoutPays(){
        listePays.add((comboPays.getValue()));
        System.out.println(listePays.get(0));
        listViewPays.setItems(FXCollections.observableArrayList(listePays));
        listViewPays.refresh();
    }

    @FXML
    public void confirmer(){
    Objet objet = new Objet();

    objet.setLibelleObjet(libelleObjet.getText());
    objet.setCategories(comboCatégorie.getValue());


        if (dialogStage.getTitle().equals("Modifier l'objet")) {
            objet.setIdObjet(Integer.valueOf(idObjet.getText()));
            insertionObjet(true,objet);
        }
        else
        if (serviceRootar.insertObjet(objet)) {
            addInEmporter();
            fenetreAlert.fenetreInformation("Ajouter l'objet", "L'objet' " + objet.getLibelleObjet() + " a été ajouté.");
        }
        dialogStage.close();
    }

    private void insertionObjet(boolean insert, Objet objet){
        if (serviceRootar.updateObjet(objet) && insert)
            fenetreAlert.fenetreInformation("Modification ", "L'objet' " + objet.getLibelleObjet() + " a été modifié.");
    }

    public void addInEmporter(){
        Emporter emporter = new Emporter();
        for(int i=0;i<listePays.size();i++){
            emporter.setIdObjet(serviceRootar.lastIdObjet().getIdObjet());
            emporter.setIdPays(listePays.get(i).getIdPays());
            serviceRootar.insertEmporter(emporter);
        }
    }

    public void afficherObjet(Objet objetSelecled) {
        setObjetSelected(objetSelecled);
        idObjet.setText(String.valueOf(objetSelecled.getIdObjet()));
        libelleObjet.setText(objetSelecled.getLibelleObjet());
        comboCatégorie.getSelectionModel().select(serviceRootar.getCategorieById(objetSelecled.getCategories().getIdCategories()));
        System.out.println(objetSelecled.getCategories().getLibelleCategories());
        comboCatégorie.setDisable(false);
        listViewPays.setItems(FXCollections.observableArrayList(serviceRootar.getPaysbyObjet(objetSelecled)));
        comboPays.setDisable(false);
    }

    public void afficherAjouterObjet(Pays pays, Categories categories) {
        comboCatégorie.getSelectionModel().select(categoriesSelected);
        comboCatégorie.setDisable(false);
        comboPays.getSelectionModel().select(paysSelected);
        comboPays.setDisable(false);
    }

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }

    public void setObjetSelected(Objet objetSelected) {
        this.objetSelected = objetSelected;}

    public void setPaysSelected(Pays paysSelected) {
        paysSelected = paysSelected;
        comboPays.getSelectionModel().select(paysSelected);
        comboPays.setDisable(true);
       }

    public void setCategoriesSelected(Categories categoriesSelected) {
        categoriesSelected = categoriesSelected;
        comboCatégorie.getSelectionModel().select(categoriesSelected);
        comboCatégorie.setDisable(true);}

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        confirmed = false;}

    public boolean isConfirmed() {
        return confirmed;}

    public void setTitle(String titre) {
        this.titres.setText(titre);
    }

}
