package fr.vincentfillon.dao;

import fr.vincentfillon.model.Genre;
import javafx.collections.ObservableList;

import java.sql.Connection;

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
    public Genre find(int id) {
        return null;
    }

    @Override
    public ObservableList findAll() {
        return null;
    }
}
