package fr.vincentfillon.views;

import fr.vincentfillon.Main;
import fr.vincentfillon.connectivity.ConnectionClass;
import fr.vincentfillon.dao.CorrespondDAO;
import fr.vincentfillon.dao.Dao;
import fr.vincentfillon.dao.JointureDAO;
import fr.vincentfillon.model.Correspond;
import fr.vincentfillon.model.Jointure;
import fr.vincentfillon.model.ListeCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
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


    // Reference à InterfacePrincipaleController
    private InterfacePrincipaleController jointure;
    private MovieJoinEditDialogController movieJoinEditDialogController;
    // Reference to the main application.
    private Main main;

    @FXML
    private ObservableList<Jointure> movieJoinData = FXCollections.observableArrayList();

    private ObservableList<ListeCheckBox> listeCheckboxData = FXCollections.observableArrayList();


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


    public ObservableList<Jointure> getMovieJoinData() {
        return movieJoinData;
    }

    public ObservableList<ListeCheckBox> getListeCheckBoxData() {
        return listeCheckboxData;
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
        movieJoinTable.setItems(getMovieJoinData());
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
    /*@FXML TODO:Effacer addMovie quand appli stable
    private void addNewMovie() {
        Dao<Film> filmDAO = new FilmDAO(ConnectionClass.connecte());
        Film tempFilm = new Film();
        boolean okClicked = Main.showMovieJoinEditDialog(tempFilm);
        if (okClicked) {
            getMovieData().add(tempFilm);
            filmDAO.create(tempFilm);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Ajout de film");
            alert.setHeaderText("Le film a bien été ajouté");
        }
    }*/

    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new movie.
     */
    @FXML
    private void addNewMovieJoin() {
        Dao<Jointure> jointureDAO = new JointureDAO(ConnectionClass.connecte());
        Dao<Correspond> correspondDao = new CorrespondDAO(ConnectionClass.connecte());
        Jointure tempJointure = new Jointure();
        Correspond tempCorrespond = new Correspond();
        ListeCheckBox listeCheckBox = new ListeCheckBox();

        boolean okClicked = Main.showMovieJoinEditDialog(tempJointure, listeCheckBox);


        if (okClicked) {
            getMovieJoinData().add(tempJointure);
            getListeCheckBoxData().add(listeCheckBox);

            jointureDAO.create(tempJointure);

            int idFilm = jointureDAO.findIdMax();
            tempCorrespond.setIdFilm(idFilm);

            System.out.println("L'IdFilm doît être égal au dernier Idjointure:  " + idFilm);
            System.out.println("Le get de tempCorrespond doît être égale à Id Film:  " + tempCorrespond.getIdFilm());

            //System.out.println("Id du dernier tempjointure ajouté" +tempJointure.getIdJointure());
            // System.out.println("IdFilm de tempCorrespond qui doit être idenntique a Idtempjointure (ci dessus):" +tempCorrespond.getIdFilm());

            if (listeCheckBox.isCboxPolicier()) {
                tempCorrespond.setIdGenre(0);
                correspondDao.create(tempCorrespond);
            }
            if (listeCheckBox.isCboxThriller()) {
                ;
                tempCorrespond.setIdGenre(1);
                correspondDao.create(tempCorrespond);
            }
            if (listeCheckBox.isCboxFantastqiqueSF()) {
                tempCorrespond.setIdGenre(2);
                correspondDao.create(tempCorrespond);
            }
            if (listeCheckBox.isCboxDrame()) {
                tempCorrespond.setIdGenre(3);
                correspondDao.create(tempCorrespond);
            }
            if (listeCheckBox.isCboxBiopic()) {
                tempCorrespond.setIdGenre(4);
                correspondDao.create(tempCorrespond);
            }
            if (listeCheckBox.isCboxAction()) {
                tempCorrespond.setIdGenre(5);
                correspondDao.create(tempCorrespond);
            }
            if (listeCheckBox.isCboxHorreur()) {
                tempCorrespond.setIdGenre(6);
                correspondDao.create(tempCorrespond);
            }
            if (listeCheckBox.isCboxComedie()) {
                tempCorrespond.setIdGenre(7);
                correspondDao.create(tempCorrespond);
            }

            if (listeCheckBox.isCboxWestern()) {
                tempCorrespond.setIdGenre(8);
                correspondDao.create(tempCorrespond);
            }
            if (listeCheckBox.isCboxAventure()) {
                tempCorrespond.setIdGenre(9);
                correspondDao.create(tempCorrespond);
            }
//TODO Vérifier si cette ligne est vraiment indispensable (Tout à la fin)
            movieJoinData.setAll(jointureDAO.findAll());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Ajout d'un film");
            alert.setHeaderText("Le film a bien été ajouté");
            alert.showAndWait();

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
        Dao<Correspond> correspondDao = new CorrespondDAO(ConnectionClass.connecte());

        Correspond tempCorrespond = new Correspond();
        int idFilm = selectedJoinMovie.getIdJointure();
        tempCorrespond.setIdFilm(idFilm);
        ListeCheckBox listegenreFromIdFilm = ((CorrespondDAO) correspondDao).extractListeCheckBoxFromJointure(selectedJoinMovie);

        ArrayList<Integer> listIntGenre = ((CorrespondDAO) correspondDao).extractIdListFromJointure(selectedJoinMovie);
        System.out.println("Essai en sélectionnant un DRAME doit être true si Drame: " + listegenreFromIdFilm.isCboxDrame());


        if (selectedJoinMovie != null) {
            boolean okClicked = Main.showMovieJoinEditDialog(selectedJoinMovie, listegenreFromIdFilm);
            if (okClicked) {
                showMovieJoinDetails(selectedJoinMovie);
                jointureDao.update(selectedJoinMovie);
                System.out.println("La liste doit être [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] quand tout est coché: " + listIntGenre);
                if (listegenreFromIdFilm.isCboxPolicier() && !listIntGenre.contains(0)) {
                    System.out.println("IF : POLICIER a été Coché MAIS PAS dans la liste(DONC coché pour la première fois");
                    tempCorrespond.setIdGenre(0);
                    correspondDao.create(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxPolicier() && listIntGenre.contains(0)) {
                    System.out.println("IF : POLICIER a été DECOCHé, il faut l'effacer avec les ID");
                    tempCorrespond.setIdGenre(0);
                    correspondDao.delete(tempCorrespond);
                }

                if (listegenreFromIdFilm.isCboxThriller() && !listIntGenre.contains(1)) {
                    System.out.println("IF : THRILLER a été Coché MAIS PAS dans la liste(DONC coché pour la première fois");

                    tempCorrespond.setIdGenre(1);
                    correspondDao.create(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxThriller() && listIntGenre.contains(1)) {
                    tempCorrespond.setIdGenre(1);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxFantastqiqueSF() && !listIntGenre.contains(2)) {
                    tempCorrespond.setIdGenre(2);
                    correspondDao.create(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxFantastqiqueSF() && listIntGenre.contains(2)) {
                    tempCorrespond.setIdGenre(2);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxDrame() && !listIntGenre.contains(3)) {
                    tempCorrespond.setIdGenre(3);
                    correspondDao.create(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxDrame() && listIntGenre.contains(3)) {
                    tempCorrespond.setIdGenre(3);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxBiopic() && !listIntGenre.contains(4)) {
                    tempCorrespond.setIdGenre(4);
                    correspondDao.create(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxBiopic() && listIntGenre.contains(4)) {
                    tempCorrespond.setIdGenre(4);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxAction() && !listIntGenre.contains(5)) {
                    tempCorrespond.setIdGenre(5);
                    correspondDao.create(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxAction() && listIntGenre.contains(5)) {
                    tempCorrespond.setIdGenre(5);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxHorreur() && !listIntGenre.contains(6)) {
                    tempCorrespond.setIdGenre(6);
                    correspondDao.create(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxHorreur() && listIntGenre.contains(6)) {
                    tempCorrespond.setIdGenre(6);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxComedie() && !listIntGenre.contains(7)) {
                    tempCorrespond.setIdGenre(7);
                    correspondDao.create(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxComedie() && listIntGenre.contains(7)) {
                    tempCorrespond.setIdGenre(7);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxWestern() && !listIntGenre.contains(8)) {
                    tempCorrespond.setIdGenre(8);
                    correspondDao.create(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxWestern() && listIntGenre.contains(8)) {
                    tempCorrespond.setIdGenre(8);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxAventure() && !listIntGenre.contains(9)) {
                    tempCorrespond.setIdGenre(9);
                    correspondDao.create(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxAventure() && listIntGenre.contains(9)) {
                    tempCorrespond.setIdGenre(9);
                    correspondDao.delete(tempCorrespond);
                }
//TODO Vérifier si cette ligne est vraiment indispensable (Tout à la fin)
                movieJoinData.setAll(jointureDao.findAll());
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Pas de sélection");
            alert.setHeaderText("Aucun film sélectionné");
            alert.setContentText("Merci de sélectionner un film");
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
            int tailleListe;
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
