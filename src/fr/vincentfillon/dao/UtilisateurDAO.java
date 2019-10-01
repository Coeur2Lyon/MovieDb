package fr.vincentfillon.dao;

import fr.vincentfillon.model.Utilisateur;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UtilisateurDAO extends Dao<Utilisateur>{

    public UtilisateurDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Utilisateur utilisateur) {
        String insertRequest = "INSERT INTO moviedb.UTILISATEUR (IdRole,Username, Password, Email, Birthday) VALUES('0','" + utilisateur.getUsername() + "','" + utilisateur.getPassword() + "','" + utilisateur.getEmail() + "', '" + utilisateur.getBirthday() + "')";
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(insertRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Utilisateur obj) {

    }

    @Override
    public void update(Utilisateur obj) {

    }

    @Override
    public Utilisateur find(int id) {
        return null;
    }

    @Override
    public ObservableList findAll() {
        return null;
    }

    @Override
    public int findIdMax() {
        return 0;
    }
}
