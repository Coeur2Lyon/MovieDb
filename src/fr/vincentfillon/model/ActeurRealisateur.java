package fr.vincentfillon.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

public class ActeurRealisateur {
    GregorianCalendar cal = new GregorianCalendar(2007, 9 - 1, 23);
    long millis = cal.getTimeInMillis();

    private int idActeurRealisateur = 0;

    private StringProperty nom;
    private StringProperty prenom;
    private StringProperty anneeNaissance;
    private StringProperty nationalite;
    private Timestamp createdAt = new Timestamp(millis);
    private int isDeleted;

    private Object StringProperty;

    public ActeurRealisateur(int idActeurRealisateur, String nom, String prenom, String anneeNaissance, String nationalite, Date createdAt, int isDeleted) {

        this.idActeurRealisateur = idActeurRealisateur;
        this.nom = new SimpleStringProperty();
        this.prenom =new SimpleStringProperty();
        this.anneeNaissance = new SimpleStringProperty();
        this.nationalite = new SimpleStringProperty();
        this.createdAt = new Timestamp(createdAt.getTime());
        this.isDeleted = isDeleted;
    }


    public ActeurRealisateur(int idActeurRealisateur, String nom, String prenom, String anneeNaissance, String nationalite) {

        this.idActeurRealisateur=idActeurRealisateur;
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.anneeNaissance = new SimpleStringProperty(anneeNaissance);
        this.nationalite = new SimpleStringProperty(nationalite);
    }

    public ActeurRealisateur() {
        this(0, null, null, null,null);
    }

    public ActeurRealisateur(String nom, String prenom, String anneeNaissance, String nationalite) {
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.anneeNaissance = new SimpleStringProperty(anneeNaissance);
        this.nationalite = new SimpleStringProperty(nationalite);
    }




    public int getIdActeurRealisateur() {
        return idActeurRealisateur;
    }

    public void setIdActeurRealisateur(int idActeurRealisateur) {
        this.idActeurRealisateur = idActeurRealisateur;
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getPrenom() {
        return prenom.get();
    }

    public StringProperty prenomProperty() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom.set(prenom);
    }

    public String getAnneeNaissance() {
        return anneeNaissance.get();
    }

    public StringProperty anneeNaissanceProperty() {
        return anneeNaissance;
    }

    public void setAnneeNaissance(String anneeNaissance) {
        this.anneeNaissance.set(anneeNaissance);
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
        this.createdAt =  createdAt;
    }


}
