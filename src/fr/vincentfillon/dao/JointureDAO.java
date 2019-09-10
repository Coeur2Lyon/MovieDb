package fr.vincentfillon.dao;

import fr.vincentfillon.model.Film;
import fr.vincentfillon.model.Jointure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class JointureDAO extends Dao<Jointure> {

    public JointureDAO(Connection connection) {
        super(connection); }

    @Override
    public void create(Jointure film) {
        String sqlInsert = "INSERT INTO moviedb.FILM (TitreFr, TitreO, Scenario, AnneeSortie,NationaliteF) VALUES('" + film.getTitreFR() + "','" + film.getTitreO() + "','" + film.getScenario() + "', '" + film.getAnneeSortie() + "','" + film.getNationalite() + "')";
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(sqlInsert);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Jointure obj) {

    }

    @Override
    public void update(Jointure obj) {

    }

    @Override
    public Jointure find(int idFilm) {

        Jointure jointure = new Jointure();
        String sqlMovieRequest =
                "SELECT * FROM FILM WHERE IdFilm = " + idFilm;

        String sqlJointureGenre =
                "SELECT Genre FROM FILM INNER JOIN CORRESPOND C on FILM.IdFilm = C.IdFilm INNER JOIN GENRE G on C.IdGenre = G.IdGenre WHERE FILM.IdFilm=" + idFilm;

        String sqlJointureRealisateur =
                "SELECT ACTEUR_REALISATEUR.Prenom,ACTEUR_REALISATEUR.Nom FROM FILM INNER JOIN REALISE R on FILM.IdFilm = R.IdFilm INNER JOIN ACTEUR_REALISATEUR ON R.IdActeurRealisateur=ACTEUR_REALISATEUR.IdActeurRealisateur WHERE FILM.IdFilm=" + idFilm;
        String sqlJointureActeurs =
                "SELECT ACTEUR_REALISATEUR.Prenom, ACTEUR_REALISATEUR.Nom FROM FILM INNER JOIN JOUE J on FILM.IdFilm = J.IdFilm INNER JOIN ACTEUR_REALISATEUR on J.IdActeurRealisateur = ACTEUR_REALISATEUR.IdActeurRealisateur WHERE FILM.IdFilm=" + idFilm;


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

                //boucle de la jointure genre:
                int genreSize = 1;
                resultGenre.last();
                genreSize = resultGenre.getRow();
                resultGenre.first();
                String genre=resultGenre.getString(1);
                if (genreSize>1){
                    resultGenre.next();
                    for (int i=2;i<genreSize;i++){
                        genre+=resultGenre.getString(1);
                        resultGenre.next();
                    }

                }

                int realisateurSize=1;
                resultRealisateur.last();
                realisateurSize=resultRealisateur.getRow();
                resultRealisateur.first();
                String realisateur =", "+resultRealisateur.getString(1) + " " + resultRealisateur.getString(2);
                if (realisateurSize>1){
                    resultRealisateur.next();
                    for(int i=2;i<realisateurSize;i++){
                        realisateur +=", "+resultRealisateur.getString(1) + " " + resultRealisateur.getString(2);
                        resultRealisateur.next();
                    }
                }


                int acteursSize = 1;
                resultActeurs.last();
                acteursSize = resultActeurs.getRow();
                resultActeurs.first();
                String acteurs = resultActeurs.getString(1) + " " + resultActeurs.getString(2);
                if(acteursSize>1){
                    for (int i = 1; i < acteursSize; i++) {
                        acteurs +=", "+ resultActeurs.getString(1) + " " + resultActeurs.getString(2);
                        resultActeurs.next();
                    }
                }


                jointure = new Jointure(titreFR,titreO,scenario,anneeSortie,nationalite,genre,realisateur,acteurs);
                resultFilm.close();
                resultActeurs.close();
                resultRealisateur.close();
                resultGenre.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return jointure;
    }

    @Override
    public ObservableList findAll() {
        ObservableList<Jointure> listefilms = FXCollections.observableArrayList();
        int i = 1;
        Jointure jointure = find(i);
        while (jointure.getTitreFR() != null) {
            if (jointure.getIsDeleted() == 0) {
                listefilms.add(jointure);
            }
            i++;
            jointure = find(i);
        }
        return listefilms;

    }
}

