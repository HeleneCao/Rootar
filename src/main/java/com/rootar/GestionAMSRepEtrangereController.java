package com.rootar;

import com.rootar.metier.*;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.ServiceRootar;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GestionAMSRepEtrangereController {

    private MenuApp menuApp;

    private ServiceRootar serviceRootar;
    private Stage dialogStage;
    private boolean confirmed;

    private FenetreAlert fenetreAlert;
    private RepresentationEtrangere repEtrSelected;
    private Pays paysSelected;
    private Ville villeSelected;
    @FXML
    private TextField idRepEtrangere;
    @FXML
    private TextField libelleRepEtrangere;
    @FXML
    private TextField telephone;
    @FXML
    private TextField adresse;
    @FXML
    private ComboBox<Pays> comboPays;
    @FXML
    private ComboBox<Ville> comboVille;
    @FXML
    private Label titres;

    public void setMenuApp(MenuApp menuApp) {
        this.menuApp = menuApp;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        this.confirmed = false;
    }

    public void setTitle(String titre) {
        this.titres.setText(titre);
    }

    public boolean isConfirmed() {
        return this.confirmed;
    }


    public void setRepEtrSelected(RepresentationEtrangere repEtrSelected) {
        this.repEtrSelected = repEtrSelected;
    }

    public void setPaysSelected(Pays paysSelected) {
        this.paysSelected = paysSelected;
        this.comboPays.getSelectionModel().select(paysSelected);
        this.comboPays.setDisable(true);
    }

    public void setVilleSelected(Ville villeSelected) {
        this.villeSelected = villeSelected;
        this.comboVille.getSelectionModel().select(villeSelected);
        this.comboVille.setDisable(true);
    }

    @FXML
    private void initialize() {
        this.repEtrSelected = new RepresentationEtrangere();
        this.serviceRootar = new ServiceRootar();
        this.fenetreAlert = new FenetreAlert();
        this.idRepEtrangere.setDisable(true);
    }

    @FXML
    public void annuler() {
        this.confirmed = false;
        this.dialogStage.close();
    }

    @FXML
    public void confirmer() {
        RepresentationEtrangere representationEtrangere = new RepresentationEtrangere();

        representationEtrangere.setLibelleRepEtrangere(libelleRepEtrangere.getText());
        representationEtrangere.setTelephone(telephone.getText());
        representationEtrangere.setAdresse(adresse.getText());
        representationEtrangere.setPays(comboPays.getValue());
        representationEtrangere.setVille(comboVille.getValue());
        representationEtrangere.setIdRepEtrangere(Integer.valueOf(idRepEtrangere.getText()));

        if (dialogStage.getTitle().equals("Modifier représentation étrangère")) {
            insertionRepEtrangere(true,representationEtrangere);
        }
        else
        if (serviceRootar.insertRepEtrangere(representationEtrangere)) {
            fenetreAlert.fenetreInformation("Ajouter représentation étrangère", "La représentation étrangère " + representationEtrangere.getLibelleRepEtrangere() + " a été ajoutée.");
        }
        dialogStage.close();
    }

    private void insertionRepEtrangere(boolean insert, RepresentationEtrangere representationEtrangere){
        if (serviceRootar.updateRepEtrangere(representationEtrangere) && insert)
            fenetreAlert.fenetreInformation("Modification ", "La représentation étrangère " + representationEtrangere.getLibelleRepEtrangere() + " a été modifiée.");
    }

    public void afficherRepEtranger(RepresentationEtrangere repEtrSelected) {

        setRepEtrSelected(repEtrSelected);
        idRepEtrangere.setText(String.valueOf(repEtrSelected.getIdRepEtrangere()));
        libelleRepEtrangere.setText(repEtrSelected.getLibelleRepEtrangere());
        telephone.setText(repEtrSelected.getTelephone());
        adresse.setText(repEtrSelected.getAdresse());
        comboPays.getSelectionModel().select(paysSelected);
        comboPays.setDisable(true);
        comboVille.getSelectionModel().select(villeSelected);
        comboVille.setDisable(true);
    }

    public void afficherAjouterRepEtrangere(Pays pays, Ville ville) {
        comboPays.getSelectionModel().select(pays);
        comboPays.setDisable(true);
        comboVille.getSelectionModel().select(ville);
        comboVille.setDisable(true);
    }


}
