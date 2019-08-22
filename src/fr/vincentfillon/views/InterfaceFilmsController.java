package fr.vincentfillon.views;

import fr.vincentfillon.Main;
import fr.vincentfillon.connectivity.ConnectionClass;
import fr.vincentfillon.dao.Dao;
import fr.vincentfillon.dao.FilmDAO;
import fr.vincentfillon.model.Film;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class InterfaceFilmsController {

    @FXML
    private TableView<Film> movieTable;
    @FXML
    private TableColumn<Film, String> colTitreVF;
    @FXML
    private TableColumn<Film, String> colTitreVO;

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

    // Reference to the main application.
    private InterfacePrincipaleController film;
    // Reference to the main application.
    private Main main;

    @FXML
    // private ComboBox<Film> myComboBox;
    private ObservableList<Film> movieData = FXCollections.observableArrayList();

    /* The constructor is called before the initialize() method.
     */
    public InterfaceFilmsController() {

        Dao<Film> filmDAO = new FilmDAO(ConnectionClass.connecte());


        //int i=1;
        //Film film = filmDAO.find(i);

        //TODO ajout méthode findAll à filmDAO pour charger tous les films
      movieData.setAll(filmDAO.findAll());

//        movieData.add(new Film("Impitoyable", "Unforgiven", "Comboy à la retraite entraîné par son ancien co-équipier dans une mission périlleuse", "1999", "US"));
//        movieData.add(new Film("Fight Club", "Fight Club", "Un employé de bureau insomniaque analyse la société de consommation de ses points de vue", "1999", "US"));
//        movieData.add(new Film("L'armée des Ombres", "L'armée des Ombres", "Un ingénieur soupçonné de pensée gaullistes est arrêté par la Gestapo", "1969", "FR"));
//        movieData.add(new Film("Les tontons flingueurs", "Les tontons flingueurs", "un ex-truand reconverti dans le négoce de matériel de travaux publics à Montauban voit sa petite vie tranquille basculer lorsque son ami d'enfance, Louis, dit le Mexicain, un gangster notoire de retour à Paris, l'appelle à son chevet.", "1963", "FR"));


    }

    public ObservableList<Film> getMovieData() {
        return movieData;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the movie table with the two columns.
        colTitreVF.setCellValueFactory(cellData -> cellData.getValue().titreVFProperty());
        colTitreVO.setCellValueFactory(cellData -> cellData.getValue().titreOProperty());

        // Clear movie details.
        showMovieDetails(null);
        //showMovieDetails(Film, film);


        // Listen for selection changes and show the movie details when changed.
        movieTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMovieDetails(newValue));
        // Add observable list data to the table
        movieTable.setItems(movieData);

    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param film
     */
    public void setMovie(InterfacePrincipaleController film) {
        this.film = film;


        //Add observable list data to the table
        movieTable.setItems(getMovieData());
    }

    /**
     * Fills all text fields to show details about the movie.
     * If the specified movie is null, all text fields are cleared.
     *
     * @param film the movie or null
     */
    private void showMovieDetails(Film film) {
        if (film != null) {
            // Fill the labels with info from the movie object.
            lblTitreVF.setText(film.getTitreFR());
            lblTitreVO.setText(film.getTitreO());
            lblScenario.setText(film.getScenario());
            lblAnneeSortie.setText(film.getAnneeSortie());
            lblNationalite.setText(film.getnationalite());

            //lbldate.setText(DateUtil.format(film.getBirthday()));
        } else {
            // movie is null, remove all the text.
            lblTitreVF.setText("");
            lblTitreVO.setText("");
            lblScenario.setText("");
            lblAnneeSortie.setText("");
            lblNationalite.setText("");
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    /*@FXML
    private void handleDeleteMovie() {
        int selectedIndex = movieTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            movieTable.getItems().remove(selectedIndex);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Movie Selected");
            alert.setContentText("Please select a movie in the table.");

            alert.showAndWait();
        }
    }*/

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new movie.
     */
    @FXML
    private void addNewMovie() {
        Film tempFilm = new Film();
        boolean okClicked = Main.showMovieEditDialog(tempFilm);
        if (okClicked) {
            getMovieData().add(tempFilm);
        }

    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected movie.
     */

    @FXML
   private void handleEditMovie() {
        Film selectedMovie = movieTable.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            boolean okClicked = Main.showMovieEditDialog(selectedMovie);
            if (okClicked) {
                showMovieDetails(selectedMovie);
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


}
