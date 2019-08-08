package fr.vincentfillon.model;

import fr.vincentfillon.connectivity.ConnectionClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnectionTest {
    Connection connection = ConnectionClass.connecte();


    {
        try {
            String query = "SELECT * FROM utilisateur";
            Statement statement;
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}




