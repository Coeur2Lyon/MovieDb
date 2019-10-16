package main.vincentfillon.views;


import main.vincentfillon.model.Correspond;
import main.vincentfillon.model.Genre;
import main.vincentfillon.model.JointureFilm;
import main.vincentfillon.model.ListeCheckBox;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;


/**
 * Fenêtre de dialogue pour éditer les informations du film
 *
 * @author Vincent Fillon
 */
public class MovieEditDialogController {

    @FXML
    private TextField fldTitreVF;
    @FXML
    private TextField fldTitreVO;
    @FXML
    private TextArea fldScenario;
    @FXML
    private TextField fldAnneeSortie;
    @FXML
    private TextField fldNationalite;
    @FXML
    private CheckBox cboxPolicier;
    @FXML
    private CheckBox cboxThriller;
    @FXML
    private CheckBox cboxFantastqiqueSF;
    @FXML
    private CheckBox cboxDrame;
    @FXML
    private CheckBox cboxBiopic;
    @FXML
    private CheckBox cboxAction;
    @FXML
    private CheckBox cboxHorreur;
    @FXML
    private CheckBox cboxComedie;
    @FXML
    private CheckBox cboxWestern;
    @FXML
    private CheckBox cboxAventure;

    public CheckBox getCboxPolicier() {
        return cboxPolicier;
    }

    public CheckBox getCboxThriller() {
        return cboxThriller;
    }

    public CheckBox getCboxFantastqiqueSF() {
        return cboxFantastqiqueSF;
    }

    public CheckBox getCboxDrame() {
        return cboxDrame;
    }

    public CheckBox getCboxBiopic() {
        return cboxBiopic;
    }

    public CheckBox getCboxAction() {
        return cboxAction;
    }

    public CheckBox getCboxHorreur() {
        return cboxHorreur;
    }

    public CheckBox getCboxComedie() {
        return cboxComedie;
    }

    public CheckBox getCboxWestern() {
        return cboxWestern;
    }

    public CheckBox getCboxAventure() {
        return cboxAventure;
    }


    private Stage dialogStage;
    private ListeCheckBox listeCheckBox;
    private JointureFilm jointureFilm;
    private Correspond correspond;
    private Genre genre;

    private boolean okClicked = false;
    ArrayList<Integer> listeGenre = new ArrayList<>();

    /**
     Initialise la classe contrôleur.
     après que la vue fxml ait été chargée.
     */
    @FXML
    private void initialize() {

    }

    /**
     * Définit/parmaètre la fenêtre de la boîte de dialogue.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        // Set the dialog icon.
        //this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }

    /**
     * Paramètre le film dans la boîte de dialogue afin qu'il puisse être éditer
     *
     * @param jointureFilm
     */
    public void setMovie(JointureFilm jointureFilm) {
        this.jointureFilm = jointureFilm;
        fldTitreVF.setText(jointureFilm.getTitreFR());
        fldTitreVO.setText(jointureFilm.getTitreO());
        fldScenario.setText(jointureFilm.getScenario());
        fldAnneeSortie.setText(jointureFilm.getAnneeSortie());
        fldNationalite.setText(jointureFilm.getNationalite());
    }


    public void setListCheckBox(ListeCheckBox listeCheckBox) {
        this.listeCheckBox = listeCheckBox;

        if (listeCheckBox.isCboxPolicier()) {
            cboxPolicier.setSelected(true);
        }
        if (listeCheckBox.isCboxAventure()) {
            cboxAventure.setSelected(true);
        }
        if (listeCheckBox.isCboxComedie()) {
            cboxComedie.setSelected(true);
        }
        if (listeCheckBox.isCboxHorreur()) {
            cboxHorreur.setSelected(true);
        }
        if (listeCheckBox.isCboxBiopic()) {
            cboxBiopic.setSelected(true);
        }
        if (listeCheckBox.isCboxDrame()) {
            cboxDrame.setSelected(true);
        }
        if (listeCheckBox.isCboxFantastqiqueSF()) {
            cboxFantastqiqueSF.setSelected(true);
        }
        if (listeCheckBox.isCboxThriller()) {
            cboxThriller.setSelected(true);
        }
        if (listeCheckBox.isCboxWestern()) {
            cboxWestern.setSelected(true);
        }
        if (listeCheckBox.isCboxAction()) {
            cboxAction.setSelected(true);
        }
    }

    /**
     *Retourne true si l'utilisateur clique sur le bouton OK, sinon false
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * Appelée quand l'utilisateur clique "OK".
     */

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            jointureFilm.setTitreFR(fldTitreVF.getText());
            jointureFilm.setTitreO(fldTitreVO.getText());
            jointureFilm.setScenario(fldScenario.getText());
            jointureFilm.setAnneeSortie(fldAnneeSortie.getText());
            jointureFilm.setNationalite(fldNationalite.getText());

            listeCheckBox.setCboxAction(cboxAction.isSelected());
            listeCheckBox.setCboxAventure(cboxAventure.isSelected());
            listeCheckBox.setCboxBiopic(cboxBiopic.isSelected());
            listeCheckBox.setCboxComedie(cboxComedie.isSelected());
            listeCheckBox.setCboxDrame(cboxDrame.isSelected());
            listeCheckBox.setCboxFantastqiqueSF(cboxFantastqiqueSF.isSelected());
            listeCheckBox.setCboxHorreur(cboxHorreur.isSelected());
            listeCheckBox.setCboxPolicier(cboxPolicier.isSelected());
            listeCheckBox.setCboxThriller(cboxThriller.isSelected());
            listeCheckBox.setCboxWestern(cboxWestern.isSelected());

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
     *Valide les entrées utilisateur dans les champs de texte(TextField).
     *
     * @return true si l'entrée est valide
     */

    private boolean isInputValid() {
        String errorMessage = "";
//TODO: Gérer le possibilités de dépassement de capacités MAX de caractères (déjà fait pour Scenario, voir capacité propriétés SQL pour les autres ).
        //TODO(Complément ci-dessus Titres O et Fr+ Nationalité: 50caractères (VARCHAR)...)
        if (fldTitreVF.getText() == null || fldTitreVF.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez un titre français\n";
        }
        if (fldTitreVO.getText() == null || fldTitreVO.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez un titre original!!\n";
        }
        if (fldScenario.getText() == null || fldScenario.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez un scenario\n";
        }
        if(fldScenario.getText().length()>65500){
            errorMessage +="Le texte que vous avez entré comporte: "+fldScenario.getText().length()+" caractères,\n Merci de ne pas dépasser 65500 caractères";
        }

        if (fldAnneeSortie.getText() == null || fldAnneeSortie.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez une année de sortie\n";
        }

        if (fldNationalite.getText() == null || fldNationalite.getText().length() == 0) {
            errorMessage += "Champs vide!\n Merci d'entrez une nationalité\n";
        }

        if (cboxAction.isSelected() == false && cboxAventure.isSelected() == false && cboxBiopic.isSelected() == false && cboxDrame.isSelected() == false && cboxComedie.isSelected() == false && cboxFantastqiqueSF.isSelected() == false && cboxHorreur.isSelected() == false && cboxPolicier.isSelected() == false && cboxThriller.isSelected() == false && cboxWestern.isSelected() == false) {
            errorMessage += "Champs vide!\n Merci de cocher au moins un genre\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Affiche le message d'erreur.
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