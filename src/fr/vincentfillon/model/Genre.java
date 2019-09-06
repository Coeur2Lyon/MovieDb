package fr.vincentfillon.model;

import javafx.beans.property.StringProperty;

public class Genre {


    public Genre(int idGenre, StringProperty genre) {
        this.idGenre = idGenre;
        this.genre = genre;
    }

    private int idGenre=0;
    private StringProperty genre;

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
