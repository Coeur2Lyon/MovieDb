package fr.vincentfillon.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Genre {

    private int idGenre=0;
    private StringProperty genre;

    public Genre(int idGenre, String genre) {
        this.idGenre = idGenre;
        this.genre = new SimpleStringProperty(genre);
    }
    public Genre() {
        this(null);
    }
    public Genre(String genre) {
        this.genre = new SimpleStringProperty(genre);
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getGenre() {
        return genre.get();
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre.set(genre);
    }

}
