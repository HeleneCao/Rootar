package com.rootar;

import com.rootar.metier.Evenements;
import com.rootar.metier.Ville;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.ServiceRootar;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GestionAMSEvenementController {

    private MenuApp menuApp;
    private ServiceRootar serviceRootar;
    private Stage dialogStage;
    private boolean confirmed;
    private FenetreAlert fenetreAlert;
    private Evenements eventSelected;
    private Ville villeSelected;
    @FXML
    private Label titres;
    @FXML
    private TextField idEvent;
    @FXML
    private TextField libelleEvent;
    @FXML
    private TextField dateDebutEvent;
    @FXML
    private TextField dateFinEvent;
    @FXML
    private TextField descriptionEvent;

    @FXML
    private ComboBox<Ville> comboVille;

    public GestionAMSEvenementController() {
    }

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

    @FXML
    private void initialize() {
        this.eventSelected = new Evenements();
        this.serviceRootar = new ServiceRootar();
        this.fenetreAlert = new FenetreAlert();
        this.idEvent.setDisable(true);
    }

    @FXML
    public void annuler() {
        this.confirmed = false;
        this.dialogStage.close();
    }

    @FXML
    public void confirmer() {
        Evenements evenements = new Evenements();

        evenements.setLibelleEvenements(libelleEvent.getText());
        evenements.setDateDebutEvenements(dateDebutEvent.getText());
        evenements.setDateFinEvenements(dateFinEvent.getText());
        evenements.setDescriptionEvenements(descriptionEvent.getText());
        evenements.setVille(this.comboVille.getValue());
        if (this.dialogStage.getTitle().equals("Modifier evenement")) {
            evenements.setIdEvenements(Integer.valueOf(idEvent.getText()));
            this.serviceRootar.updateEvent(evenements);
        }

        if (this.dialogStage.getTitle().equals("Ajouter evenement")) {
            this.serviceRootar.insertEvent(evenements);
        }

        this.confirmed = true;
        this.dialogStage.close();
    }

    public void afficherEvent(Evenements eventSelected) {
        setEventSelected(eventSelected);

       idEvent.setText(String.valueOf(eventSelected.getIdEvenements()));
        libelleEvent.setText(eventSelected.getLibelleEvenements());
       dateDebutEvent.setText(eventSelected.getDateDebutEvenements());
        dateFinEvent.setText(eventSelected.getDateFinEvenements());
        descriptionEvent.setText(eventSelected.getDescriptionEvenements());
        this.comboVille.getSelectionModel().select(villeSelected);
        this.comboVille.setDisable(true);
    }

    public void setVilleSelected(Ville villeSelected) {
        this.villeSelected = villeSelected;
        this.comboVille.getSelectionModel().select(villeSelected);
        this.comboVille.setDisable(true);
    }

    public void setEventSelected(Evenements eventSelected) {
        this.eventSelected = eventSelected;
    }
}
