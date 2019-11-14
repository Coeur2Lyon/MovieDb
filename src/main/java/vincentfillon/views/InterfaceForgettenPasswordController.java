package vincentfillon.views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.awt.*;

public class InterfaceForgettenPasswordController {



    @FXML
    public void handleOK(ActionEvent actionEvent) {

        

        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Adresse e-mail inconnue!");
        alert.setHeaderText("L'adresse entrée ne correspond à aucun compte\n Vous pouvez créer un compte \n à partir de l'interface principale");
        alert.showAndWait();

    }
}
