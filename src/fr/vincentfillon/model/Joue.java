package fr.vincentfillon.model;

public class Joue {
    Film film;
    ActeurRealisateur acteurRealisateur;

    int idFilm=0;
    int idActeurRealisateur=0;

    public Joue(){}

    public Joue(int idActeurRealisateur, int idFilm) {
        this.idActeurRealisateur = idActeurRealisateur;
        this.idFilm = idFilm;

    }
    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    public int getIdActeurRealisateur() {
        return idActeurRealisateur;
    }

    public void setIdActeurRealisateur(int idActeurRealisateur) {
        this.idActeurRealisateur = idActeurRealisateur;
    }


}
