package vincentfillon.views;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import vincentfillon.Main;
import vincentfillon.connectivity.ConnectionClass;
import vincentfillon.dao.Dao;
import vincentfillon.dao.UtilisateurDAO;
import vincentfillon.model.Utilisateur;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class InterfaceUserController {

    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    @FXML
    private DatePicker dteBirthday;


    private InterfacePrincipaleController scene;
    private Stage stage;
    private boolean okClicked = false;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void addUser() {
        if (isInputValid()) {
            Dao<Utilisateur> utilisateurDao = new UtilisateurDAO(ConnectionClass.connecte());
            Utilisateur user=new Utilisateur();

            LocalDate localDate=dteBirthday.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            java.util.Date date = Date.from(instant);

            user.setIdRole(0);
            user.setUsername(txtUsername.getText());
            user.setEmail(txtEmail.getText());
            user.setPassword(txtPassword.getText());
            user.setBirthday(date);

            okClicked=true;

            if (okClicked) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.initOwner(Main.getPrimaryStage());
                alert.setTitle("Ajout d'utilisateur");
                alert.setHeaderText("Le film a bien été ajouté");
                Optional<ButtonType> result=alert.showAndWait();

                System.out.println("le get result renvoit: "+alert.getResult());
                if(result.isPresent()){
                    utilisateurDao.create(user);

                    Platform.exit();
                }


            }
        }
    }
    private boolean isInputValid() {
        String errorMessage = "";

        if (txtUsername.getText() == null || txtUsername.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez un pseudo\n";
        }
        //TODO: Sécuriser les conditions password
        if (txtEmail.getText() == null || txtEmail.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez un mail valide!\n";
        }

        if (txtPassword.getText() == null || txtPassword.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci préciser votre date de naissance\n";
        }

        //TODO: Sécuriser la condition du Birthday
        if (dteBirthday.getConverter() == null) {
            errorMessage += "Champs vide!\n Merci d'entrez une nationalité\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Affiche le message d'erreur.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            //alert.initOwner(dialogStage);
            alert.setTitle("Champs non valide");
            alert.setHeaderText("Merci de rectifier les champs non valides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}
