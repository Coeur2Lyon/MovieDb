package fr.vincentfillon.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

public class Jointure {

    GregorianCalendar cal = new GregorianCalendar(2007, 9 - 1, 23);
    long millis = cal.getTimeInMillis();

    int idJointure = 0;
    private StringProperty titreFR;
    private StringProperty titreO;
    private StringProperty scenario;
    private StringProperty anneeSortie;
    private StringProperty nationalite;
    private StringProperty genre;


    private StringProperty realisateurs;
    private StringProperty acteurs;

    private int isDeleted;
    private Timestamp createdAt = new Timestamp(millis);

    private StringProperty prenomRealisateurs;
    private StringProperty nomRealisateurs;
    private StringProperty prenomActeurs;
    private StringProperty nomActeurs;

    private Object StringProperty;

    public Jointure(int idJointure, String titreFR, String titreO, String scenario, String anneeSortie, String nationalite, String genre, String realisateurs, String acteurs, Date createdAt, int isDeleted) {
        this.idJointure = idJointure;
        this.titreFR = new SimpleStringProperty(titreFR);
        this.titreO = new SimpleStringProperty(titreO);
        this.scenario = new SimpleStringProperty(scenario);
        this.anneeSortie = new SimpleStringProperty(anneeSortie);
        this.nationalite = new SimpleStringProperty(nationalite);
        this.genre = new SimpleStringProperty(genre);
        this.realisateurs = new SimpleStringProperty(realisateurs);
        this.acteurs = new SimpleStringProperty(acteurs);
        this.createdAt = new Timestamp(createdAt.getTime());
        this.isDeleted = isDeleted;
    }

    public Jointure() {
        this(0,null, null, null, null, null, null, null, null);
    }

    public Jointure(String titreFR, String titreO, String scenario, String anneeSortie, String nationalite, String genre, String realisateurs, String acteurs) {
        this.titreFR = new SimpleStringProperty(titreFR);
        this.titreO = new SimpleStringProperty(titreO);
        this.scenario = new SimpleStringProperty(scenario);
        this.anneeSortie = new SimpleStringProperty(anneeSortie);
        this.nationalite = new SimpleStringProperty(nationalite);
        this.genre = new SimpleStringProperty(genre);
        this.realisateurs = new SimpleStringProperty(realisateurs);
        this.acteurs = new SimpleStringProperty(acteurs);
    }

    public Jointure(int idJointure, String titreFR, String titreO, String scenario, String anneeSortie, String nationalite, String genre, String realisateurs, String acteurs) {
        this.idJointure=idJointure;
        this.titreFR = new SimpleStringProperty(titreFR);
        this.titreO = new SimpleStringProperty(titreO);
        this.scenario = new SimpleStringProperty(scenario);
        this.anneeSortie = new SimpleStringProperty(anneeSortie);
        this.nationalite = new SimpleStringProperty(nationalite);
        this.genre = new SimpleStringProperty(genre);
        this.realisateurs = new SimpleStringProperty(realisateurs);
        this.acteurs = new SimpleStringProperty(acteurs);
    }

    public int getIdJointure() {
        return idJointure;
    }

    public void setIdJointure(int idJointure) {
        this.idJointure = idJointure;
    }

    public String getTitreFR() {
        return titreFR.get();
    }

    public StringProperty titreFRProperty() {
        return titreFR;
    }

    public void setTitreFR(String titreFR) {
        this.titreFR.set(titreFR);
    }

    public String getTitreO() {
        return titreO.get();
    }

    public StringProperty titreOProperty() {
        return titreO;
    }

    public void setTitreO(String titreO) {
        this.titreO.set(titreO);
    }

    public String getScenario() {
        return scenario.get();
    }

    public StringProperty scenarioProperty() {
        return scenario;
    }

    public void setScenario(String scenario) {
        this.scenario.set(scenario);
    }

    public String getAnneeSortie() {
        return anneeSortie.get();
    }

    public StringProperty anneeSortieProperty() {
        return anneeSortie;
    }

    public void setAnneeSortie(String anneeSortie) {
        this.anneeSortie.set(anneeSortie);
    }

    public String getNationalite() {
        return nationalite.get();
    }

    public StringProperty nationaliteProperty() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite.set(nationalite);
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

    public String getRealisateurs() {
        return realisateurs.get();
    }

    public StringProperty realisateursProperty() {
        return realisateurs;
    }

    public void setRealisateurs(String realisateurs) {
        this.realisateurs.set(realisateurs);
    }

    public String getActeurs() {
        return acteurs.get();
    }

    public StringProperty acteursProperty() {
        return acteurs;
    }

    public void setActeurs(String acteurs) {
        this.acteurs.set(acteurs);
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public String getPrenomRealisateurs() {
        return prenomRealisateurs.get();
    }

    public StringProperty prenomRealisateursProperty() {
        return prenomRealisateurs;
    }

    public void setPrenomRealisateurs(String prenomRealisateurs) {
        this.prenomRealisateurs.set(prenomRealisateurs);
    }

    public String getNomRealisateurs() {
        return nomRealisateurs.get();
    }

    public StringProperty nomRealisateursProperty() {
        return nomRealisateurs;
    }

    public void setNomRealisateurs(String nomRealisateurs) {
        this.nomRealisateurs.set(nomRealisateurs);
    }

    public String getPrenomActeurs() {
        return prenomActeurs.get();
    }

    public StringProperty prenomActeursProperty() {
        return prenomActeurs;
    }

    public void setPrenomActeurs(String prenomActeurs) {
        this.prenomActeurs.set(prenomActeurs);
    }

    public String getNomActeurs() {
        return nomActeurs.get();
    }

    public StringProperty nomActeursProperty() {
        return nomActeurs;
    }

    public void setNomActeurs(String nomActeurs) {
        this.nomActeurs.set(nomActeurs);
    }

}
