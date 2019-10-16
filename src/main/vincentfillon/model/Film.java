package main.vincentfillon.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;


public class Film {

    GregorianCalendar cal = new GregorianCalendar(2007, 9 - 1, 23);

    private int idFilm = 0;
    private StringProperty titreFR;
    private StringProperty titreO;
    private StringProperty scenario;
    private StringProperty anneeSortie;
    private StringProperty nationalite;

    long millis = cal.getTimeInMillis();
    private Timestamp createdAt = new Timestamp(millis);

    private int isDeleted = 0;

    private Object StringProperty;

    public Film() {
        this(null, null, null, null, null);
    }

    public Film(int idFilm, String titreFR, String titreO, String scenario, String anneeSortie, String nationaliteF, Date createdAt, int isDeleted) {
        this.idFilm = idFilm;
        this.titreFR = new SimpleStringProperty(titreFR);
        this.titreO = new SimpleStringProperty(titreO);
        this.scenario = new SimpleStringProperty(scenario);
        this.anneeSortie = new SimpleStringProperty(anneeSortie);
        this.nationalite = new SimpleStringProperty(nationaliteF);
        this.createdAt = new Timestamp(createdAt.getTime());
        this.isDeleted = isDeleted;
    }

    public Film(String titreFR, String titreO, String scenario, String anneeSortie, String nationaliteF) {
        this.titreFR = new SimpleStringProperty(titreFR);
        this.titreO = new SimpleStringProperty(titreO);
        this.scenario = new SimpleStringProperty(scenario);
        this.anneeSortie = new SimpleStringProperty(anneeSortie);
        this.nationalite = new SimpleStringProperty(nationaliteF);
    }

    public int getIdFilm() {return idFilm;}

    public void setIdFilm(int idFilm) { this.idFilm = idFilm;}

    public String getTitreFR() {return titreFR.get();    }

    public void setTitreFR(String titreFR) {this.titreFR.set(titreFR);}

    public StringProperty titreVFProperty() { return titreFR;}

    public String getTitreO() {return titreO.get();}

    public void setTitreO(String titreO) {this.titreO.set(titreO);}

    public StringProperty titreOProperty() {return titreO;}

    public String getScenario() {return scenario.get();}

    public void setScenario(String scenario) {this.scenario.set(scenario);}

    public StringProperty scenarioProperty() {return scenario;}

    public String getAnneeSortie() {return anneeSortie.get();}

    public void setAnneeSortie(String anneeSortie) {this.anneeSortie.set(anneeSortie);}

    public StringProperty anneeSortieProperty() {return anneeSortie;}

    public String getNationalite() {return nationalite.get();}

    public void setnationalite(String nationalite) {this.nationalite.set(nationalite);}

    public StringProperty nationaliteProperty() {return nationalite;}

    public Timestamp getCreatedAt() {return createdAt;}

    public void setCreatedAt(Timestamp createdAt) {this.createdAt = createdAt;}

    public int getIsDeleted() {return isDeleted;}

    public void setIsDeleted(int isDeleted) {this.isDeleted = isDeleted;}
}
