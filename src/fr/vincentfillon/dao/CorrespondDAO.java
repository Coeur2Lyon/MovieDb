package fr.vincentfillon.dao;

import fr.vincentfillon.model.Correspond;
import fr.vincentfillon.model.Film;
import fr.vincentfillon.model.Genre;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CorrespondDAO extends Dao<Correspond> {

    public CorrespondDAO(Connection conn) {
        super(conn);
    }

    @Override
    public void create(Correspond correspond) {
        System.out.println("Id du Film dans CorrespondDAO/create() :"+correspond.getIdFilm());
        System.out.println("Id du Genre dans CorrespondDAO/create():"+correspond.getIdGenre());

        int idFilm=correspond.getIdFilm();
        int idGenre=correspond.getIdGenre();

       // String sqlFindMax="SELECT MAX(IdFilm) FROM FILM";

        System.out.println("Contrôle juste avant Insertion SQL:");
        System.out.println("L'Id du Film à insérer est :"+idFilm);
        System.out.println("L'Id du Genre à insérer est :"+idGenre);

       String sqlInsertCorrespond="INSERT INTO moviedb.CORRESPOND(IdGenre, IdFilm) VALUES ("+idGenre+"," + idFilm + ")";
//Attribution de l'acteur/réal. d'id 10 (Acteur fantôme.
        String sqLInsertRealisateurREALISE = "INSERT INTO moviedb.REALISE(IdActeurRealisateur, IdFilm) VALUES (10," + idFilm+ ")";
        String sqlInsertActeursJOUE = "INSERT INTO moviedb.JOUE(IdActeurRealisateur, IdFilm) VALUES (10," + idFilm+ ")";

        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(sqlInsertCorrespond);
            statement.executeUpdate(sqLInsertRealisateurREALISE);
            statement.executeUpdate(sqlInsertActeursJOUE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Correspond obj) {

    }

    @Override
    public void update(Correspond obj) {

    }

    @Override
    public Correspond find(int id) {
        return null;
    }

    @Override
    public ObservableList findAll() {
        return null;
    }

    @Override
    public int findIdfromTitre(String titre) {
        return 0;
    }

    @Override
    public int findIdMax() {
        return 0;
    }
}
