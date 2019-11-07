package vincentfillon.model;

public class Evalue {
    Film film;
    Utilisateur utilisateur;

    int idFilm=0;

    public Evalue(int idFilm, int idUser, int note) {
        this.idFilm = idFilm;
        this.idUser = idUser;
        this.note = note;
    }

    int idUser=0;
    int note=0;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public Evalue(){}

    public Evalue(int idUser, int idFilm) {
        this.idUser= idUser;
        this.idFilm = idFilm;

    }
    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }


}
