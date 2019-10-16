package main.vincentfillon.model;

public class ListeCheckBox {

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

    public ListeCheckBox() {

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



    public ListeCheckBox(boolean cboxPolicier, boolean cboxThriller, boolean cboxFantastqiqueSF, boolean cboxDrame, boolean cboxBiopic, boolean cboxAction, boolean cboxHorreur, boolean cboxComedie, boolean cboxWestern, boolean cboxAventure) {
        this.cboxPolicier = cboxPolicier;
        this.cboxThriller = cboxThriller;
        this.cboxFantastqiqueSF = cboxFantastqiqueSF;
        this.cboxDrame = cboxDrame;
        this.cboxBiopic = cboxBiopic;
        this.cboxAction = cboxAction;
        this.cboxHorreur = cboxHorreur;
        this.cboxComedie = cboxComedie;
        this.cboxWestern = cboxWestern;
        this.cboxAventure = cboxAventure;
    }
}
