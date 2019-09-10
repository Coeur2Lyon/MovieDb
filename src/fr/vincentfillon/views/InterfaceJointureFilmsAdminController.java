package fr.vincentfillon.views;

import fr.vincentfillon.Main;
import fr.vincentfillon.connectivity.ConnectionClass;
import fr.vincentfillon.dao.Dao;
import fr.vincentfillon.dao.FilmDAO;
import fr.vincentfillon.dao.JointureDAO;
import fr.vincentfillon.model.Film;
import fr.vincentfillon.model.Jointure;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class InterfaceJointureFilmsAdminController {

    @FXML
    private TableView<Jointure> movieJoinTable;
    @FXML
    private TableColumn<Jointure, String> colTitreVF;
    @FXML
    private TableColumn<Jointure, String> colAnneeSortie;

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



    private InterfacePrincipaleController jointure;
    // Reference to the main application.
    private Main main;

    @FXML
    private ObservableList<Jointure> movieJoinData = FXCollections.observableArrayList();

    /* The constructor is called before the initialize() method.
     */
    public InterfaceJointureFilmsAdminController() {


        Dao<Jointure> jointureDAO = new JointureDAO(ConnectionClass.connecte());



//Pour trouver le film d'indice i:
      //  Jointure jointure = jointureDAO.find(1);
        movieJoinData.setAll(jointureDAO.findAll());
//        movieJoinData.add(new Jointure("Impitoyable", "Unforgiven", "Comboy à la retraite entraîné par son ancien co-équipier dans une mission périlleuse", "1999", "US"));
//        movieJoinData.add(new Jointure("Fight Club", "Fight Club", "Un employé de bureau insomniaque analyse la société de consommation de ses points de vue", "1999", "US"));
//        movieJoinData.add(new Jointure("L'armée des Ombres", "L'armée des Ombres", "Un ingénieur soupçonné de pensée gaullistes est arrêté par la Gestapo", "1969", "FR"));
//        movieJoinData.add(new Jointure("Les tontons flingueurs", "Les tontons flingueurs", "un ex-truand reconverti dans le négoce de matériel de travaux publics à Montauban voit sa petite vie tranquille basculer lorsque son ami d'enfance, Louis, dit le Mexicain, un gangster notoire de retour à Paris, l'appelle à son chevet.", "1963", "FR"));
    }

    public ObservableList<Jointure> getmovieJoinData() {
        return movieJoinData;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the movie table with the two columns.
        colTitreVF.setCellValueFactory(cellData -> cellData.getValue().titreFRProperty());
        colAnneeSortie.setCellValueFactory(cellData -> cellData.getValue().anneeSortieProperty());

        // Clear movie details.
        showMovieJoinDetails(null);

        // Listen for selection changes and show the movie details when changed.
        movieJoinTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMovieJoinDetails(newValue));
        // Add observable list data to the table
        movieJoinTable.setItems(movieJoinData);
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param jointure
     */
    public void setMovie(InterfacePrincipaleController jointure) {
        this.jointure = jointure;
        //Add observable list data to the table
        movieJoinTable.setItems(getmovieJoinData());
    }

    /**
     * Fills all text fields to show details about the movie.
     * If the specified movie is null, all text fields are cleared.
     *
     * @param jointureFilm the movie or null
     */
    private void showMovieJoinDetails(Jointure jointureFilm) {
        if (jointureFilm != null) {
            // Fill the labels with info from the movie object.
            lblTitreVF.setText(jointureFilm.getTitreFR());
            lblTitreVO.setText(jointureFilm.getTitreO());
            lblScenario.setText(jointureFilm.getScenario());
            lblAnneeSortie.setText(jointureFilm.getAnneeSortie());
            lblNationalite.setText(jointureFilm.getNationalite());
            lblGenre.setText(jointureFilm.getGenre());
            lblRealisateur.setText(jointureFilm.getRealisateurs());
            lblActeurs.setText(jointureFilm.getActeurs());

        } else {
            // movie is null, remove all the text.
            lblTitreVF.setText("");
            lblTitreVO.setText("");
            lblScenario.setText("");
            lblAnneeSortie.setText("");
            lblNationalite.setText("");
            lblGenre.setText("");
            lblRealisateur.setText("");
            lblActeurs.setText("");
        }
    }


    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new movie.
     */
    @FXML
    private void addNewMovieJoin() {
        Dao<Jointure> jointureDao = new JointureDAO(ConnectionClass.connecte());
        Jointure tempJointureFilm = new Jointure();
        boolean okClicked = Main.showMovieJoinEditDialog(tempJointureFilm);
        if (okClicked) {
            getmovieJoinData().add(tempJointureFilm);
            jointureDao.create(tempJointureFilm);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Ajout d'un film");
            alert.setHeaderText("Le film a bien été ajouté");
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected movie.
     */
    @FXML
    private void editMovieJoin() {

        Jointure selectedJoinMovie = movieJoinTable.getSelectionModel().getSelectedItem();
        Dao<Jointure> jointureDao = new JointureDAO(ConnectionClass.connecte());
        if (selectedJoinMovie != null) {
            boolean okClicked = Main.showMovieJoinEditDialog(selectedJoinMovie);
            if (okClicked) {
                showMovieJoinDetails(selectedJoinMovie);
                jointureDao.update(selectedJoinMovie);
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Movie Selected");
            alert.setContentText("Please select a movie in the table.");
            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void deleteMovieJoin() {
        Jointure selectedJoinMovie = movieJoinTable.getSelectionModel().getSelectedItem();
        Dao<Jointure> jointureDAO = new JointureDAO(ConnectionClass.connecte());
        if (selectedJoinMovie != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Suppression de film");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer le film sélectionné? ");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                jointureDAO.delete(selectedJoinMovie);
            }
            movieJoinData.remove(selectedJoinMovie);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Movie Selected");
            alert.setContentText("Please select a movie in the table.");
            alert.showAndWait();
        }
    }
}
