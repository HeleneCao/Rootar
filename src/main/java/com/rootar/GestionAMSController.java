package com.rootar;

import com.rootar.metier.Continent;
import com.rootar.metier.Monnaie;
import com.rootar.metier.Pays;
import com.rootar.metier.Visas;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GestionAMSController {
    @FXML
    private TextField idPays;
    @FXML
    private TextField codePays;
    @FXML
    private TextField nomPaysFr;
    @FXML
    private TextField nomPaysAng;
    @FXML
    private TextField nationalite;
    @FXML
    private TextField capitale;
    @FXML
    private TextField nombreHabitant;
    @FXML
    private TextField superficie;
    @FXML
    private TextField devise;
    @FXML
    private TextField feteNationale;
    @FXML
    private TextField indicatifTel;
    @FXML
    private ComboBox <Continent>continent;
    @FXML
    private ComboBox<Monnaie> monnaie;
    @FXML
    private TextField visas;

    private Stage dialogStage;
    private boolean confirmed;

    private boolean choixBouton ;
    private ServiceRootar serviceRootar;
    private Pays paysSeleted;

    private FenetreAlert fenetreAlert;



    @FXML
    private Label titres;
    @FXML
    private void initialize() {
        paysSeleted= new Pays();
   serviceRootar = new ServiceRootar();
    initAjouter();
    fenetreAlert = new FenetreAlert();


    }

    @FXML
    public void abandonner() {
        confirmed = false;
        dialogStage.close();

    }
    private void initAjouter(){
        System.out.println(serviceRootar.getContinentFiltre());

        continent.setItems(FXCollections.observableArrayList(serviceRootar.getContinentFiltre()));
        monnaie.setItems(FXCollections.observableArrayList(serviceRootar.getMonnaieFiltre()));
        visas.setText("null");
        visas.setDisable(true);
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        confirmed = false;

    }
    public void setTitle(String titre) {

        titres.setText(titre);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    @FXML
    public void annuler(){
        confirmed = false;
        dialogStage.close();
    }
    @FXML
    public void ajouterPays() {
        Pays pays = new Pays();
        if (pays != null) {

            pays.setIdPays(Integer.valueOf(idPays.getText()));
            pays.setCodePays(codePays.getText());
            pays.setNomPaysFr(nomPaysFr.getText());
            pays.setNomPaysAng(nomPaysAng.getText());
            pays.setNationalite(nationalite.getText());
            pays.setCapitale(capitale.getText());
            pays.setNbreHabitant(Integer.valueOf(nombreHabitant.getText()));
            pays.setSuperficie(Integer.valueOf(superficie.getText()));
            pays.setDevise(devise.getText());
            pays.setFeteNationale(feteNationale.getText());
            pays.setIndicatifTel(indicatifTel.getText());
            pays.setContinent(continent.getValue());
            pays.setMonnaie((monnaie.getValue()));
            if (serviceRootar.insertPays(pays)){
                fenetreAlert.fenetreInformation("Ajouter pays", "Le pays " + pays.getNomPaysFr() + " a été ajouté.");
            }

            dialogStage.close();

        }
    }
    
    @FXML
    public void modifierPays() {
        Pays pays = new Pays();
        if(paysSeleted.getCodePays() != null){


            pays.setIdPays(Integer.valueOf(idPays.getText()));
            pays.setCodePays(codePays.getText());
            pays.setNomPaysFr(nomPaysFr.getText());
            pays.setNomPaysAng(nomPaysAng.getText());
            pays.setNationalite(nationalite.getText());
            pays.setCapitale(capitale.getText());
            pays.setNbreHabitant(Integer.valueOf(nombreHabitant.getText()));
            pays.setSuperficie(Integer.valueOf(superficie.getText()));
            pays.setDevise(devise.getText());
            pays.setFeteNationale(feteNationale.getText());
            pays.setIndicatifTel(indicatifTel.getText());
            pays.setContinent(continent.getValue());
            pays.setMonnaie(monnaie.getValue());
            if(serviceRootar.updatePays(pays));
            fenetreAlert.fenetreInformation("Modification ", "Le pays " + paysSeleted.getNomPaysFr() + " a été modifié.");
            dialogStage.close();
        }
    }

    public void supprimerArticle(){

    }
    @FXML
    public void confirmer() {
        Pays pays = new Pays();
        pays.setIdPays(Integer.valueOf(idPays.getText()));
        pays.setCodePays(codePays.getText());
        pays.setNomPaysFr(nomPaysFr.getText());
        pays.setNomPaysAng(nomPaysAng.getText());
        pays.setNationalite(nationalite.getText());
        pays.setCapitale(capitale.getText());
        pays.setNbreHabitant(Integer.valueOf(nombreHabitant.getText()));
        pays.setSuperficie(Integer.valueOf(superficie.getText()));
        pays.setDevise(devise.getText());
        pays.setFeteNationale(feteNationale.getText());
        pays.setIndicatifTel(indicatifTel.getText());
        pays.setContinent(continent.getValue());
        pays.setMonnaie((monnaie.getValue()));

        if (dialogStage.getTitle().equals("Modifier pays")) {
            serviceRootar.updatePays(pays);
        }
        if (dialogStage.getTitle().equals("Ajouter pays")) {
            serviceRootar.insertPays(pays);
        }
        confirmed = true;

        dialogStage.close();
    }

    private void showAlertWithoutHeaderText(String titre, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }


    public void afficherArticle(Pays paysSelected) {
        this.paysSeleted=paysSelected;
        idPays.setText(String.valueOf(paysSelected.getIdPays()));
        codePays.setText(paysSelected.getCodePays());
        nomPaysFr.setText(paysSelected.getNomPaysFr());
        nomPaysAng.setText(paysSelected.getNomPaysAng());
        nationalite.setText(paysSelected.getNationalite());
        capitale.setText(paysSelected.getCapitale());
        nombreHabitant.setText(String.valueOf(paysSelected.getNbreHabitant()));
        superficie.setText(String.valueOf(paysSelected.getSuperficie()));
        devise.setText(paysSelected.getDevise());
        feteNationale.setText(paysSelected.getFeteNationale());
        indicatifTel.setText(paysSelected.getIndicatifTel());


    }

}
