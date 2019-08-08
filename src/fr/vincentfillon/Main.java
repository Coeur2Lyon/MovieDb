package fr.vincentfillon;

import fr.vincentfillon.model.Film;
import fr.vincentfillon.views.MovieEditDialogController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    public void start(Stage primaryStage){
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
        }
        catch( IOException e){
            e.printStackTrace();
        }
    }
    public static boolean showMovieEditDialog(Film film) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/MovieEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Movie");
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
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);

    }
}