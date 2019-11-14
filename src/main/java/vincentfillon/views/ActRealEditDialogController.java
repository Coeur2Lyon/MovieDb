package vincentfillon.views;

import vincentfillon.model.ActeurRealisateur;
import vincentfillon.model.JointureActeursRealisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Boîte de dialogue pour éditer un acteur/réalisateur.
 *
 * @author Vincent Fillon
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
    private JointureActeursRealisateur acteurRealisateur;
    private boolean okClicked = false;

    /**
     Initialise la classe contrôleur.
     après que la vue fxml ait été chargée.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Définit/Paramètre la fenêtre de la boîte de dialogue
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }

    /**
     * Définit/paramètre l'instance JointureActeursRealisateur
     *afin que les infos de l'acteur-réal sélectionné soit affichés dans la boîte de dialogue.
     * @param jointureActeursRealisateur
     */
    public void setActeurRealisateur(JointureActeursRealisateur jointureActeursRealisateur) {
        this.acteurRealisateur=jointureActeursRealisateur;
//Paramétrages des infos à afficher dans les TextFields de la fenêtre ActRelaEditDialog
        fldPrenom.setText(acteurRealisateur.getPrenom());
        fldNom.setText(acteurRealisateur.getNom());
        fldAnneeNaissance.setText(acteurRealisateur.getAnneeNaissance());
        fldNationalite.setText(acteurRealisateur.getNationalite());
    }

    /**
     * Retourne "true" si l'utilisateur clique "OK"n sinon "false"
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Appelée quand l'utilisateur clique ok.
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
     * Appelée quand l'utilisateur clique sur "Annuler".
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * Valide les entrées utilisateur dans les champs de texte.
     *
     * @return true si l'entrée est valide
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
            // Affiche le message d'erreur.
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Champs non valide");
            alert.setHeaderText("Merci de rectifier les champs non valides");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    public void handleDelete(ActionEvent actionEvent) {
    }
}