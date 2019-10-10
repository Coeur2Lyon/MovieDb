package fr.vincentfillon.views;

import fr.vincentfillon.Main;
import fr.vincentfillon.connectivity.ConnectionClass;
import fr.vincentfillon.dao.Dao;
import fr.vincentfillon.dao.JointureFilmDAO;
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
    private MovieEditDialogController movieEditDialogController;
    private JointureActeursRealisateur acteurRealisateur;
    private Stage dialogStage;
    private JointureFilm jointureFilm;

   // Référence à l'interface principale Main
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
        //  JointureFilm jointureFilm = jointureDAO.find(1);
        movieJoinData.setAll(jointureDAO.findAll());
//        movieJoinData.add(new JointureFilm("Impitoyable", "Unforgiven", "Comboy à la retraite entraîné par son ancien co-équipier dans une mission périlleuse", "1999", "US"));
//        movieJoinData.add(new JointureFilm("Fight Club", "Fight Club", "Un employé de bureau insomniaque analyse la société de consommation de ses points de vue", "1999", "US"));
//        movieJoinData.add(new JointureFilm("L'armée des Ombres", "L'armée des Ombres", "Un ingénieur soupçonné de pensée gaullistes est arrêté par la Gestapo", "1969", "FR"));
//        movieJoinData.add(new JointureFilm("Les tontons flingueurs", "Les tontons flingueurs", "un ex-truand reconverti dans le négoce de matériel de travaux publics à Montauban voit sa petite vie tranquille basculer lorsque son ami d'enfance, Louis, dit le Mexicain, un gangster notoire de retour à Paris, l'appelle à son chevet.", "1963", "FR"));
    }




    public ObservableList<JointureFilm> getMovieData() {
        return movieJoinData;
    }



    /**
     Initialise la classe contrôleur.
     après que la vue fxml ait été chargée.
     */


    @FXML
    public void initialize() {
        super.initialize();
        // Initialize the movie table with the two columns.
    }

    /**
     Est appellée par l'application principale Main pour se donner une référence à elle-même
     *
     * @param jointureFilm
     */
    public void setMovie(InterfacePrincipaleController jointureFilm) {
        super.setMovie(jointureFilm);
    }

    /**
     * Remplie les champs de textes/Labels pour afficher les détails du film.
     * If the specified movie is null, all text fields are cleared.
     *
     * @param jointureFilm the movie or null
     */
    public void showMovieJoinDetails(JointureFilm jointureFilm) {
        super.showMovieJoinDetails(jointureFilm);
    }

    /**
     * Appelée quand l'utilisateur clique the new button. Opens a dialog to edit
     * details for a new movie.
     */
    @FXML
    public void addNewMovieJoin() {
        super.addNewMovieJoin();
    }

    /**
     * Appelée quand l'utilisateur clique Le bouton "Editer...". Opens a dialog to edit
     * Ouvre une fenêtre de dialogue pour éditer les informations du film sélectionné.
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
     * Appelée quand l'utilisateur clique sur le boutton "Effacer..."
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
            // Affiche le message d'erreur.
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
