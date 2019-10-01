package fr.vincentfillon.dao;

import fr.vincentfillon.model.JointureActeursRealisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class JointureActeursRealisateurDAO extends Dao<JointureActeursRealisateur> {

    public JointureActeursRealisateurDAO(Connection connection) {
        super(connection);
    }


    @Override
    public void create(JointureActeursRealisateur jointureActeursRealisateur) {


        String nom = jointureActeursRealisateur.getNom();
        String prenom = jointureActeursRealisateur.getPrenom();
        int anneeNaissance = Integer.parseInt(jointureActeursRealisateur.getAnneeNaissance());
        String nationalite = jointureActeursRealisateur.getNationalite();


        String sqlInsertRequest = "INSERT INTO moviedb.ACTEUR_REALISATEUR (Nom, Prenom, AnneeNaissance, NationaliteAR) VALUES('" + nom + "','" + prenom + "','" + anneeNaissance + "', '" + nationalite + "')";

        //A la création, n assiqgne systématiquement l'acteur/Réalisateur d'id 10 qui correspond à un acteur vide.

        try {

            Statement statement = this.connect.createStatement();
            statement.executeUpdate(sqlInsertRequest);

        } catch (Exception e) {

        }

    }

    @Override
    public void delete(JointureActeursRealisateur jointureActeursRealisateur) {
        String deleteRequest = "UPDATE moviedb.ACTEUR_REALISATEUR SET IsDeleted=1 WHERE IdActeurRealisateur=" + jointureActeursRealisateur.getIdJointure() + "";
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(deleteRequest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(JointureActeursRealisateur jointureActeursRealisateur) {
        int anneeNaissance = Integer.parseInt(jointureActeursRealisateur.getAnneeNaissance());
        String updateRequest = "UPDATE moviedb.ACTEUR_REALISATEUR SET Nom='" + jointureActeursRealisateur.getNom() + "', Prenom='" + jointureActeursRealisateur.getPrenom() + "',  AnneeNaissance='" + anneeNaissance + "', NationaliteAR='" + jointureActeursRealisateur.getNationalite() + "' WHERE IdActeurRealisateur=" + jointureActeursRealisateur.getIdJointure();
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(updateRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public JointureActeursRealisateur find(int idJointure) {

        JointureActeursRealisateur jointureActeursRealisateur = new JointureActeursRealisateur();
        String sqlMovieRequest =
                "SELECT * FROM ACTEUR_REALISATEUR WHERE IdActeurRealisateur = " + idJointure;

        String sqlJointureJoue =
                "SELECT FILM.TitreFr,FILM.TitreO FROM FILM INNER JOIN JOUE J on FILM.IdFilm = J.IdFilm INNER JOIN ACTEUR_REALISATEUR ON J.IdActeurRealisateur=ACTEUR_REALISATEUR.IdActeurRealisateur WHERE ACTEUR_REALISATEUR.IdActeurRealisateur=" + idJointure;

        String sqlJointureRealise =
                "SELECT FILM.TitreFr,FILM.TitreO FROM FILM INNER JOIN REALISE R on FILM.IdFilm = R.IdFilm INNER JOIN ACTEUR_REALISATEUR ON R.IdActeurRealisateur=ACTEUR_REALISATEUR.IdActeurRealisateur WHERE ACTEUR_REALISATEUR.IdActeurRealisateur=" + idJointure;


        try {
            ResultSet resultActeursRealisateur = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlMovieRequest);

            ResultSet resultJoue = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlJointureJoue);
            ResultSet resultRealise = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlJointureRealise);


            if (resultActeursRealisateur.first()) {

                String nom = resultActeursRealisateur.getString(2);
                String prenom = resultActeursRealisateur.getString(3);
                int anneeNaissance = resultActeursRealisateur.getInt(4);
                String nationalite = resultActeursRealisateur.getString(5);
                Date createdAt = resultActeursRealisateur.getDate(6);
                Integer isDeleted = resultActeursRealisateur.getInt(7);
                String joue = "-";
                String realise = "-";
                if (resultJoue.first()) {
                    joue = resultJoue.getString(1) + "(" + resultJoue.getString(2) + ")";
                }

                if (resultRealise.first()) {
                    realise = resultRealise.getString(1) + "(" + resultRealise.getString(2) + ")";
                }

                String anneeNaissanceString = Integer.toString(anneeNaissance);


                //boucle de la construction de la String "realise" extraite de la jointure:
                int realiseSize = 1;
                resultRealise.last();
                realiseSize = resultRealise.getRow();
                resultRealise.first();

                if (realiseSize > 1) {
                    resultRealise.next();
                    for (int i = 2; i < realiseSize; i++) {
                        realise += ", " + resultRealise.getString(1) + " " + resultRealise.getString(2);
                        resultRealise.next();
                    }
                }
//boucle de la construction de la String "joue" extraite de la jointure:
                int joueSize = 1;
                resultJoue.last();
                joueSize = resultJoue.getRow();
                resultJoue.first();

                if (joueSize > 1) {
                    resultJoue.next();
                    for (int i = 1; i < joueSize; i++) {
                        joue += ", " + resultJoue.getString(1) + " " + resultJoue.getString(2);
                        resultJoue.next();
                    }
                }

                jointureActeursRealisateur = new JointureActeursRealisateur(idJointure, nom, prenom, anneeNaissanceString, nationalite, realise, joue, createdAt, isDeleted);

                resultActeursRealisateur.close();
                resultJoue.close();
                resultRealise.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jointureActeursRealisateur;
    }

    @Override
    public ObservableList findAll() {
        ObservableList<JointureActeursRealisateur> listeActeursRealisateur = FXCollections.observableArrayList();
        for (int i = 1; i <= findIdMax(); i++) {
            JointureActeursRealisateur jointureActeursRealisateur = find(i);

            if (jointureActeursRealisateur.getIsDeleted() == 0) {
                listeActeursRealisateur.add(jointureActeursRealisateur);
            }
        }
        return listeActeursRealisateur;
    }


    @Override
    public int findIdMax() {
        int idMax = 0;
        String sqlFindMax = "SELECT MAX(IdActeurRealisateur) FROM ACTEUR_REALISATEUR";
        try {
            ResultSet resultActeurRealisateurId = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlFindMax);

            if (resultActeurRealisateurId.first()) {
                idMax = resultActeurRealisateurId.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idMax;
    }

}
