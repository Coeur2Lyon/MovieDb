package fr.vincentfillon.connectivity;

import fr.vincentfillon.Main;
import javafx.scene.control.Alert;

import java.sql.*;

public class ConnectionClass { //Avec connexion unique à l'aide du design pattern SingleTon
    /*  //Connexion OVH
      static Connection connection = null;
      static String driver = "com.mysql.cj.jdbc.Driver";
      static String url = "jdbc:mysql";
      static String ip = "moviedbowudb.mysql.db";
      static String dbName = "moviedbowudb";
      static String username = "moviedbowudb";
      static String password = "Officedp69";
      static String urlStr = url + "://" + ip + "/" + dbName + "";
  */
//Connexion LocalHost
    static Connection connection = null;
    static String driver = "com.mysql.cj.jdbc.Driver";
    static String url = "jdbc:mysql";
    static String ip = "localhost";
    static String dbName = "moviedb";
    static String username = "root";
    static String password = "moviedb";
    static String urlStr = url + "://" + ip + "/" + dbName + "";

    private ConnectionClass() {
    }

    public static Connection connecte() {
        try {
            if (connection == null) {
                Class.forName(driver);
                connection = DriverManager.getConnection(urlStr, username, password);
                //Statement statement = connection.createStatement();
            } else {
                System.out.println("Il existe déja une connexion");
                Class.forName(driver);
                connection = DriverManager.getConnection(urlStr, username, password);
            }
        } catch (ClassNotFoundException |
                SQLException e) {
            System.out.println("Echec de la connexion");
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Echec de la connexion");
            alert.setHeaderText("Echec de la connexion");
            alert.setContentText("Vérifiez que vous êtes bien connecté à votre base de données puis recommencez.");
            alert.showAndWait();
            e.printStackTrace();
        }
        return connection;
    }

    /*
    /* Fermeture silencieuse du resultset */
    public static void fermetureSilencieuse( ResultSet resultSet ) {
        if ( resultSet != null ) {
            try {
                resultSet.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture du ResultSet : " + e.getMessage() );
            }
        }
    }

    /* Fermeture silencieuse du statement */
    public static void fermetureSilencieuse( Statement statement ) {
        if ( statement != null ) {
            try {
                statement.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture du Statement : " + e.getMessage() );
            }
        }
    }

    /* Fermeture silencieuse de la connexion */
    public static void fermetureSilencieuse( Connection connexion ) {
        if ( connexion != null ) {
            try {
                connexion.close();
            } catch ( SQLException e ) {
                System.out.println( "Échec de la fermeture de la connexion : " + e.getMessage() );
            }
        }
    }

    /* Fermetures silencieuses du statement et de la connexion */
    public static void fermeturesSilencieuses( Statement statement, Connection connexion ) {
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );
    }

    /* Fermetures silencieuses du resultset, du statement et de la connexion */
    public static void fermeturesSilencieuses( ResultSet resultSet, Statement statement, Connection connexion ) {
        fermetureSilencieuse( resultSet );
        fermetureSilencieuse( statement );
        fermetureSilencieuse( connexion );
    }


}
