package fr.vincentfillon.views;

//public class MovieEditDialogController {

import fr.vincentfillon.model.Film;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
//import ch.makery.address.util.DateUtil;

/**
 * Dialog to edit details of a movie.
 *
 * @author Marco Jakob
 */
public class FiltreDialogController {

    @FXML
    private ChoiceBox cboxGenre;
    @FXML
    private ChoiceBox cboxRealisateur;
    @FXML
    private ChoiceBox cboxActeur;



    private Stage dialogStage;

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
         /*  this.film = film;

     fldTitreVF.setText(film.getTitreFR());
        fldTitreVO.setText(film.getTitreO());
        fldScenario.setText(film.getScenario());
        fldAnneeSortie.setText(film.getAnneeSortie());
        fldNationalite.setText(film.getNationalite());*/
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
           //TODO: Fonction qui active le filtre sélectionné

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
            // Show the error message.
           /* Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();
*/
            return false;

    }
}