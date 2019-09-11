package fr.vincentfillon.views;

//public class MovieEditDialogController {

import fr.vincentfillon.model.ActeurRealisateur;
import fr.vincentfillon.model.Jointure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
//import ch.makery.address.util.DateUtil;

/**
 * Dialog to edit details of a movie.
 *
 * @author Marco Jakob
 */
public class ActRealEditDialogController {

    @FXML
    private TextField fldPrenom;
    @FXML
    private TextField fldNom;

    @FXML
    private TextField fldAnneeNaissance;
    @FXML
    private TextField fldNationalite;





    private Stage dialogStage;
    private ActeurRealisateur acteurRealisateur;
    private boolean okClicked = false;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Sets the stage of this dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }

    /**
     * Sets the movie Join to be edited in the dialog.
     *
     * @param acteurRealisateur
     */
    public void setMovieJoin(ActeurRealisateur acteurRealisateur) {
        this.acteurRealisateur=acteurRealisateur;

        fldPrenom.setText(acteurRealisateur.getPrenom());
        fldNom.setText(acteurRealisateur.getNom());

        fldAnneeNaissance.setText(acteurRealisateur.getNom());
        fldNationalite.setText(acteurRealisateur.getNationalite());

    }

    /**
     * Returns true if the user clicked OK, false otherwise.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            acteurRealisateur.setPrenom(fldPrenom.getText());
            acteurRealisateur.setNom(fldNom.getText());

            acteurRealisateur.setAnneeNaissance(fldAnneeNaissance.getText());
            acteurRealisateur.setNationalite(fldNationalite.getText());


            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Validates the user input in the text fields.
     *
     * @return true if the input is valid
     */
    private boolean isInputValid() {
        String errorMessage = "";

        if (fldPrenom.getText() == null || fldPrenom.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez un titre français\n";
        }
        if (fldNom.getText() == null || fldNom.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez un titre original!!\n";
        }

        if (fldAnneeNaissance.getText() == null || fldAnneeNaissance.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez une année de sortie\n";
        }

        if (fldNationalite.getText() == null || fldNationalite.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez une nationalité\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs non valide");
            alert.setHeaderText("Merci de rectifier les champs non valides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    public void addNewActeurRealisateur(ActionEvent actionEvent) {
        initActRealEditDialog();

    }

    private void initActRealEditDialog() {

    }

    public void setActeurRealisateur(ActeurRealisateur acteurRealisateur) {
    }
}