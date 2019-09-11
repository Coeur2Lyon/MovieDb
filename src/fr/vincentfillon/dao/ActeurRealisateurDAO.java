package fr.vincentfillon.dao;

import fr.vincentfillon.model.ActeurRealisateur;
import fr.vincentfillon.model.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class ActeurRealisateurDAO extends Dao<ActeurRealisateur> {

    public ActeurRealisateurDAO(Connection connection) {
        super(connection);
    }


    public void create(ActeurRealisateur acteurRealisateur) {

        String sqlInsert = "INSERT INTO moviedb.ACTEUR_REALISATEUR (Nom, Prenom, AnneeNaissance, NationaliteAR) VALUES('" + acteurRealisateur.getNom() + "','" + acteurRealisateur.getPrenom() + "','" + acteurRealisateur.getAnneeNaissance() + "', '" + acteurRealisateur.getNationalite() + "')";
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(sqlInsert);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(ActeurRealisateur acteurRealisateur) {
        String sqlUpdate = "UPDATE ACTEUR_REALISATEUR SET IsDeleted=1 WHERE IdActeurRealisateur=" + acteurRealisateur.getIdActeurRealisateur() + "";
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(sqlUpdate);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ActeurRealisateur acteurRealisateur) {
        int anneeNaissance = Integer.parseInt(acteurRealisateur.getAnneeNaissance());
        String sqlUpdate = "UPDATE ACTEUR_REALISATEUR SET Nom='" + acteurRealisateur.getNom() + "', Prenom='" + acteurRealisateur.getPrenom() + "',  AnneeNaissance='" + anneeNaissance + "', NationaliteF='" + acteurRealisateur.getNationalite() + "' WHERE IdActeurRealisateur=" + acteurRealisateur.getIdActeurRealisateur() + "";
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(sqlUpdate);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ActeurRealisateur find(int idActeurRealisateur) {
        ActeurRealisateur acteurRealisateur = new ActeurRealisateur();
        String sqlQuery = "SELECT * FROM ACTEUR_REALISATEUR WHERE IdActeurRealisateur = " + idActeurRealisateur;
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlQuery);

            if (result.first()) {
                String nom = result.getString(2);
                String prenom = result.getString(3);
                String anneeNaissance=result.getString(4);
                String nationalite = result.getString(5);
                Date createdAt = result.getDate(6);
                Integer isDeleted = result.getInt(7);
                result.close();
                acteurRealisateur = new ActeurRealisateur(nom,prenom,anneeNaissance,nationalite);
                result.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acteurRealisateur;
    }

    public ObservableList findAll() {

        ObservableList<ActeurRealisateur> listeActeursRealisateurs = FXCollections.observableArrayList();

        int i = 1;
        ActeurRealisateur acteurRealisateur = find(i);
        while (acteurRealisateur.getNom() != null) {
            if (acteurRealisateur.getIsDeleted() == 0) {
                listeActeursRealisateurs.add(acteurRealisateur);
            }
            i++;
            acteurRealisateur = find(i);
        }
        return listeActeursRealisateurs;
    }
}
