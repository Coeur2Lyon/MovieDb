package fr.vincentfillon.views;

import fr.vincentfillon.connectivity.ConnectionClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;
import java.time.Clock;

public class InterfaceUserController {
    public void createUser(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
    //ConnectionClass connectionClass=new ConnectionClass();
        Connection connection=ConnectionClass.connecte();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM utilisateur");
        System.out.println("Printing schema for table: " + resultSet.getMetaData().getTableName(1));
        int columnCount = resultSet.getMetaData().getColumnCount();

        for (int i = 1; i <= columnCount; i++) {
            System.out.println(i + " " + resultSet.getMetaData().getColumnName(i));
        }

        System.out.println("Searching for example user.");
        boolean exampleUserFound = false;

        while (resultSet.next()) {
            String username = resultSet.getString("username");
            if (username.equals("jakutilisateur")) {
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Timestamp updatedAt = resultSet.getTimestamp("updated_at");
                System.out.println("Example user found.");
                System.out.println("Name: " + username);
                System.out.println("Created at: " + createdAt);
                System.out.println("Updated at: " + updatedAt);
                exampleUserFound = true;
                break;
            }

            /*else if (username.equals("shiin")) {
                Timestamp createdAt = resultSet.getTimestamp("created_at");
                Timestamp updatedAt = resultSet.getTimestamp("updated_at");
                System.out.println("Example user found.");
                System.out.println("Name: " + username);
                System.out.println("Created at: " + createdAt);
                System.out.println("Updated at: " + updatedAt);
                exampleUserFound = true;

                break;
            }*/
        }

        if (!exampleUserFound) {
            System.out.println("Example user not found. Inserting a new user.");
            String  username="usernamec", password="usernamec", email="usernamec", birthday="1980-06-04";
            Integer idRole=0;
            Timestamp createdAt=new Timestamp(new java.util.Date().getTime());
            String query="INSERT INTO utilisateur (IdRole, Username, Password, Email, Birthday,Created_at) VALUES('"+idRole+ "','"+username+"','"+password+"','"+email+"','"+birthday+"','"+createdAt+"')";
          //  cnxBddMysqlAfip=connectionALaBdd();
            statement=connection.createStatement();
            statement.executeUpdate(query);
            System.out.println("Produit bien ajouté avec Id en Auto incrément");

           // Statement statement2 =connection.createStatement();
           // statement2.executeUpdate("INSERT INTO utilisateur "+" VALUESd ([value-1],[0],['shiin'],['root'],['toto@gmail.com'],[1980-06-04])");

            /*
            * String query="INSERT INTO stagiaires (IdRole, Username, Password, Email, Birthday) VALUES('"+nom+"','"+prenom+"','"+date_entree+"','"+motdepasse+"')";
	            cnxBddMysqlAfip=connectionALaBdd();
	            statemntAfip=cnxBddMysqlAfip.createStatement();
	            statemntAfip.executeUpdate(query);
	            System.out.println("Produit bien ajouté avec Id en Auto incrément");
            *
            * */

            /*PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO utilisateur (IdRole, Username, Password, Email, Birthday) VALUES (0,shiin,root,toto@gmail.com,1980-06-04)");
            preparedStatement.executeUpdate();*/

                    /*preparedStatement.setString(1, "shiin");
            preparedStatement.setString(2, "insecure");
            preparedStatement.setTimestamp(3, new Timestamp(new java.util.Date().getTime()));
            preparedStatement.executeUpdate();*/
        }
    }


}
