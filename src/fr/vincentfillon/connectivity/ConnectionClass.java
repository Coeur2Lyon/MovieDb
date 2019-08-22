package fr.vincentfillon.connectivity;

import java.sql.*;

public class ConnectionClass { //Avec connexion unique à l'aide du design pattern SingleTon
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
                connection.close();
                Class.forName(driver);
                connection = DriverManager.getConnection(urlStr, username, password);

            }
        } catch (ClassNotFoundException |
                SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
