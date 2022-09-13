package com.rootar;

import com.rootar.metier.Pays;
import com.rootar.metier.Region;
import com.rootar.metier.Ville;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GestionAMSVilleController {
    @FXML
    private TextField nomVille;
    @FXML
    private Label titres;
    @FXML
    private TextField idVille;
    @FXML
    private ComboBox<Region> comboRegion;
    private Stage dialogStage;
    private boolean confirmed;
    private Pays paysSelected;
    private ServiceRootar serviceRootar;

    private FenetreAlert fenetreAlert;

    @FXML
    private void initialize() {

        serviceRootar = new ServiceRootar();
        fenetreAlert = new FenetreAlert();
        idVille.setDisable(true);


    }

    private void initRegion(){
        comboRegion.setItems(FXCollections.observableArrayList(serviceRootar.getRegionFilterByPays(getPaysSelected())));
        comboRegion.getItems().add(0,new Region(0,"Aucune"));

    }

    @FXML
    public void annuler() {
        confirmed = false;
        dialogStage.close();
    }

    @FXML
    public void confirmer() {
        Ville ville= new Ville();
        ville.setIdVille(Integer.valueOf(idVille.getText()));
        ville.setNomVille(nomVille.getText());
        ville.setRegion(comboRegion.getValue());

        if (dialogStage.getTitle().equals("Modifier ville")) {
           insertionVille(true,ville);
        }
        else
            if (serviceRootar.insertVille(ville)) {
                fenetreAlert.fenetreInformation("Ajouter pays", "La ville " + ville.getNomVille() + " a été ajouté.");
            }



        dialogStage.close();
    }

    public void afficherVille(Ville ville) {

        idVille.setText(String.valueOf(ville.getIdVille()));
        nomVille.setText(ville.getNomVille());
        comboRegion.getSelectionModel().select(ville.getRegion());
        comboRegion.setDisable(true);
    }
    public void afficherAjouterVille(Region region){

        comboRegion.getSelectionModel().select(region);
        comboRegion.setDisable(true);
    }

    public void setPaysSelected(Pays paysSelected) {
        this.paysSelected = paysSelected;
        initRegion();
    }

    public Pays getPaysSelected() {
        return paysSelected;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        confirmed = false;
    }
    private void insertionVille(boolean insert, Ville ville){
        if (serviceRootar.updateVille(ville) && insert)
            fenetreAlert.fenetreInformation("Modification ", "La ville " + ville.getNomVille() + " a été modifié.");
    }
    public void setTitle(String titre) {
        titres.setText(titre);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

}
