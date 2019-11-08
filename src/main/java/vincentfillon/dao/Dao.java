package vincentfillon.dao;

import javafx.collections.ObservableList;

import java.sql.*;


public abstract class Dao<T> {
    protected Connection connect = null;

    public Dao(Connection conn){
        this.connect = conn;
    }

    /**
     * Méthode de création (ajout/insertion dans la base de données)
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
     * Méthode de recherche d'un élément d'une table en donction de son id
     * @param id
     * @return T
     */
    public abstract T find(int id);
    /**
     * Méthode de recherche d'un film en donction de son id
     * @return ObservableList
     */

    public abstract ObservableList findAll();
    /**
     * Méthode de recherche de l'id Max d'une table
     * @return int
     */
    public abstract int findIdMax();
    /**
     * Méthode de recherche de l'identifiant d'une table par rapport à l'entrée(String) utilisateur
     * @return int
     */
    public int findIdByEntry(String entry) {
        int userId = 0;
        return userId;
    }
}