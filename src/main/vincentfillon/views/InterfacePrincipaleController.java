package vincentfillon.views;

import vincentfillon.Main;
import vincentfillon.connectivity.ConnectionClass;
import vincentfillon.dao.Dao;
import vincentfillon.dao.UtilisateurDAO;
import vincentfillon.model.Utilisateur;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javafx.scene.control.TextField;

import java.io.IOException;

public class InterfacePrincipaleController extends Main {

    private AnchorPane interfaceFilms;
    private AnchorPane interfaceJointureFilms;
    private main.vincentfillon.Main main;

    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;

    @FXML
    public void initInterfaceFilms(ActionEvent actionEvent) throws IOException {

        Dao<Utilisateur> utilisateurDAO = new UtilisateurDAO(ConnectionClass.connecte());
        ObservableList<Utilisateur> inactiveUserList = ((UtilisateurDAO) utilisateurDAO).findInactiveUsers();

        String userMail = txtEmail.getText();
        String userPassword = txtPassword.getText();

        int userId = utilisateurDAO.findIdByEntry(userMail);
        Utilisateur utilisateur = utilisateurDAO.find(userId);

        Parent root;
        Stage newWindow = new Stage();
        try {
            if (utilisateur != null && userId != 0) {//Le mail est dans la liste

                String passwordInDb = utilisateur.getPassword();
                userPassword = userPassword.intern();
                passwordInDb = passwordInDb.intern();

                if (passwordInDb == userPassword) { //Le mail est dans la liste ET le mot de passe correspond.
                    //TODO: 1. Développer la fonctionnalité qui différencie l'utulisateur de l'administrateur
                    //TODO: 2.Enelever EDIT & SUPPR du Layout si l'utilisateur n'est pas Admin
                    //TODO: 3.Adapter la taille du Label Scenario (dans le ScrollPane qui doit rester lui de taille fixe en fonction de la longueur de la String scenario.
                    root = FXMLLoader.load(getClass().getResource("InterfaceFilmsAdmin.fxml"));
                    newWindow.setScene(new Scene(root, 850, 642));
                    newWindow.initModality(Modality.APPLICATION_MODAL);
                    newWindow.show();

                } else {//Le mail est dans la liste MAIS le mot de passe ne correspond pas.

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(Main.getPrimaryStage());
                    alert.setTitle("Mot de passe erroné");
                    alert.setHeaderText("Le mot de passe entré est incorrect!");
                    alert.setContentText("Il y a bien un compte existant associé à votre e-mail,\n Toutefois, le mot de passe entré ne correspond pas à ce compte\nVérifiez que la touche que les majuscules ne soient pas verrouillés.");
                    alert.showAndWait();
                }

            } else if (inactiveUserList.contains(userMail)) {//Le mail est dans la liste des inactifs
                //TODO: Votre compte est désactivé souhaitez vous le réactivez (avec un Alert OK/Annuler)

            } else { //Le mail n'est pas dans la liste: Créez un compte

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(Main.getPrimaryStage());
                alert.setTitle("Utilisateur/ E-mail inconnu");
                alert.setHeaderText("E-mail erroné ou compte inexistant!");
                alert.setContentText("L'e-mail entré ne correspond à aucun compte\n ou contient une erreur,\n Merci de bien vouloir entrer un e-mail valide\n ou bien de créer un compte 'utilisateur'");
                alert.showAndWait();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initInterfaceActeursRealisateurs(ActionEvent actionEvent) throws IOException {
        Dao<Utilisateur> utilisateurDAO = new UtilisateurDAO(ConnectionClass.connecte());
        ObservableList<Utilisateur> inactiveUserList = ((UtilisateurDAO) utilisateurDAO).findInactiveUsers();

        String userMail = txtEmail.getText();
        String userPassword = txtPassword.getText();

        int userId = utilisateurDAO.findIdByEntry(userMail);
        Utilisateur utilisateur = utilisateurDAO.find(userId);

        Parent root;
        Stage newWindow = new Stage();
        try {
            if (utilisateur != null && userId != 0) {//Le mail est dans la liste

                String passwordInDb = utilisateur.getPassword();
                userPassword = userPassword.intern();
                passwordInDb = passwordInDb.intern();

                if (passwordInDb == userPassword) { //Le mail est dans la liste ET le mot de passe correspond.
                    //TODO: 1. Développer la fonctionnalité qui différencie l'utulisateur de l'administrateur
                    //TODO: 2.Enelever EDIT & SUPPR du Layout si l'utilisateur n'est pas Admin
                    //TODO: 3.Adapter la taille du Label Scenario (dans le ScrollPane qui doit rester lui de taille fixe en fonction de la longueur de la String scenario.

                    root = FXMLLoader.load(getClass().getResource("InterfaceActeursRealisateursAdmin.fxml"));
                    newWindow.setScene(new Scene(root, 850, 642));
                    newWindow.initModality(Modality.APPLICATION_MODAL);

                    newWindow.show();

                } else {//Le mail est dans la liste MAIS le mot de passe ne correspond pas.

                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.initOwner(Main.getPrimaryStage());
                    alert.setTitle("Mot de passe erroné");
                    alert.setHeaderText("Le mot de passe entré est incorrect!");
                    alert.setContentText("Il y a bien un compte existant associé à votre e-mail,\n Toutefois, le mot de passe entré ne correspond pas à ce compte\nVérifiez que la touche que les majuscules ne soient pas verrouillés.");
                    alert.showAndWait();
                }

            } else if (inactiveUserList.contains(userMail)) {//Le mail est dans la liste des inactifs
                //TODO: Votre compte est désactivé souhaitez vous le réactivez (avec un Alert OK/Annuler)

            } else { //Le mail n'est pas dans la liste: Créez un compte
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.initOwner(Main.getPrimaryStage());
                alert.setTitle("Utilisateur/ E-mail inconnu");
                alert.setHeaderText("E-mail erroné ou compte inexistant!");
                alert.setContentText("L'e-mail entré ne correspond à aucun compte\n ou contient une erreur,\n Merci de bien vouloir entrer un e-mail valide\n ou bien de créer un compte 'utilisateur'");
                alert.showAndWait();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initInterfaceUser(ActionEvent actionEvent) {
        Stage newWindow = new Stage();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("InterfaceUser.fxml"));
            newWindow.setScene(new Scene(root, 500, 600));
            newWindow.initModality(Modality.APPLICATION_MODAL);
            newWindow.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
