package fr.vincentfillon.dao;


import fr.vincentfillon.model.Film;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static javafx.scene.input.KeyCode.T;


public class FilmDAO extends Dao<Film> {

    public FilmDAO(Connection connection) {
        super(connection);
    }

    public boolean create(Film obj) {
        return false;
    }

    public boolean delete(Film obj) {
        return false;
    }

    public boolean update(Film obj) {
        return false;
    }

    public Film find(int id) {
       Film film=new Film();
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    //ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM film WHERE elv_id = " + id);
                    ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM film");
            if (result.first()){
                String titreFR = result.getString(2);
                String titreO = result.getString(3);
                String nationalite = result.getString(4);
                String scenario = result.getString(5);
                String anneeSortie = result.getString(6);
                Film film = new Film(titreFR, titreO, nationalite, scenario, anneeSortie);
                list = List<T>;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }
}
/*
 while (resultSet.next()) {
                        int idFilm = resultSet.getInt(1);
                        String titreFR = resultSet.getString(2);
                        String titreO = resultSet.getString(3);
                        String nationalite = resultSet.getString(4);
                        String scenario = resultSet.getString(5);
                        String anneeSortie = resultSet.getString(6);
                        Timestamp createdAt = resultSet.getTimestamp(8);
                        Film film = new Film(idFilm, titreFR, titreO, nationalite, scenario, anneeSortie);
                        list = (List<T>) film;
 */