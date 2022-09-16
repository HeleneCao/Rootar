package com.rootar;

import com.rootar.metier.Pays;
import com.rootar.metier.Priorite;
import com.rootar.metier.Sante;
import com.rootar.metier.Ville;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.ServiceRootar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GestionAMSanteController {
    @FXML
    private Label titres;
    @FXML
    private TextField idSante;
    @FXML
    private TextField libelleSante;
    @FXML
    private ComboBox<Priorite> comboPriorite;
    @FXML
    private ChoiceBox<String> choiceBoxValidite;
    private Stage dialogStage;
    private ServiceRootar serviceRootar;
    private Sante santeSelected;
    private FenetreAlert fenetreAlert;
    private Pays paysSelected;
    @FXML
    private void initialize(){
        fenetreAlert=new FenetreAlert();
        serviceRootar=new ServiceRootar();
        idSante.setDisable(true);


        /*choiceBoxValidite.getItems().add(0,"Choisir Validité");
        choiceBoxValidite.getItems().add(1,"OUI");
        choiceBoxValidite.getItems().add(2,"NON");
        choiceBoxValidite.getSelectionModel().select(0);*/
        comboPriorite.setItems(FXCollections.observableArrayList(serviceRootar.getAllPriorite()));


    }
    @FXML
    private void confirmer(){
        Sante sante= new Sante();

        sante.setLibelleSante(libelleSante.getText());
        sante.setPriorite(comboPriorite.getValue());
        System.out.println(libelleSante.getText());

        if (dialogStage.getTitle().equals("Ajouter santé")) {
            serviceRootar.insertSante(sante);
        }
        if(dialogStage.getTitle().equals("Modifier santé")){
            sante.setIdSante(Integer.valueOf(idSante.getText()));
            serviceRootar.updateSante(sante);

        }
        dialogStage.close();
    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setSanteSelected(Sante santeSelected) {
        this.santeSelected = santeSelected;


    }

    public void afficherCat() {
        idSante.setDisable(true);
        idSante.setText(String.valueOf(santeSelected.getIdSante()));
        libelleSante.setText(santeSelected.getLibelleSante());
        comboPriorite.getSelectionModel().select(serviceRootar.getPrioriteBySante(santeSelected.getPriorite().getIdPriorite()));


    }
}
