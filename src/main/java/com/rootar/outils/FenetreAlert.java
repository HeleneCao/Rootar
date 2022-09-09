package com.rootar.outils;

import javafx.scene.control.Alert;

public class FenetreAlert {
    public FenetreAlert() {
    }
    public void fenetreInformation(String titre, String message){

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titre);

        // Header Text: null
        alert.setHeaderText(titre);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
