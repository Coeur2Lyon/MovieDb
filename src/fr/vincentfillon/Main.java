package fr.vincentfillon;

import fr.vincentfillon.model.ActeurRealisateur;
import fr.vincentfillon.model.Jointure;
import fr.vincentfillon.model.ListeCheckBox;
import fr.vincentfillon.views.ActRealEditDialogController;
import fr.vincentfillon.views.MovieJoinEditDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        // this.primaryStage.setTitle("AddressApp");
        initInterfacePrncipale();
    }

    private void initInterfacePrncipale() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("views/InterfacePrincipale.fxml"));
            primaryStage.setTitle("MovieDB: Connexion");
            primaryStage.setScene(new Scene(root, 600, 600));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Fichier utilisé : Applel de la méthode à partir de la classe film uniquement
    public static boolean showMovieJoinEditDialog(Jointure jointure, ListeCheckBox listeCheckBox) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/MovieJoinEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajout / Edition de films");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Movie into the controller.
            MovieJoinEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMovie(jointure);

            //set the Checkbox into the controller

            controller.setListCheckBox(listeCheckBox);
            System.out.println("listeCheckBox.isCboxThriller() dans la méthode: showMovieJoinEditDialog()"+listeCheckBox.isCboxThriller());
            System.out.println("listeCheckBox.isCboxPolicier()dans la méthode: showMovieJoinEditDialog())"+listeCheckBox.isCboxPolicier());


            // Set the dialog icon.
            //dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean showActeurRealisateurEditDialog(ActeurRealisateur acteurRealisateur) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/ActRealEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajout / Edition d'acteurs/réalisateurs");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Movie into the controller.
            ActRealEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setActeurRealisateur(acteurRealisateur);

            // Set the dialog icon.
            //dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    //TODO:Effacer ublic static boolean showMovieEditDialog_SAUVEGARDE(Film film) quand ça marchera.
    //Fichier utilisé : Applel de la méthode à partir de la classe film uniquement
   /* public static boolean showMovieEditDialog_SAUVEGARDE(Film film) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/MovieEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajout / Edition de films");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Movie into the controller.
            MovieEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMovie(film);

            // Set the dialog icon.
            dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    */
    public static boolean showFiltreDialog() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/FiltreDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Choix des filtres");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Movie into the controller.
            MovieJoinEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            //controller.setMovie(film);

            // Set the dialog icon.
            dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}