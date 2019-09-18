package fr.vincentfillon.dao;

import fr.vincentfillon.model.ActeurRealisateur;
import fr.vincentfillon.model.Genre;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class GenreDAO extends Dao<Genre> {

    public GenreDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Genre obj) {

    }

    @Override
    public void delete(Genre obj) {

    }

    @Override
    public void update(Genre obj) {

    }

    @Override
    public Genre find(int idGenre) {
        Genre genre = new Genre();
        String sqlQuery = "SELECT * FROM GENRE WHERE IdGenre = " + idGenre;
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlQuery);

            if (result.first()) {
                String nomGenre = result.getString(2);
                result.close();
                genre = new Genre(idGenre,nomGenre);
                result.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genre;

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
