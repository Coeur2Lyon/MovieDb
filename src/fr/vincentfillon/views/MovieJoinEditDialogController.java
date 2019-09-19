package fr.vincentfillon.views;


import fr.vincentfillon.model.Correspond;
import fr.vincentfillon.model.Genre;
import fr.vincentfillon.model.Jointure;
import fr.vincentfillon.model.ListeCheckBox;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;
//import ch.makery.address.util.DateUtil;

/**
 * Dialog to edit details of a movie.
 *
 * @author Vincent Fillon
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
    private Jointure jointure;
    private Correspond correspond;
    private Genre genre;

    private boolean okClicked = false;
    ArrayList<Integer> listeGenre = new ArrayList<>();

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
     * @param jointure
     */
    public void setMovie(Jointure jointure) {
        this.jointure = jointure;
        fldTitreVF.setText(jointure.getTitreFR());
        fldTitreVO.setText(jointure.getTitreO());
        fldScenario.setText(jointure.getScenario());
        fldAnneeSortie.setText(jointure.getAnneeSortie());
        fldNationalite.setText(jointure.getNationalite());
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
        System.out.println("listeCheckBox.isCboxDrame() dans la méthode: setListCheckBox()" + listeCheckBox.isCboxDrame());
        System.out.println("listeCheckBox.isCboxPolicier()dans la méthode: setLisCheckBox())" + listeCheckBox.isCboxPolicier());

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
    //CheckBox cboxAventure, CheckBox cboxWestern, CheckBox cboxComedie, CheckBox cboxHorreur, CheckBox cboxAction, CheckBox cboxBiopic, CheckBox cboxDrame, CheckBox cboxFantastqiqueSF, CheckBox cboxThriller, CheckBox cboxPolicier
    @FXML
    private void handleOk() {
        if (isInputValid()) {
            jointure.setTitreFR(fldTitreVF.getText());
            jointure.setTitreO(fldTitreVO.getText());
            jointure.setScenario(fldScenario.getText());
            jointure.setAnneeSortie(fldAnneeSortie.getText());
            jointure.setNationalite(fldNationalite.getText());

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

            System.out.println("listeCheckBox.isCboxThriller() dans la méthode: handleOk()" + listeCheckBox.isCboxThriller());
            System.out.println("listeCheckBox.isCboxPolicier()dans la méthode: handleOk())" + listeCheckBox.isCboxPolicier());

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

        if (cboxAction.isSelected() == false && cboxAventure.isSelected() == false && cboxBiopic.isSelected() == false && cboxDrame.isSelected() == false && cboxComedie.isSelected() == false && cboxFantastqiqueSF.isSelected() == false && cboxHorreur.isSelected() == false && cboxPolicier.isSelected() == false && cboxThriller.isSelected() == false && cboxWestern.isSelected() == false) {
            errorMessage += "Champs vide!\n Merci de cocher au moins un genre\n";
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