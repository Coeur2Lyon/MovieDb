package fr.vincentfillon.views;

import fr.vincentfillon.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class InterfacePrincipaleController extends Main {


    private AnchorPane interfaceFilms;
    private AnchorPane interfaceJointureFilms;
    private fr.vincentfillon.Main main;


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
    public void initInterfaceJointureFilms(ActionEvent actionEvent) throws IOException {

        Parent root;
        Stage newWindow = new Stage();
        try {
            root = FXMLLoader.load(getClass().getResource("InterfaceFilmsAdmin.fxml"));
            newWindow.setScene(new Scene(root,850,642));
            newWindow.initModality(Modality.APPLICATION_MODAL);

            newWindow.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initInterfaceActeursRealisateurs(ActionEvent actionEvent) throws IOException {

        Parent root;
        Stage newWindow = new Stage();
        try {
            root = FXMLLoader.load(getClass().getResource("InterfaceActeursRealisateursAdmin.fxml"));
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
