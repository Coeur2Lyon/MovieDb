package vincentfillon.dao;

import vincentfillon.model.Utilisateur;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.text.SimpleDateFormat;

public class UtilisateurDAO extends Dao<Utilisateur> {

    public UtilisateurDAO(Connection connection) {
        super(connection);
    }

    @Override
    public void create(Utilisateur utilisateur) {

        int idRole=2;// 2 est l'identifiant d'un simple utilisateur (l'administrateur est "1")
        String username=utilisateur.getUsername();
        String password=utilisateur.getPassword();
        String email= utilisateur.getEmail();
        java.util.Date birthdayDate=utilisateur.getBirthday();

        java.sql.Date sqlDate=new java.sql.Date(birthdayDate.getTime());

        String insertRequest = "INSERT INTO UTILISATEUR (IdRole,Username, Password, Email, Birthday) VALUES(?,?,?,?,?)";
        try {

            PreparedStatement preparedStatement = connect.prepareStatement(insertRequest);
            preparedStatement.setInt(1,idRole);
            preparedStatement.setString(2,username);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,email);
            preparedStatement.setDate(5,sqlDate);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Utilisateur obj) {
    }

    @Override
    public void update(Utilisateur obj) {
    }

    @Override
    public Utilisateur find(int idUser) {

        Utilisateur utilisateur = new Utilisateur();

        String sqlSelectUtilisateur = "SELECT * FROM UTILISATEUR WHERE IdUser=" + idUser;

        try {
            ResultSet resultSet = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlSelectUtilisateur);

            if (resultSet.first()) {

                int idRole = resultSet.getInt(2);
                String username = resultSet.getString(3);
                String password = resultSet.getString(4);
                String email = resultSet.getString(5);
                Date birthdayDate = resultSet.getDate(6);
                String dateFormat = "yyyy-MM-dd";
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
                String birthday = dateFormat.format(String.valueOf(birthdayDate));

                Date createdAt = resultSet.getDate(7);
                int isDeleted = resultSet.getInt(8);

                utilisateur.setIdUser(idUser);
                utilisateur.setIdRole(idRole);
                utilisateur.setUsername(username);
                utilisateur.setPassword(password);
                utilisateur.setEmail(email);
                utilisateur.setBirthday(birthdayDate);
                utilisateur.setCreatedAt(createdAt);
                utilisateur.setIsDeleted(isDeleted);
            }

        } catch (SQLException e) {
            e.getErrorCode();

        }
        return utilisateur;
    }

    @Override
    public ObservableList findAll() {
        ObservableList<Utilisateur> inactiveUsersList = FXCollections.observableArrayList();

        for (int i = 1; i <= findIdMax(); i++) {
            Utilisateur utilisateur = find(i);
            if (utilisateur.getIsDeleted() == 0) {
                inactiveUsersList.add(utilisateur);
            }
        }
        return inactiveUsersList;
    }

    public ObservableList findInactiveUsers() {

        ObservableList<Utilisateur> activeUsersList = FXCollections.observableArrayList();

        for (int i = 1; i <= findIdMax(); i++) {
            Utilisateur utilisateur = find(i);
            if (utilisateur.getIsDeleted() == 0) {
                activeUsersList.add(utilisateur);
            }
        }
        return activeUsersList;
    }

    @Override
    public int findIdMax() {
        int idMax = 0;
        String sqlFindMax = "SELECT MAX(IdUser) FROM UTILISATEUR";

        try {
            ResultSet resultFilm = this.connect.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY).executeQuery(sqlFindMax);

            if (resultFilm.first()) {
                idMax = resultFilm.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idMax;
    }


    @Override
    public int findIdByEntry(String usermail) {
        int userId = 0;

        String sqlIdByMail = "SELECT IdUser FROM UTILISATEUR WHERE Email=?";

        try {
            PreparedStatement findIdByUsermailStatement = connect.prepareStatement(sqlIdByMail);
            findIdByUsermailStatement.setString(1, usermail);

            ResultSet resultat = findIdByUsermailStatement.executeQuery();

            if (resultat.first()) {
                userId = resultat.getInt(1);
            } else {
                System.out.println(usermail + " n'est pas dans la liste UTILISATEUR");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

}
