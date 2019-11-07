package vincentfillon.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

public class JointureActeursRealisateur {
    GregorianCalendar cal = new GregorianCalendar(2007, 9 - 1, 23);
    long millis = cal.getTimeInMillis();
    private int idJointure = 0;
    private StringProperty nom;
    private StringProperty prenom;
    private StringProperty anneeNaissance;
    private StringProperty nationalite;
    private StringProperty realise;
    private StringProperty joue;
    private int isDeleted;
    private Timestamp createdAt = new Timestamp(millis);


    public JointureActeursRealisateur(int idJointure, String nom, String prenom, String anneeNaissance, String nationalite, String realise, String joue, Date createdAt, int isDeleted) {
        this.idJointure = idJointure;
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.anneeNaissance = new SimpleStringProperty(anneeNaissance);
        this.nationalite = new SimpleStringProperty(nationalite);
        this.realise = new SimpleStringProperty(realise);
        this.joue = new SimpleStringProperty(joue);
        this.createdAt = new Timestamp(createdAt.getTime());
        this.isDeleted = isDeleted;
    }

    public JointureActeursRealisateur() {
        this(0, null, null, null, null, null, null);
    }

    public JointureActeursRealisateur(int idJointure, String nom, String prenom, String anneeNaissance, String nationalite, String realise, String joue) {
        this.idJointure = idJointure;
        this.nom = new SimpleStringProperty(nom);
        this.prenom = new SimpleStringProperty(prenom);
        this.anneeNaissance = new SimpleStringProperty(anneeNaissance);
        this.nationalite = new SimpleStringProperty(nationalite);
        this.realise = new SimpleStringProperty(realise);
        this.joue = new SimpleStringProperty(joue);

    }

    public int getIdJointure() {
        return idJointure;
    }

    public void setIdJointure(int idJointure) {
        this.idJointure = idJointure;
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

    public String getRealise() {
        return realise.get();
    }

    public StringProperty realiseProperty() {
        return realise;
    }

    public void setRealise(String realise) {
        this.realise.set(realise);
    }

    public String getJoue() {
        return joue.get();
    }

    public StringProperty joueProperty() {
        return joue;
    }

    public void setJoue(String joue) {
        this.joue.set(joue);
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


}
