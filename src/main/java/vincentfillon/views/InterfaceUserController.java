package vincentfillon.views;

import vincentfillon.Main;
import vincentfillon.connectivity.ConnectionClass;
import vincentfillon.dao.Dao;
import vincentfillon.dao.FilmDAO;
import vincentfillon.dao.UtilisateurDAO;
import vincentfillon.model.Film;
import vincentfillon.model.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;
import java.time.Clock;

public class InterfaceUserController {

    @FXML
    private void addUser() {
        Dao<Utilisateur> utilisateurDao = new UtilisateurDAO(ConnectionClass.connecte());
        Utilisateur user=new Utilisateur();
        boolean okClicked=true;
        if (okClicked) {
            utilisateurDao.create(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Ajout d'utilisateur");
            alert.setHeaderText("Le film a bien été ajouté");
        }
    }
}
