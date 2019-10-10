package fr.vincentfillon;

import fr.vincentfillon.model.JointureActeursRealisateur;
import fr.vincentfillon.model.JointureFilm;
import fr.vincentfillon.model.ListeCheckBox;
import fr.vincentfillon.views.ActRealEditDialogController;
import fr.vincentfillon.views.JoueEditDialogController;
import fr.vincentfillon.views.MovieEditDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
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

    public static boolean showMovieJoinEditDialog(JointureFilm jointureFilm, ListeCheckBox listeCheckBox) {
        try {
            // Charge le fichier FXML et crée une nouvelle fenêtre (Stage).
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/MovieEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Crée la fenêtre de dialogue:
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajout / Edition de films");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Paramètre le film dans le contrôlleur
            MovieEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMovie(jointureFilm);

            //Paramètre la liste de CheckBox(pour les genres) dans le contrôleur.
            controller.setListCheckBox(listeCheckBox);

            // Affiche la fenêtre de dialogue et attend une action de l'utilisateur.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean showActeurRealisateurEditDialog(JointureActeursRealisateur acteurRealisateur) {
        try {
            // Charge le fichier FXML et crée une nouvelle fenêtre (Stage).
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/ActRealEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            //Crée la fenêtre de dialogue
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Ajout / Edition d'acteurs/réalisateurs");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Paramètre les données de l'acteur/réalisateurr dans le contrôleur
            ActRealEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setActeurRealisateur(acteurRealisateur);

            // Ouvrir la boîte de dialog(show) et attendre une action de l'utilisateur.
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean showJoueEditDialog(JointureActeursRealisateur acteurRealisateur) {
        try {
            // Charge le fichier FXML et crée une nouvelle fenêtre (Stage).
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/JoueEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Crée la fenêtre de la boîte de dialogue.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Attribution d'un film à un acteur");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Définie/paramètre(Set) acteur dans controlôleur
            JoueEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setInfosActeur(acteurRealisateur);

            // Ouvrir la boîte de dialog(show) et attendre une action de l'utilisateur.
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