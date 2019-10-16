package main.vincentfillon.dao;

import main.vincentfillon.model.ActeurRealisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.Date;

public class ActeurRealisateurDAO extends Dao<ActeurRealisateur> {

    public ActeurRealisateurDAO(Connection connection) {
        super(connection);
    }


    public void create(ActeurRealisateur acteurRealisateur) {
        int anneeNaissance = Integer.parseInt(acteurRealisateur.getAnneeNaissance());
        String insertRequest = "INSERT INTO moviedb.ACTEUR_REALISATEUR (Nom, Prenom, AnneeNaissance, NationaliteAR) VALUES('" + acteurRealisateur.getNom() + "','" + acteurRealisateur.getPrenom() + "','" + anneeNaissance + "', '" + acteurRealisateur.getNationalite() + "')";
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(insertRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(ActeurRealisateur acteurRealisateur) {
        String deleteRequest = "UPDATE moviedb.ACTEUR_REALISATEUR SET IsDeleted=1 WHERE IdActeurRealisateur=" + acteurRealisateur.getIdActeurRealisateur() + "";
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(deleteRequest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ActeurRealisateur acteurRealisateur) {
        int anneeNaissance = Integer.parseInt(acteurRealisateur.getAnneeNaissance());
        String updateRequest = "UPDATE moviedb.ACTEUR_REALISATEUR SET Nom='" + acteurRealisateur.getNom() + "', Prenom='" + acteurRealisateur.getPrenom() + "',  AnneeNaissance='" + anneeNaissance + "', NationaliteAR='" + acteurRealisateur.getNationalite() + "' WHERE IdActeurRealisateur=" + acteurRealisateur.getIdActeurRealisateur();
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(updateRequest);

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
                String anneeNaissance = result.getString(4);
                String nationalite = result.getString(5);
                Timestamp createdAt = result.getTimestamp(6);
                int isDeleted = result.getInt(7);
                result.close();

                acteurRealisateur = new ActeurRealisateur(idActeurRealisateur, nom, prenom, anneeNaissance, nationalite,createdAt,isDeleted);

                acteurRealisateur.setIdActeurRealisateur(idActeurRealisateur);
                acteurRealisateur.setNom(nom);
                acteurRealisateur.setPrenom(prenom);
                acteurRealisateur.setAnneeNaissance(anneeNaissance);
                acteurRealisateur.setNationalite(nationalite);
                acteurRealisateur.setCreatedAt(createdAt);
                acteurRealisateur.setIsDeleted(isDeleted);
                result.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return acteurRealisateur;
    }

    public ObservableList findAll() {

        ObservableList<ActeurRealisateur> listeActeursRealisateurs = FXCollections.observableArrayList();
        for (int i = 1; i <= findIdMax(); i++) {

            ActeurRealisateur acteurRealisateur = find(i);
            if (acteurRealisateur.getIsDeleted()==0) {
               listeActeursRealisateurs.add(acteurRealisateur);
            }
        }
        return listeActeursRealisateurs;
    }

    @Override
    public int findIdMax() {
        int idMax = 0;
        String sqlFindMax = "SELECT MAX(IdActeurRealisateur) FROM ACTEUR_REALISATEUR";
        try {
            ResultSet resultACtReal = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlFindMax);

            if (resultACtReal.first()) {
                idMax = resultACtReal.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idMax;
    }
}
