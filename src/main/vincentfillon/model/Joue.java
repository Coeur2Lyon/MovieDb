package main.vincentfillon.model;

public class Joue {
    Film film;
    ActeurRealisateur acteurRealisateur;

    int idFilm=0;
    int idActeursRealisateur =0;

    public Joue(){}

    public Joue(int idActeursRealisateur, int idFilm) {
        this.idActeursRealisateur = idActeursRealisateur;
        this.idFilm = idFilm;

    }
    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public int getIdActeursRealisateur() {
        return idActeursRealisateur;
    }

    public void setIdActeursRealisateur(int idActeursRealisateur) {
        this.idActeursRealisateur = idActeursRealisateur;
    }


}
