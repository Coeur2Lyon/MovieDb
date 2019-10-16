package main.vincentfillon.dao;

import main.vincentfillon.model.JointureFilm;
import main.vincentfillon.model.Joue;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class RealiseDAO extends Dao<Joue> {


    public RealiseDAO(Connection conn) {
        super(conn);
    }

    @Override
    public void create(Joue joue) {
        int idFilm = joue.getIdFilm();
        int idActeurRealisateur = joue.getIdActeursRealisateur();
        String sqlInsertActeursJOUE = "INSERT INTO moviedb.JOUE(IdActeurRealisateur, IdFilm) VALUES (" + idActeurRealisateur + "," + idFilm + ")";
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(sqlInsertActeursJOUE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int extractIdSelectedFilmFromJointure(JointureFilm jointureFilm) {
        int extractedId=jointureFilm.getIdJointure();
        return extractedId;
    }

    @Override
    public void delete(Joue obj) {

    }

    @Override
    public void update(Joue obj) {

    }

    @Override
    public Joue find(int id) {
        return null;
    }

    @Override
    public ObservableList findAll() {
        return null;
    }


    @Override
    public int findIdMax() {
        return 0;
    }
}
