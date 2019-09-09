package fr.vincentfillon.views;

//public class MovieEditDialogController {

import fr.vincentfillon.model.Film;
import fr.vincentfillon.model.Jointure;
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
public class MovieJoinEditDialogController {

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
    @FXML
    private TextField fldGenre;
    @FXML
    private TextField fldRealisateur;
    @FXML
    private TextField fldActeurs;




    private Stage dialogStage;
    private Jointure jointure;
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
     * @param jointure
     */
    public void setMovieJoin(Jointure jointure) {
        this.jointure = jointure;

        fldTitreVF.setText(jointure.getTitreFR());
        fldTitreVO.setText(jointure.getTitreO());
        fldScenario.setText(jointure.getScenario());
        fldAnneeSortie.setText(jointure.getAnneeSortie());
        fldNationalite.setText(jointure.getNationalite());
        fldGenre.setText(jointure.getPrenomActeur()+" "+jointure);
        fldActeurs.setText(jointure.getPrenomActeur()+" "+jointure);
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
            jointure.setTitreFR(fldTitreVF.getText());
            jointure.setTitreO(fldTitreVO.getText());
            jointure.setScenario(fldScenario.getText());
            jointure.setAnneeSortie(fldAnneeSortie.getText());
            jointure.setNationalite(fldNationalite.getText());
            jointure.setGenre(fldGenre.getText());
            jointure.setNomRealisateur(fldRealisateur.getText());
            jointure.setNomActeur(fldActeurs.getText());

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
            errorMessage += "Champs vide!\n Merci d'entrez un titre français\n";
        }
        if (fldTitreVO.getText() == null || fldTitreVO.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez un titre original!!\n";
        }
        if (fldScenario.getText() == null || fldScenario.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez un scenario\n";
        }

        if (fldAnneeSortie.getText() == null || fldAnneeSortie.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez une année de sortie\n";
        }

        if (fldNationalite.getText() == null || fldNationalite.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez une nationalité\n";
        }

        if (fldGenre.getText() == null || fldNationalite.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez une \n";
        }

        if (fldRealisateur.getText() == null || fldNationalite.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez une \n";
        }
        if (fldActeurs.getText() == null || fldNationalite.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez une \n";
        }

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