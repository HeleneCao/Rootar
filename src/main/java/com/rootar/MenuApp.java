package com.rootar;

import com.rootar.metier.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
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

    public void showEditEvent(Ville villeSelected, Evenements eventSelected, String titre) {
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
            controller.setVilleSelected(villeSelected);
            if (titre.equals("Ajouter evenement")) {
                controller.setVilleSelected(villeSelected);
            } else
                controller.afficherEvent(eventSelected);
            controller.setTitle(titre);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEditRepEtrangere(Pays paysSelected, Ville villeSelected , RepresentationEtrangere repEtrSelected, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("GestionAMSRepEtrangere.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(titre);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(anchorPane);
            GestionAMSRepEtrangereController controller = loader.getController();

            controller.setDialogStage(dialogStage);
            controller.setPaysSelected(paysSelected);
            controller.setVilleSelected(villeSelected);
            controller.setTitle(titre);
            if (titre.equals("Modifier représentation étrangère")){
                controller.afficherRepEtranger(repEtrSelected);
            }
            else
                controller.afficherAjouterRepEtrangere(paysSelected, villeSelected);
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

    public void showEditCat(Categories categoriesSelected, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("GestionAMSCat.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(titre);

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(anchorPane);
            GestionAMSCatController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            if (titre.equals("Ajouter catégorie")) {
                controller.setCatSelected(null);
            }
            if (titre.equals("Modifier catégorie")) {
                controller.afficherCat(categoriesSelected);
            }

            dialogStage.setScene(scene);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showEditObjet(Pays paysSelected, Categories categoriesSelected, Objet objetSelected, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("GestionAMSObjet.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(titre);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);

            Scene scene = new Scene(anchorPane);
            GestionAMSObjetController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPaysSelected(paysSelected);
            controller.setCategoriesSelected(categoriesSelected);
            controller.setTitle(titre);
            if (titre.equals("Modifier l'objet")){

                controller.afficherObjet(objetSelected);
            }
            else
                controller.afficherAjouterObjet(paysSelected, categoriesSelected);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showEditSante(Sante santeSelected, String titre) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("GestionAMSSante.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(titre);

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(anchorPane);
            GestionAMSanteController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            if (titre.equals("Ajouter santé")) {
                controller.setSanteSelected(santeSelected);
            }
            if (titre.equals("Modifier santé")) {
                controller.setSanteSelected(santeSelected);
                controller.afficherCat();

            }


            dialogStage.setScene(scene);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void showEditMap(String titre) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MenuApp.class.getResource("test.fxml"));
            AnchorPane anchorPane = (AnchorPane) loader.load();

            dialogStage = new Stage();
            dialogStage.setTitle(titre);

            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(anchorPane);
            TestController controller = loader.getController();
            controller.setDialogStage(dialogStage);



            dialogStage.setScene(scene);
            dialogStage.showAndWait();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
