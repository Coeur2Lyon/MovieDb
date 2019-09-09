package fr.vincentfillon.model;

import javafx.beans.property.StringProperty;

public class Jointure {

    int idJointure=0;
    private StringProperty titreFR;
    private StringProperty titreO;
    private StringProperty scenario;
    private StringProperty anneeSortie;
    private StringProperty nationalite;
    private StringProperty genre;
    private StringProperty prenomActeur;
    private StringProperty nomActeur;
    private StringProperty prenomRealisateur;
    private StringProperty nomRealisateur;

    public Jointure(int idJointure, StringProperty titreFR, StringProperty titreO, StringProperty scenario, StringProperty anneeSortie, StringProperty nationalite, StringProperty genre, StringProperty prenomActeur, StringProperty nomActeur, StringProperty prenomRealisateur, StringProperty nomRealisateur) {
        this.idJointure = idJointure;
        this.titreFR = titreFR;
        this.titreO = titreO;
        this.scenario = scenario;
        this.anneeSortie = anneeSortie;
        this.nationalite = nationalite;
        this.genre = genre;
        this.prenomActeur = prenomActeur;
        this.nomActeur = nomActeur;
        this.prenomRealisateur = prenomRealisateur;
        this.nomRealisateur = nomRealisateur;
    }

    public Jointure() {
        this.titreFR = titreFR;
        this.titreO = titreO;
        this.scenario = scenario;
        this.anneeSortie = anneeSortie;
        this.nationalite = nationalite;
        this.genre = genre;
        this.prenomActeur = prenomActeur;
        this.nomActeur = nomActeur;
        this.prenomRealisateur = prenomRealisateur;
        this.nomRealisateur = nomRealisateur;
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

    public String getPrenomActeur() {
        return prenomActeur.get();
    }

    public StringProperty prenomActeurProperty() {
        return prenomActeur;
    }

    public void setPrenomActeur(String prenomActeur) {
        this.prenomActeur.set(prenomActeur);
    }

    public String getNomActeur() {
        return nomActeur.get();
    }

    public StringProperty nomActeurProperty() {
        return nomActeur;
    }

    public void setNomActeur(String nomActeur) {
        this.nomActeur.set(nomActeur);
    }

    public String getPrenomRealisateur() {
        return prenomRealisateur.get();
    }

    public StringProperty prenomRealisateurProperty() {
        return prenomRealisateur;
    }

    public void setPrenomRealisateur(String prenomRealisateur) {
        this.prenomRealisateur.set(prenomRealisateur);
    }

    public String getNomRealisateur() {
        return nomRealisateur.get();
    }

    public StringProperty nomRealisateurProperty() {
        return nomRealisateur;
    }

    public void setNomRealisateur(String nomRealisateur) {
        this.nomRealisateur.set(nomRealisateur);

    }


}
