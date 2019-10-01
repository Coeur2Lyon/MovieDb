package fr.vincentfillon.dao;

import fr.vincentfillon.model.Correspond;
import fr.vincentfillon.model.JointureFilm;
import fr.vincentfillon.model.ListeCheckBox;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CorrespondDAO extends Dao<Correspond> {

    public CorrespondDAO(Connection conn) {
        super(conn);
    }

    @Override
    public void create(Correspond correspond) {

        int idFilm = correspond.getIdFilm();
        int idGenre = correspond.getIdGenre();

        String sqlInsertCorrespond = "INSERT INTO moviedb.CORRESPOND(IdGenre, IdFilm) VALUES (" + idGenre + "," + idFilm + ")";
//Attribution de l'acteur/réal. d'id 10 (Acteur fantôme.
        String sqLInsertRealisateurREALISE = "INSERT INTO moviedb.REALISE(IdActeurRealisateur, IdFilm) VALUES (10," + idFilm + ")";
        String sqlInsertActeursJOUE = "INSERT INTO moviedb.JOUE(IdActeurRealisateur, IdFilm) VALUES (10," + idFilm + ")";

        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(sqlInsertCorrespond);
            statement.executeUpdate(sqLInsertRealisateurREALISE);
            statement.executeUpdate(sqlInsertActeursJOUE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Correspond correspond) {

        int idGenre = correspond.getIdGenre();
        int idFilm=correspond.getIdFilm();

        System.out.println("Contrôle juste avant Supprssion DANS CorrespondDAO/delete:");
        System.out.println("L'Id du Film à supprimer est :" + correspond.getIdFilm());
        System.out.println("L'Id du Genre à insérer est :" + correspond.getIdGenre());

        String sqlDeleteRequest = "DELETE FROM CORRESPOND WHERE IdGenre="+idGenre+" AND IdFilm="+idFilm;

        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(sqlDeleteRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void deleteByInt(int idGenre, int idFilm){

        String sqlDeleteRequest = "DELETE FROM CORRESPOND WHERE IdGenre="+idGenre+" AND IdFilm="+idFilm;
        try {
            Statement statement = this.connect.createStatement();
            statement.executeUpdate(sqlDeleteRequest);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Correspond obj) {

    }

    @Override
    public Correspond find(int id) {
        return null;
    }

    @Override
    public ObservableList findAll() {
        return null;
    }



    @Override
    public int findIdMax() {
        return 0;
    }

    public ListeCheckBox extractListeCheckBoxFromJointure(JointureFilm jointureFilm) {
        ListeCheckBox listeCheckBox = new ListeCheckBox();
        int idFilm = jointureFilm.getIdJointure();

        String sqlGenreFromId = "SELECT IdGenre FROM CORRESPOND WHERE IdFilm=" + idFilm + "";

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlGenreFromId);
            if (result.first()) {
                ArrayList<Integer> listeIntgenre = new ArrayList<>();
                result.last();
                int tailleListe = result.getRow();
                result.first();

                for (int i = 1; i <= tailleListe; i++) {
                    listeIntgenre.add(result.getInt(1));
                    result.next();
                }

                System.out.println("Liste des Ids Genre relevés: " + listeIntgenre);

                if (listeIntgenre.contains(0)) {
                    listeCheckBox.setCboxPolicier(true);
                } else {
                    listeCheckBox.setCboxPolicier(false);
                }
                if (listeIntgenre.contains(1)) {
                    listeCheckBox.setCboxThriller(true);
                } else {
                    listeCheckBox.setCboxThriller(false);
                }
                if (listeIntgenre.contains(2)) {
                    listeCheckBox.setCboxFantastqiqueSF(true);
                } else {
                    listeCheckBox.setCboxFantastqiqueSF(false);
                }
                if (listeIntgenre.contains(3)) {
                    listeCheckBox.setCboxDrame(true);
                    System.out.println("isDrame Doit etre TRUE:" + listeCheckBox.isCboxDrame());
                } else {
                    listeCheckBox.setCboxDrame(false);
                }
                if (listeIntgenre.contains(4)) {
                    listeCheckBox.setCboxBiopic(true);
                } else {
                    listeCheckBox.setCboxBiopic(false);
                }
                if (listeIntgenre.contains(5)) {
                    listeCheckBox.setCboxAction(true);
                } else {
                    listeCheckBox.setCboxAction(false);
                }
                if (listeIntgenre.contains(6)) {
                    listeCheckBox.setCboxHorreur(true);
                } else {
                    listeCheckBox.setCboxHorreur(false);
                }
                if (listeIntgenre.contains(7)) {
                    listeCheckBox.setCboxComedie(true);
                } else {
                    listeCheckBox.setCboxComedie(false);
                }
                if (listeIntgenre.contains(8)) {
                    listeCheckBox.setCboxWestern(true);
                } else {
                    listeCheckBox.setCboxWestern(false);
                }
                if (listeIntgenre.contains(9)) {
                    listeCheckBox.setCboxAventure(true);
                } else {
                    listeCheckBox.setCboxAventure(false);
                }
            }


        } catch (SQLException e) {

        }
        return listeCheckBox;
    }

    public ArrayList<Integer> extractIdListFromJointure(JointureFilm jointureFilm) {
        ArrayList<Integer> listeIntgenre = new ArrayList<>();
        int idFilm = jointureFilm.getIdJointure();

        String sqlGenreFromId = "SELECT IdGenre FROM CORRESPOND WHERE IdFilm=" + idFilm + "";

        try {
            ResultSet result = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlGenreFromId);
            if (result.first()) {

                result.last();
                int tailleListe = result.getRow();
                result.first();

                for (int i = 1; i <= tailleListe; i++) {
                    listeIntgenre.add(result.getInt(1));
                    result.next();
                }
            }
        } catch (SQLException e) {

        }
        return listeIntgenre;
    }

    // Méthode qui nous permet d'obtenir la même liste d'id sans faire de requête
    public ArrayList<Integer> extractListIdGenreFromListCheckbox(ListeCheckBox listeCheckBox) {
        ArrayList<Integer> listIntGenre = new ArrayList<>();

        if (listeCheckBox.isCboxPolicier()) {
            listIntGenre.add(0);
        }
        if (listeCheckBox.isCboxThriller()) {
            listIntGenre.add(1);
        }
        if (listeCheckBox.isCboxFantastqiqueSF()) {
            listIntGenre.add(2);
        }
        if (listeCheckBox.isCboxDrame()) {
            listIntGenre.add(3);
        }
        if (listeCheckBox.isCboxBiopic()) {
            listIntGenre.add(4);
        }
        if (listeCheckBox.isCboxAction()) {
            listIntGenre.add(5);
        }
        if (listeCheckBox.isCboxHorreur()) {
            listIntGenre.add(6);
        }
        if (listeCheckBox.isCboxComedie()) {
            listIntGenre.add(7);
        }
        if (listeCheckBox.isCboxWestern()) {
            listIntGenre.add(8);
        }
        if (listeCheckBox.isCboxAventure()) {
            listIntGenre.add(9);
        }
        return listIntGenre;

    }


}

