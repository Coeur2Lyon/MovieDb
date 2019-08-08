package fr.vincentfillon.views;

import fr.vincentfillon.Main;
import fr.vincentfillon.connectivity.ConnectionClass;
import fr.vincentfillon.model.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
        /* Test de connexion à la BDD  si besoin
        Connection connection= ConnectionClass.connecte();

        try {
            String query="SELECT * FROM utilisateur";
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while(resultSet.next()){
                System.out.println(resultSet.getString(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }*/

        Parent root;
        Stage newWindow = new Stage();
        try {
            //FXMLLoader loader = new FXMLLoader();
            //loader.setLocation(InterfacePrincipaleController.class.getResource("InterfaceFilms.fxml"));
            //AnchorPane interfaceFilms = (AnchorPane) loader.load();
            //InterfaceFilmsController controller;
            //controller = loader.getController();
            // controller.setMovie(this);
            root = FXMLLoader.load(getClass().getResource("InterfaceFilms.fxml"));
            newWindow.setScene(new Scene(root,850,550));
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
