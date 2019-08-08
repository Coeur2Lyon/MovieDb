package fr.vincentfillon.views;

//public class MovieEditDialogController {

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import fr.vincentfillon.model.Film;
//import ch.makery.address.util.DateUtil;

/**
 * Dialog to edit details of a movie.
 *
 * @author Marco Jakob
 */
public class MovieEditDialogController {

    @FXML
    private TextField fldTitreVF;
    @FXML
    private TextField fldTitreVO;
    @FXML
    private TextField fldScenario;
    @FXML
    private TextField fldAnneeSortie;
    @FXML
    private TextField fldNationalite;


    private Stage dialogStage;
    private Film film;
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
     * Sets the movie to be edited in the dialog.
     *
     * @param film
     */
    public void setMovie(Film film) {
        this.film = film;

        fldTitreVF.setText(film.getTitreFR());
        fldTitreVO.setText(film.getTitreO());
        fldScenario.setText(film.getScenario());
        fldAnneeSortie.setText(film.getAnneeSortie());
        fldNationalite.setText(film.getnationalite());
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
            film.setTitreFR(fldTitreVF.getText());
            film.setTitreO(fldTitreVO.getText());
            film.setScenario(fldScenario.getText());
            film.setAnneeSortie(fldAnneeSortie.getText());
            film.setnationalite(fldNationalite.getText());

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

        if (fldTitreVF.getText() == null || fldTitreVF.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (fldTitreVO.getText() == null || fldTitreVO.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (fldScenario.getText() == null || fldScenario.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }

        if (fldAnneeSortie.getText() == null || fldAnneeSortie.getText().length() == 0) {
            errorMessage += "No valid postal code!\n";
        }

        if (fldNationalite.getText() == null || fldNationalite.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }

        /*if (birthdayField.getText() == null || birthdayField.getText().length() == 0) {
            errorMessage += "No valid birthday!\n";
        } else {
            if (!DateUtil.validDate(birthdayField.getText())) {
                errorMessage += "No valid birthday. Use the format dd.mm.yyyy!\n";
            }
        }*/

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
}