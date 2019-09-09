package fr.vincentfillon.dao;

import fr.vincentfillon.model.Jointure;
import javafx.collections.ObservableList;

import java.sql.Connection;

public class JointureDAO extends Dao<Jointure> {

    public JointureDAO(Connection connection) {
        super(connection);
    }

    String sqlJointure =
            "SELECT TitreFr,TitreO, Scenario, AnneeSortie, NationaliteF,Genre, ACTEUR.Prenom, ACTEUR.Nom, REALISATEUR.Prenom,REALISATEUR.Nom" +
                    "FROM FILM" +
                    "INNER JOIN CORRESPOND C on FILM.IdFilm = C.IdFilm" +
                    "INNER JOIN GENRE G on C.IdGenre = G.IdGenre" +
                    "INNER JOIN JOUE J on FILM.IdFilm = J.IdFilm" +
                    "INNER JOIN ACTEUR_REALISATEUR ACTEUR on J.IdActeurRealisateur = ACTEUR.IdActeurRealisateur" +
                    "INNER JOIN REALISE R on FILM.IdFilm = R.IdFilm" +
                    "INNER JOIN ACTEUR_REALISATEUR REALISATEUR on R.IdActeurRealisateur = REALISATEUR.IdActeurRealisateur'";



    @Override
    public void create(Jointure obj) {

    }

    @Override
    public void delete(Jointure obj) {

    }

    @Override
    public void update(Jointure obj) {

    }

    @Override
    public Jointure find(int id) {
        return null;
    }

    @Override
    public ObservableList findAll() {
        return null;
    }
}

