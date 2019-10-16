package main.vincentfillon.dao;


import main.vincentfillon.model.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;


public class FilmDAO extends Dao<Film> {

    public FilmDAO(Connection connection) {
        super(connection);
    }


    public void create(Film film) {

        String insertRequest = "INSERT INTO moviedb.FILM (TitreFr, TitreO, Scenario, AnneeSortie,NationaliteF) VALUES('" + film.getTitreFR() + "','" + film.getTitreO() + "','" + film.getScenario() + "', '" + film.getAnneeSortie() + "','" + film.getNationalite() + "')";
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(insertRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Film film) {
        String updateRequest = "UPDATE FILM SET IsDeleted=1 WHERE IdFilm=" + film.getIdFilm() + "";
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(updateRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Film film) {
        int anneeSortie = Integer.parseInt(film.getAnneeSortie());
        String updateRequest = "UPDATE FILM SET TitreFr='" + film.getTitreFR() + "', TitreO='" + film.getTitreO() + "', Scenario='" + film.getScenario() + "', AnneeSortie='" + anneeSortie + "', NationaliteF='" + film.getNationalite() + "' WHERE IdFilm=" + film.getIdFilm() + "";

        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(updateRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Film find(int idFilm) {
        Film film = new Film();
        String sqlQuery = "SELECT * FROM FILM WHERE IdFilm = " + idFilm;
        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlQuery);

            if (result.first()) {
                String titreFR = result.getString(2);
                String titreO = result.getString(3);
                String scenario = result.getString(4);
                String anneeSortie = result.getString(5);
                String nationalite = result.getString(6);
                Date createdAt = result.getDate(7);
                Integer isDeleted = result.getInt(8);
                result.close();
                film = new Film(idFilm, titreFR, titreO, scenario, anneeSortie, nationalite, createdAt, isDeleted);
                result.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return film;

    }

    public ObservableList findAll() {

        ObservableList<Film> listefilms = FXCollections.observableArrayList();

        int i = 1;
        Film film = find(i);
        while (film.getTitreFR() != null) {
            if (film.getIsDeleted() == 0) {
                listefilms.add(film);
            }
            i++;
            film = find(i);
        }
        return listefilms;
    }


    public int findIdfromTitre(String titre) {
        return 0;
    }

    @Override
    public int findIdMax() {
        return 0;
    }
}
