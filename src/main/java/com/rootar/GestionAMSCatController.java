package com.rootar;

import com.rootar.metier.Categories;
import com.rootar.outils.FenetreAlert;
import com.rootar.service.ServiceRootar;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GestionAMSCatController {
    private Stage dialogStage;
    private ServiceRootar serviceRootar;
    private Categories catSelected;
    @FXML
    private TextField idCat;

    @FXML
    private TextField libelleCat;
    @FXML
    private void initialize() {
        catSelected=new Categories();
        serviceRootar = new ServiceRootar();
        idCat.setDisable(true);
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    @FXML
    public void confirmer(){
        Categories categories = new Categories();
        categories.setLibelleCategories(libelleCat.getText());
        if(dialogStage.getTitle().equals("Ajouter catégorie")){
            serviceRootar.insertCategories(categories);
        }

    }
    @FXML
    public void annuler() {

        dialogStage.close();
    }
    public void setCatSelected(Categories catSelected) {
        this.catSelected = catSelected;
    }
    public void afficherCat(Categories categories){
        idCat.setText(String.valueOf(categories.getIdCategories()));
        libelleCat.setText(String.valueOf(categories.getLibelleCategories()));

    }
}
