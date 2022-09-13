package com.rootar;

import com.rootar.metier.Evenements;
import com.rootar.metier.Pays;
import com.rootar.metier.Region;
import com.rootar.metier.Ville;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuApp extends Application {
    private Stage primaryStage;
    private Stage dialogStage;

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void showRootar() {
        try {
            // Chargement du fichier fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("GestionRootar.fxml"));
            AnchorPane menuLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(menuLayout);
            primaryStage.setScene(scene);

            RootarController controller = loader.getController();
            controller.setMenuApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showDetailsPlus(Pays paysSelected) {
        try {
            // Chargement du fichier fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("GestionDetailsPlus.fxml"));
            AnchorPane menuLayout = (AnchorPane) loader.load();

            Scene scene = new Scene(menuLayout);
            dialogStage = new Stage();
            primaryStage.setScene(scene);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            DetailsPLusController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPaysSelected(paysSelected);
            if (paysSelected != null) {
                controller.afficherRegion(paysSelected);
            }
            controller.setMenuApp(this);

            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showEdit(Pays paysSelected, String titre) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("GestionAMS.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(titre);

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(anchorPane);
            GestionAMSController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            if (paysSelected != null)

                controller.afficherPays(paysSelected);
            controller.setTitle(titre);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditVille(Pays paysSelected, Ville ville, Region regionSelected, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("GestionAMSVille.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(titre);

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(anchorPane);
            GestionAMSVilleController controller = loader.getController();

            controller.setDialogStage(dialogStage);
            controller.setPaysSelected(paysSelected);
            controller.setTitle(titre);
            if (titre.equals("Modifier ville"))
                controller.afficherVille(ville);
            else
                controller.afficherAjouterVille(regionSelected);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showEditEvent(Evenements eventSelected, String titre) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("GestionAMSEvent.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(titre);

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(anchorPane);
            GestionAMSEvenementController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setVilleSelected(eventSelected.getVille());
            if (titre.equals("Ajouter evenement")){
                controller.setVilleSelected(eventSelected.getVille());
            }
            else
                controller.afficherEvent(eventSelected);
            controller.setTitle(titre);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Rootar");
        showRootar();


    }
}