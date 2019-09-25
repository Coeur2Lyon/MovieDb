package fr.vincentfillon.model;

import java.util.ArrayList;

public class Correspond {

    Film film;
    Genre genre;

    int idFilm=0;
    int idGenre=0;


    public Correspond() {}

    public Correspond(int idFilm, int idGenre) {
        this.idFilm = idFilm;
        this.idGenre = idGenre;
    }

    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }



}
