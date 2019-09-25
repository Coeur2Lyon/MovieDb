package fr.vincentfillon.views;

import fr.vincentfillon.Main;
import fr.vincentfillon.connectivity.ConnectionClass;
import fr.vincentfillon.dao.CorrespondDAO;
import fr.vincentfillon.dao.Dao;
import fr.vincentfillon.dao.JointureDAO;
import fr.vincentfillon.model.Correspond;
import fr.vincentfillon.model.JointureFilm;
import fr.vincentfillon.model.ListeCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Optional;

public class InterfaceJointureFilmsAdminController {

    @FXML
    private TableView<JointureFilm> movieJoinTable;
    @FXML
    private TableColumn<JointureFilm, String> colTitreVF;
    @FXML
    private TableColumn<JointureFilm, String> colAnneeSortie;

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
    private ObservableList<JointureFilm> movieJoinData = FXCollections.observableArrayList();

    private ObservableList<ListeCheckBox> listeCheckboxData = FXCollections.observableArrayList();


    /* The constructor is called before the initialize() method.
     */
    public InterfaceJointureFilmsAdminController()  {


        Dao<JointureFilm> jointureDAO = new JointureDAO(ConnectionClass.connecte());

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

    public ObservableList<ListeCheckBox> getListeCheckBoxData() {
        return listeCheckboxData;
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
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
    public void showMovieJoinDetails(JointureFilm jointureFilm) {
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
    public void addNewMovieJoin() {
        Dao<JointureFilm> jointureDAO = new JointureDAO(ConnectionClass.connecte());
        Dao<Correspond> correspondDao = new CorrespondDAO(ConnectionClass.connecte());
        JointureFilm tempJointureFilm = new JointureFilm();
        Correspond tempCorrespond = new Correspond();
        ListeCheckBox listeCheckBox = new ListeCheckBox();

        boolean okClicked = Main.showMovieJoinEditDialog(tempJointureFilm, listeCheckBox);


        if (okClicked) {
            getMovieJoinData().add(tempJointureFilm);
            getListeCheckBoxData().add(listeCheckBox);

            jointureDAO.create(tempJointureFilm);

            int idFilm = jointureDAO.findIdMax();
            tempCorrespond.setIdFilm(idFilm);

            System.out.println("L'IdFilm doît être égal au dernier Idjointure:  " + idFilm);
            System.out.println("Le get de tempCorrespond doît être égale à Id Film:  " + tempCorrespond.getIdFilm());

            //System.out.println("Id du dernier tempjointure ajouté" +tempJointureFilm.getIdJointure());
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
    public void editMovieJoin() {
        JointureFilm selectedJoinMovie = movieJoinTable.getSelectionModel().getSelectedItem();

        Dao<JointureFilm> jointureDao = new JointureDAO(ConnectionClass.connecte());
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

                if (listegenreFromIdFilm.isCboxPolicier() && !listIntGenre.contains(0)) {
                    tempCorrespond.setIdGenre(0);
                    correspondDao.create(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxPolicier() && listIntGenre.contains(0)) {
                    tempCorrespond.setIdGenre(0);
                    correspondDao.delete(tempCorrespond);
                }

                if (listegenreFromIdFilm.isCboxThriller() && !listIntGenre.contains(1)) {
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
            // Pas de film sélectionné.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Pas de sélection");
            alert.setHeaderText("Aucun film sélectionné.");
            alert.setContentText("Merci de sélectionner un film");
            alert.showAndWait();
        }
    }

    /**
     * Called when the user clicks on the delete button.
     */
    @FXML
    private void deleteMovieJoin() {
        JointureFilm selectedJoinMovie = movieJoinTable.getSelectionModel().getSelectedItem();
        Dao<JointureFilm> jointureDAO = new JointureDAO(ConnectionClass.connecte());
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
        }else {
            // Pas de film sélectionné.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Pas de sélection");
            alert.setHeaderText("Aucun film sélectionné.");
            alert.setContentText("Merci de sélectionner un film");
            alert.showAndWait();
        }
    }

}
