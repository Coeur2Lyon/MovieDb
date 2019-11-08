package vincentfillon.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Genre {

    private int idGenre = 0;
    private StringProperty genre;

    private boolean cboxPolicier;
    private boolean cboxThriller;
    private boolean cboxFantastqiqueSF;
    private boolean cboxDrame;
    private boolean cboxBiopic;
    private boolean cboxAction;
    private boolean cboxHorreur;
    private boolean cboxComedie;
    private boolean cboxWestern;
    private boolean cboxAventure;


    public Genre(int idGenre, String genre) {
        this.idGenre = idGenre;
        this.genre = new SimpleStringProperty(genre);
    }

    public Genre() {
        this(null);
    }

    public Genre(String genre) {
        this.genre = new SimpleStringProperty(genre);
    }

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

    public boolean isCboxPolicier() {
        return cboxPolicier;
    }

    public void setCboxPolicier(boolean cboxPolicier) {
        this.cboxPolicier = cboxPolicier;
    }

    public boolean isCboxThriller() {
        return cboxThriller;
    }

    public void setCboxThriller(boolean cboxThriller) {
        this.cboxThriller = cboxThriller;
    }

    public boolean isCboxFantastqiqueSF() {
        return cboxFantastqiqueSF;
    }

    public void setCboxFantastqiqueSF(boolean cboxFantastqiqueSF) {
        this.cboxFantastqiqueSF = cboxFantastqiqueSF;
    }

    public boolean isCboxDrame() {
        return cboxDrame;
    }

    public void setCboxDrame(boolean cboxDrame) {
        this.cboxDrame = cboxDrame;
    }

    public boolean isCboxBiopic() {
        return cboxBiopic;
    }

    public void setCboxBiopic(boolean cboxBiopic) {
        this.cboxBiopic = cboxBiopic;
    }

    public boolean isCboxAction() {
        return cboxAction;
    }

    public void setCboxAction(boolean cboxAction) {
        this.cboxAction = cboxAction;
    }

    public boolean isCboxHorreur() {
        return cboxHorreur;
    }

    public void setCboxHorreur(boolean cboxHorreur) {
        this.cboxHorreur = cboxHorreur;
    }

    public boolean isCboxComedie() {
        return cboxComedie;
    }

    public void setCboxComedie(boolean cboxComedie) {
        this.cboxComedie = cboxComedie;
    }

    public boolean isCboxWestern() {
        return cboxWestern;
    }

    public void setCboxWestern(boolean cboxWestern) {
        this.cboxWestern = cboxWestern;
    }

    public boolean isCboxAventure() {
        return cboxAventure;
    }

    public void setCboxAventure(boolean cboxAventure) {
        this.cboxAventure = cboxAventure;
    }

}
