package fr.vincentfillon.views;

import fr.vincentfillon.Main;
import fr.vincentfillon.connectivity.ConnectionClass;
import fr.vincentfillon.dao.Dao;
import fr.vincentfillon.dao.JointureFilmDAO;
import fr.vincentfillon.model.ActeurRealisateur;
import fr.vincentfillon.model.JointureActeursRealisateur;
import fr.vincentfillon.model.JointureFilm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class JoueEditDialogController extends InterfaceFilmsAdminController {


    @FXML
    public TableView<JointureFilm> movieJoinTable;
    @FXML
    private TableColumn<JointureFilm, String> colTitreVF;
    @FXML
    private TableColumn<JointureFilm, String> colAnneeSortie;

    @FXML
    private Label lblPrenomTitre;
    @FXML
    private Label lblNomTitre;



    @FXML
    private Label lblId;
    public String getLblId() {
        String lblIdText=lblId.getText();
        return lblIdText;
    }
    @FXML
    private Label lblTitreVF;
    @FXML
    private Label lblTitreVO;
    @FXML
    private Label lblScenario;
    @FXML
    private Label lblAnneeSortie;
    @FXML
    private Label lblNationalite;
    @FXML
    private Label lblGenre;
    @FXML
    private Label lblRealisateur;
    @FXML
    private Label lblActeurs;


    // Reference à InterfacePrincipaleController
    private InterfacePrincipaleController jointure;
    private MovieJoinEditDialogController movieJoinEditDialogController;
    private JointureActeursRealisateur acteurRealisateur;
    private Stage dialogStage;
    private JointureFilm jointureFilm;

    // Reference to the main application.
    private Main main;
    InterfaceFilmsAdminController interfaceFilmsAdminController = new InterfaceFilmsAdminController();

    private boolean okClicked = false;


    @FXML
    private ObservableList<JointureFilm> movieJoinData = FXCollections.observableArrayList();




    /* The constructor is called before the initialize() method.
     */
    public JoueEditDialogController() {

        super();
        Dao<JointureFilm> jointureDAO = new JointureFilmDAO(ConnectionClass.connecte());

//Pour trouver le film d'indice i:
        //  JointureFilm jointure = jointureDAO.find(1);
        movieJoinData.setAll(jointureDAO.findAll());
//        movieJoinData.add(new JointureFilm("Impitoyable", "Unforgiven", "Comboy à la retraite entraîné par son ancien co-équipier dans une mission périlleuse", "1999", "US"));
//        movieJoinData.add(new JointureFilm("Fight Club", "Fight Club", "Un employé de bureau insomniaque analyse la société de consommation de ses points de vue", "1999", "US"));
//        movieJoinData.add(new JointureFilm("L'armée des Ombres", "L'armée des Ombres", "Un ingénieur soupçonné de pensée gaullistes est arrêté par la Gestapo", "1969", "FR"));
//        movieJoinData.add(new JointureFilm("Les tontons flingueurs", "Les tontons flingueurs", "un ex-truand reconverti dans le négoce de matériel de travaux publics à Montauban voit sa petite vie tranquille basculer lorsque son ami d'enfance, Louis, dit le Mexicain, un gangster notoire de retour à Paris, l'appelle à son chevet.", "1963", "FR"));
    }




    public ObservableList<JointureFilm> getMovieJoinData() {
        return movieJoinData;
    }



    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */


    @FXML
    public void initialize() {
        super.initialize();
        // Initialize the movie table with the two columns.
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param jointure
     */
    public void setMovie(InterfacePrincipaleController jointure) {
        super.setMovie(jointure);
    }

    /**
     * Fills all text fields to show details about the movie.
     * If the specified movie is null, all text fields are cleared.
     *
     * @param jointureFilm the movie or null
     */
    public void showMovieJoinDetails(JointureFilm jointureFilm) {
        super.showMovieJoinDetails(jointureFilm);
    }

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new movie.
     */
    @FXML
    public void addNewMovieJoin() {
        super.addNewMovieJoin();
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected movie.
     */


    @FXML
    public void editMovieJoin() {
        super.editMovieJoin();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;

        // Set the dialog icon.
        this.dialogStage.getIcons().add(new Image("file:resources/images/edit.png"));
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void handleOK() {
        if (isInputValid()) {
            //JointureFilm selectedFilm = movieJoinTable.getSelectionModel().getSelectedItem();
            okClicked = true;
            dialogStage.close();
        }
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    public void setInfosActeur(JointureActeursRealisateur acteurRealisateur) {
        this.acteurRealisateur = acteurRealisateur;
//Paramétrages des infos à afficher dans les TextFields de la fenêtre ActRelaEditDialog
        lblPrenomTitre.setText(acteurRealisateur.getPrenom());
        lblNomTitre.setText(acteurRealisateur.getNom());
        lblId.setText(String.valueOf(acteurRealisateur.getIdJointure()));
    }


    public TableView<JointureFilm> getMovieJoinTable() {
        return movieJoinTable;
    }


    public int getIdSelectedFilm() {
        int idSelectedFilm=1;
        Dao<JointureFilm> jointureFilmDao = new JointureFilmDAO(ConnectionClass.connecte());
        JointureFilm selectedFilm= movieJoinTable.getSelectionModel().getSelectedItem();
        selectedFilm=jointureFilmDao.find(selectedFilm.getIdJointure());
        idSelectedFilm=selectedFilm.getIdJointure();

        return idSelectedFilm;
    }

    public boolean isInputValid() {
        String errorMessage = "";
        JointureFilm selectedFilmIsValid = movieJoinTable.getSelectionModel().getSelectedItem();

        if (selectedFilmIsValid == null) {
            errorMessage += "Pas de film sélectionné!\n Merci de sélectionner un film\n";
        }

        if (errorMessage.length() == 0) {
            ArrayList<Integer> listeId=new ArrayList<>();
            int idSelectedFilm=selectedFilmIsValid.getIdJointure();
            listeId.add(idSelectedFilm);
            return true;
        } else {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }



}
