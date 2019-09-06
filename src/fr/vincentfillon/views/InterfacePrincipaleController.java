package fr.vincentfillon.views;

import fr.vincentfillon.Main;
import fr.vincentfillon.model.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;


public class InterfacePrincipaleController extends Main {

    //private AnchorPane interfaceUser;
    private AnchorPane interfaceFilms;
    private fr.vincentfillon.Main main;
    private ObservableList<Film> personData = FXCollections.observableArrayList();

    //private static ObservableList<Film> movieData = FXCollections.observableArrayList();





    /*public static ObservableList<Film> getMovieData() {
        return movieData;
    }*/

    @FXML
    public void initInterfaceFilms(ActionEvent actionEvent) throws IOException {

        Parent root;
        Stage newWindow = new Stage();
        try {
            //FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(InterfacePrincipaleController.class.getResource("InterfaceFilmsAdmin.fxml"));
            //AnchorPane interfaceFilms = (AnchorPane) loader.load();
            //InterfaceFilmsAdminController controller;
            //controller = loader.getController();
            // controller.setMovie(this);
            root = FXMLLoader.load(getClass().getResource("InterfaceFilmsAdmin.fxml"));
            newWindow.setScene(new Scene(root,850,642));
            newWindow.initModality(Modality.APPLICATION_MODAL);

            newWindow.show();



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initInterfaceUser(ActionEvent actionEvent) {
        Stage newWindow = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("InterfaceUser.fxml"));
            newWindow.setScene(new Scene(root, 500, 600));
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
