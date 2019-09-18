package fr.vincentfillon.dao;




import javafx.collections.ObservableList;

import java.sql.*;


public abstract class Dao<T> {
    protected Connection connect = null;

    public Dao(Connection conn){
        this.connect = conn;
    }

    /**
     * Méthode de création
     * @param obj
     * @return boolean
     */
    public abstract void create(T obj);

    /**
     * Méthode pour effacer
     * @param obj
     * @return boolean
     */
    public abstract void delete(T obj);

    /**
     * Méthode de mise à jour
     * @param obj
     * @return boolean
     */
    public abstract void update(T obj);

    /**
     * Méthode de recherche d'un film en donction de son id
     * @param id
     * @return T
     */
    public abstract T find(int id);

    public abstract ObservableList findAll();


    public abstract int findIdfromTitre(String titre);

    public abstract int findIdMax();

}