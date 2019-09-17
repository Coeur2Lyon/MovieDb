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

        int idFilm=correspond.getIdFilm();
        int idGenre=correspond.getIdGenre();

        //String sqlFindMax="SELECT MAX(IdFilm) FROM FILM";

        System.out.println("Contrôle juste avant Insertion SQL:");
        System.out.println("L'Id du Film à insérer est :"+idFilm);
        System.out.println("L'Id du Genre à insérer est :"+idFilm);

       String sqlInsertCorrespond="INSERT INTO moviedb.CORRESPOND(IdGenre, IdFilm) VALUES ("+idGenre+"," + idFilm + ")";

        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(sqlInsertCorrespond);

            System.out.println("Id Jointure: " + idFilm);
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
}
