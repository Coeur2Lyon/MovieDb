package fr.vincentfillon.dao;

import fr.vincentfillon.model.JointureFilm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class JointureFilmDAO extends Dao<JointureFilm> {

    public JointureFilmDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(JointureFilm jointureFilm) {

        String titreF = jointureFilm.getTitreFR();
        String titreO = jointureFilm.getTitreO();
        String scenario = jointureFilm.getScenario();
        int anneeSortie = Integer.parseInt(jointureFilm.getAnneeSortie());
        String nationalite = jointureFilm.getNationalite();


        String sqlInsertFilmRequest = "INSERT INTO moviedb.FILM (TitreFr, TitreO, Scenario, AnneeSortie,NationaliteF) VALUES('" + titreF + "','" + titreO + "','" + scenario + "', '" + anneeSortie + "','" + nationalite + "')";

        //A la création, n assiqgne systématiquement l'acteur/Réalisateur d'id 10 qui correspond à un acteur vide.

        try {

            Statement statement = this.connect.createStatement();
            statement.executeUpdate(sqlInsertFilmRequest);

        } catch (Exception e) {

        }

    }

    @Override
    public void delete(JointureFilm jointureFilm) {
        String updateRequest = "UPDATE FILM SET IsDeleted=1 WHERE IdFilm=" + jointureFilm.getIdJointure() + "";
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(updateRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(JointureFilm jointureFilm) {
        String titreF = jointureFilm.getTitreFR();
        String titreO = jointureFilm.getTitreO();
        String scenario = jointureFilm.getScenario();
        int anneeSortie = Integer.parseInt(jointureFilm.getAnneeSortie());
        String nationalite = jointureFilm.getNationalite();
        int idJointure;
        idJointure = jointureFilm.getIdJointure();

        //String updateRequest = "UPDATE moviedb.FILM SET TitreFr='" + jointureFilm.getTitreFR() + "', TitreO='" + jointureFilm.getTitreO() + "', Scenario='" + jointureFilm.getScenario() + "', AnneeSortie='" +anneeSortie + "', NationaliteF='" + jointureFilm.getNationalite() + "' WHERE idJointure=" + jointureFilm.getIdJointure() + ";";
        //String updateRequest="UPDATE moviedb.FILM SET TitreFr='tEST MODIF', TitreO='TEST TEST', Scenario='TEST', AnneeSortie=1963, NationaliteF='FR' WHERE idJointure=3";

        String updateRequest = "UPDATE moviedb.FILM SET TitreFr='" + titreF + "', TitreO='" + titreO + "', Scenario='" + scenario + "', AnneeSortie=" + anneeSortie + ", NationaliteF='" + nationalite + "' WHERE FILM.IdFilm=" + idJointure + "";

        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(updateRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public JointureFilm find(int idJointure) {

        JointureFilm jointureFilm = new JointureFilm();
        String sqlMovieRequest =
                "SELECT * FROM FILM WHERE IdFilm = " + idJointure;

        String sqlJointureGenre =
                "SELECT Genre FROM FILM INNER JOIN CORRESPOND C on FILM.IdFilm = C.IdFilm INNER JOIN GENRE G on C.IdGenre = G.IdGenre WHERE FILM.IdFilm=" + idJointure;

        String sqlJointureRealisateur =
                "SELECT ACTEUR_REALISATEUR.Prenom,ACTEUR_REALISATEUR.Nom FROM FILM INNER JOIN REALISE R on FILM.IdFilm = R.IdFilm INNER JOIN ACTEUR_REALISATEUR ON R.IdActeurRealisateur=ACTEUR_REALISATEUR.IdActeurRealisateur WHERE FILM.IdFilm=" + idJointure;
        String sqlJointureActeurs =
                "SELECT ACTEUR_REALISATEUR.Prenom, ACTEUR_REALISATEUR.Nom FROM FILM INNER JOIN JOUE J on FILM.IdFilm = J.IdFilm INNER JOIN ACTEUR_REALISATEUR on J.IdActeurRealisateur = ACTEUR_REALISATEUR.IdActeurRealisateur WHERE FILM.IdFilm=" + idJointure;


        try {
            ResultSet resultFilm = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlMovieRequest);

            ResultSet resultGenre = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlJointureGenre);
            ResultSet resultRealisateur = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlJointureRealisateur);
            ResultSet resultActeurs = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlJointureActeurs);


            if (resultFilm.first() && resultGenre.first() && resultRealisateur.first() && resultActeurs.first()) {
                String titreFR = resultFilm.getString(2);
                String titreO = resultFilm.getString(3);
                String scenario = resultFilm.getString(4);
                String anneeSortie = resultFilm.getString(5);
                String nationalite = resultFilm.getString(6);
                Date createdAt = resultFilm.getDate(7);
                Integer isDeleted = resultFilm.getInt(8);
                String genre = resultGenre.getString(1);
                String realisateur = resultRealisateur.getString(1) + " " + resultRealisateur.getString(2);
                String acteurs = resultActeurs.getString(1) + " " + resultActeurs.getString(2);


                //boucle de la construction de la String "genre" extraite de la jointure:
                int genreSize = 1;
                resultGenre.last();
                genreSize = resultGenre.getRow();
                resultGenre.first();

                if (genreSize > 1) {
                    resultGenre.next();
                    for (int i = 2; i <= genreSize; i++) {
                        genre += ", " + resultGenre.getString(1);
                        resultGenre.next();
                    }

                }
//boucle de la construction de la String "realisateur" extraite de la jointure:
                int realisateurSize = 1;
                resultRealisateur.last();
                realisateurSize = resultRealisateur.getRow();
                resultRealisateur.first();

                if (realisateurSize > 1) {
                    resultRealisateur.next();
                    for (int i = 2; i < realisateurSize; i++) {
                        realisateur += ", " + resultRealisateur.getString(1) + " " + resultRealisateur.getString(2);
                        resultRealisateur.next();
                    }
                }
//boucle de la construction de la String "acteurs" extraite de la jointure:
                int acteursSize = 1;
                resultActeurs.last();
                acteursSize = resultActeurs.getRow();
                resultActeurs.first();

                if (acteursSize > 1) {
                    resultActeurs.next();
                    for (int i = 1; i < acteursSize; i++) {
                        acteurs += ", " + resultActeurs.getString(1) + " " + resultActeurs.getString(2);
                        resultActeurs.next();
                    }
                }

                if (genre == null) {
                    genre = "";

                }
                if (realisateur == null) {
                    realisateur = "";
                }
                if (acteurs == null) {
                    acteurs = "";
                }


                jointureFilm = new JointureFilm(idJointure, titreFR, titreO, scenario, anneeSortie, nationalite, genre, realisateur, acteurs, createdAt, isDeleted);

                resultFilm.close();
                resultActeurs.close();
                resultRealisateur.close();
                resultGenre.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jointureFilm;
    }

    @Override
    public ObservableList findAll() {
        ObservableList<JointureFilm> listeFilms = FXCollections.observableArrayList();
        for (int i = 1; i <= findIdMax(); i++) {
            JointureFilm jointureFilm = find(i);

            if (jointureFilm.getIsDeleted() == 0) {
                listeFilms.add(jointureFilm);
            }
        }


        /*
        int i = 1;
        JointureFilm jointure = find(i);
        while (jointure.getTitreFR() != null) {
            if (jointure.getIsDeleted() == 0) {
                listeFilms.add(jointure);
            }
            i++;
            jointure = find(i);
        }*/
        return listeFilms;
    }


    public int findIdfromTitre(String titre) {
        int id = 0;


        return id;
    }

    @Override
    public int findIdMax() {
        int idMax = 0;
        String sqlFindMax = "SELECT MAX(IdFilm) FROM FILM";
        try {
            ResultSet resultFilm = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlFindMax);

            if (resultFilm.first()) {
                idMax = resultFilm.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idMax;
    }
}

