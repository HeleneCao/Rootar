package com.rootar;

import com.rootar.metier.*;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

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
    private ComboBox<Continent> continent;
    @FXML
    private ComboBox<Monnaie> monnaie;
    @FXML
    private TextField visas;
    @FXML
    private ListView<Langues> listeViewLangues;
    @FXML
    private ComboBox<Langues> choiceBoxLang;
    @FXML
    private Button buttonAddLang;

    private Stage dialogStage;
    private boolean confirmed;


    private ServiceRootar serviceRootar;
    private Pays paysSeleted;

    private FenetreAlert fenetreAlert;

    ArrayList<Langues> listeLangues;
    @FXML
    private Label titres;

    @FXML
    private void initialize() {
        paysSeleted = new Pays();
        serviceRootar = new ServiceRootar();
        fenetreAlert = new FenetreAlert();
        choiceBoxLang.setItems(FXCollections.observableArrayList(serviceRootar.getAll()));
        listeLangues= new ArrayList<>();

        initAjouter();

    }

    private void initAjouter() {

        continent.setItems(FXCollections.observableArrayList(serviceRootar.getContinentFiltre()));
        monnaie.setItems(FXCollections.observableArrayList(serviceRootar.getMonnaieFiltre()));
        visas.setText("null");
        visas.setDisable(true);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
        confirmed = false;

    }

    @FXML
    public void buttonAjoutLangues(){
        listeLangues.add(choiceBoxLang.getValue());
        listeViewLangues.setItems(FXCollections.observableArrayList(listeLangues));
        listeViewLangues.refresh();
    }



    public void setTitle(String titre) {
        titres.setText(titre);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    @FXML
    public void annuler() {
        confirmed = false;
        dialogStage.close();
    }

    @FXML
    public void confirmer() {
        Pays pays = new Pays();
        pays.setIdPays(Integer.valueOf(idPays.getText()));
        pays.setCodePays(codePays.getText());
        pays.setNomPaysFr(nomPaysFr.getText());
        pays.setNomPaysAng(nomPaysAng.getText());
        pays.setNationalite(nationalite.getText());
        pays.setNbreHabitant(Integer.valueOf(nombreHabitant.getText()));
        pays.setSuperficie(Integer.valueOf(superficie.getText()));
        pays.setDevise(devise.getText());
        pays.setFeteNationale(feteNationale.getText());
        pays.setIndicatifTel(indicatifTel.getText());
        pays.setContinent(continent.getValue());
        pays.setMonnaie((monnaie.getValue()));

        if (dialogStage.getTitle().equals("Modifier pays")) {
            if (serviceRootar.updatePays(pays)) ;
            fenetreAlert.fenetreInformation("Modification ", "Le pays " + paysSeleted.getNomPaysFr() + " a été modifié.");
        }
        if (dialogStage.getTitle().equals("Ajouter pays")) {

            if (serviceRootar.insertPays(pays)) {
                addInParler();
                fenetreAlert.fenetreInformation("Ajouter pays", "Le pays " + pays.getNomPaysFr() + " a été ajouté.");
            }
        }
        confirmed = true;

        dialogStage.close();
    }
    public void addInParler(){
        Parler parler = new Parler();
        for(int i=0;i<listeLangues.size();i++){
            parler.setIdPays(Integer.valueOf(idPays.getText()));
            parler.setIdLangues(listeLangues.get(i).getIdLangues());
            serviceRootar.insertParler(parler);
        }
    }
    public void afficherPays(Pays paysSelected) {
        paysSeleted = paysSelected;
        idPays.setText(String.valueOf(paysSelected.getIdPays()));
        codePays.setText(paysSelected.getCodePays());
        nomPaysFr.setText(paysSelected.getNomPaysFr());
        nomPaysAng.setText(paysSelected.getNomPaysAng());
        nationalite.setText(paysSelected.getNationalite());
        nombreHabitant.setText(String.valueOf(paysSelected.getNbreHabitant()));
        superficie.setText(String.valueOf(paysSelected.getSuperficie()));
        devise.setText(paysSelected.getDevise());
        feteNationale.setText(paysSelected.getFeteNationale());
        indicatifTel.setText(paysSelected.getIndicatifTel());
        continent.getSelectionModel().select(paysSelected.getContinent());
        monnaie.getSelectionModel().select(paysSelected.getMonnaie());
    }
}
