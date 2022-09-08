package com.rootar;

import com.rootar.metier.Continent;
import com.rootar.metier.Monnaie;
import com.rootar.metier.Pays;
import com.rootar.metier.Visas;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
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
    private TextField Nationalite;
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


    @FXML
    private Label titres;
    @FXML
    private void initialize() {
        serviceRootar = new ServiceRootar();
        initAjouter();


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
    public void ajouter() {
        Pays pays = new Pays();
        if (pays != null) {

            System.out.println("helene doit créer la methode insert");
            //if (serviceRootar.insertArticle(pays))
               // dialogStage.close();

        }
    }

    public void modifierArticle() {


    }



    public void supprimerArticle(){

    }
    @FXML
    public void confirmer() {
       /* System.out.println(choixBouton);
        if (articleSelected==null){
            ajouter();

        }
        if (!choixBouton && articleSelected !=null ){
            modifierArticle();
            showAlertWithoutHeaderText("modification article","Article modifié!!!");
            System.out.println("modifier");
        }
        if (choixBouton && articleSelected !=null){
            System.out.println("supprimer");
            showAlertWithoutHeaderText("Suppression article","Article supprimé!!!");
            supprimerArticle();
            dialogStage.close();
        }*/


        confirmed = true;

    }
    private void showAlertWithoutHeaderText(String titre, String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);

        // Header Text: null
        alert.setHeaderText(null);
        alert.setContentText(text);

        alert.showAndWait();
    }
}
