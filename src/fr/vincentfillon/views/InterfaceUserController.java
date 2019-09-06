package fr.vincentfillon.views;

import fr.vincentfillon.Main;
import fr.vincentfillon.connectivity.ConnectionClass;
import fr.vincentfillon.dao.Dao;
import fr.vincentfillon.dao.FilmDAO;
import fr.vincentfillon.dao.UtilisateurDAO;
import fr.vincentfillon.model.Film;
import fr.vincentfillon.model.Utilisateur;
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
