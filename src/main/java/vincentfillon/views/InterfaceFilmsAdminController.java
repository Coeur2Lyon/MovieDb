package vincentfillon.views;

import vincentfillon.Main;
import vincentfillon.connectivity.ConnectionClass;
import vincentfillon.dao.CorrespondDAO;
import vincentfillon.dao.Dao;
import vincentfillon.dao.JointureFilmDAO;
import vincentfillon.model.Correspond;
import vincentfillon.model.JointureFilm;
import vincentfillon.model.ListeCheckBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.Optional;

public class InterfaceFilmsAdminController {

    @FXML
    private TableView<JointureFilm> movieTable;
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


    // Référence à InterfacePrincipaleController
    private InterfacePrincipaleController jointure;
    private MovieEditDialogController movieEditDialogController;
    // Référence à l'application principale Main.
    private Main main;

    @FXML
    private ObservableList<JointureFilm> movieData = FXCollections.observableArrayList();

    private ObservableList<ListeCheckBox> listeCheckboxData = FXCollections.observableArrayList();

    public InterfaceFilmsAdminController() {
        Dao<JointureFilm> jointureFilmDao = new JointureFilmDAO(ConnectionClass.connecte());
        movieData.setAll(jointureFilmDao.findAll());
// On peut ajouter manuellement des données au tableau (pour tests):
// movieData.add(new JointureFilm("Impitoyable", "Unforgiven", "Comboy à la retraite entraîné par son ancien co-équipier dans une mission périlleuse", "1999", "US"));
    }

    public ObservableList<JointureFilm> getMovieData() {
        return movieData;
    }

    public ObservableList<ListeCheckBox> getListeCheckBoxData() {
        return listeCheckboxData;
    }

    /**
     * Initialise la classe contrôleur.
     * après que la vue fxml ait été chargée.
     */
    @FXML
    public void initialize() {
        // Initialise la Tableview moivieTable avec 2 colonnes
        colTitreVF.setCellValueFactory(cellData -> cellData.getValue().titreFRProperty());
        colAnneeSortie.setCellValueFactory(cellData -> cellData.getValue().anneeSortieProperty());

        // Efface les détails de films
        showMovieJoinDetails(null);

        //Ecoute (Se tient prêt pour) le changement (en termes de sélection) à venir. Affiche les détails de l'acteur/réalisateur quand le changement est opéré (la sélection faite)
        movieTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showMovieJoinDetails(newValue));
        // Ajoute les données contenue dans lObservableList à la TableView
        movieTable.setItems(movieData);
    }

    /**
     * Est appelée par le controlleur de l'interface principale (InterfacePrincipaleController)
     * pour donner en retour une référence à lui-même
     *
     * @param jointureFilm
     */
    public void setMovie(InterfacePrincipaleController jointureFilm) {
        this.jointure = jointureFilm;
        //Ajoute les données de l'observable liste à la TableView
        movieTable.setItems(getMovieData());
    }

    /**
     * Remplie les labels de "Détails du film avec les information récupérées (getters) de l'objet
     * JointureFilms(construit à partir des données de la base de donnée via le patterDAO
     *
     * @param jointureFilm the movie or null
     */
    public void showMovieJoinDetails(JointureFilm jointureFilm) {
        if (jointureFilm != null) {
            // Remplie les labels avec les données issues de l'instance de jointureFilm la classe JointureFilm
            lblTitreVF.setText(jointureFilm.getTitreFR());
            lblTitreVO.setText(jointureFilm.getTitreO());
            lblScenario.setText(jointureFilm.getScenario());
            lblAnneeSortie.setText(jointureFilm.getAnneeSortie());
            lblNationalite.setText(jointureFilm.getNationalite());
            lblGenre.setText(jointureFilm.getGenre());
            lblRealisateur.setText(jointureFilm.getRealisateurs());
            lblActeurs.setText(jointureFilm.getActeurs());

        } else {
            // le film est "null", on affiche donc des chaînes de caractères vide ("")
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
     * Appellée quand l'utilisateur clique sur "+Film"
     * Ouvre la fenêtre de dialogue MovieEditController.
     */
    @FXML
    public void addNewMovieJoin() {
        Dao<JointureFilm> jointureFilmDao = new JointureFilmDAO(ConnectionClass.connecte());
        Dao<Correspond> correspondDao = new CorrespondDAO(ConnectionClass.connecte());
        JointureFilm tempJointureFilm = new JointureFilm();
        Correspond tempCorrespond = new Correspond();
        ListeCheckBox listeCheckBox = new ListeCheckBox();

        boolean okClicked = Main.showMovieJoinEditDialog(tempJointureFilm, listeCheckBox);

        if (okClicked) {
            jointureFilmDao.create(tempJointureFilm);

            getMovieData().add(tempJointureFilm);
            getListeCheckBoxData().add(listeCheckBox);

            int idFilm = jointureFilmDao.findIdMax();
            tempCorrespond.setIdFilm(idFilm);

            if (listeCheckBox.isCboxPolicier()) {
                tempCorrespond.setIdGenre(0);
                correspondDao.create(tempCorrespond);
            }
            if (listeCheckBox.isCboxThriller()) {
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Ajout d'un film");
            alert.setHeaderText("Le film a bien été ajouté");
            alert.showAndWait();
        }
    }

    /**
     * Appelée quand l'utilisateur clique Le bouton "Editer...".
     * Ouvre une fenêtre de dialogue pour éditer les informations du film sélectionné.
     */
    @FXML
    public void editMovieJoin() {
        JointureFilm selectedJoinMovie = movieTable.getSelectionModel().getSelectedItem();

        Dao<JointureFilm> jointureFilmDao = new JointureFilmDAO(ConnectionClass.connecte());
        Dao<Correspond> correspondDao = new CorrespondDAO(ConnectionClass.connecte());

        Correspond tempCorrespond = new Correspond();

        if (selectedJoinMovie != null) {
            String genre = "";
            int idFilm = selectedJoinMovie.getIdJointure();
            tempCorrespond.setIdFilm(idFilm);
            ListeCheckBox listegenreFromIdFilm = ((CorrespondDAO) correspondDao).extractListeCheckBoxFromJointure(selectedJoinMovie);

            ArrayList<Integer> listIntGenre = ((CorrespondDAO) correspondDao).extractIdListFromJointure(selectedJoinMovie);

            boolean okClicked = Main.showMovieJoinEditDialog(selectedJoinMovie, listegenreFromIdFilm);
            if (okClicked) {
                showMovieJoinDetails(selectedJoinMovie);

                if (listegenreFromIdFilm.isCboxPolicier() && !listIntGenre.contains(0)) {
                    tempCorrespond.setIdGenre(0);
                    correspondDao.update(tempCorrespond);
                    listIntGenre.add(0);

                }
                if (!listegenreFromIdFilm.isCboxPolicier() && listIntGenre.contains(0)) {
                    tempCorrespond.setIdGenre(0);
                    correspondDao.delete(tempCorrespond);
                    listIntGenre.remove(listIntGenre.indexOf(0));
                }
                if (listegenreFromIdFilm.isCboxThriller() && !listIntGenre.contains(1)) {
                    tempCorrespond.setIdGenre(1);
                    correspondDao.update(tempCorrespond);
                    listIntGenre.add(1);
                    listIntGenre.remove(listIntGenre.indexOf(1));
                }
                if (!listegenreFromIdFilm.isCboxThriller() && listIntGenre.contains(1)) {
                    tempCorrespond.setIdGenre(1);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxFantastqiqueSF() && !listIntGenre.contains(2)) {
                    tempCorrespond.setIdGenre(2);
                    correspondDao.update(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxFantastqiqueSF() && listIntGenre.contains(2)) {
                    tempCorrespond.setIdGenre(2);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxDrame() && !listIntGenre.contains(3)) {
                    tempCorrespond.setIdGenre(3);
                    correspondDao.update(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxDrame() && listIntGenre.contains(3)) {
                    tempCorrespond.setIdGenre(3);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxBiopic() && !listIntGenre.contains(4)) {
                    tempCorrespond.setIdGenre(4);
                    correspondDao.update(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxBiopic() && listIntGenre.contains(4)) {
                    tempCorrespond.setIdGenre(4);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxAction() && !listIntGenre.contains(5)) {
                    tempCorrespond.setIdGenre(5);
                    correspondDao.update(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxAction() && listIntGenre.contains(5)) {
                    tempCorrespond.setIdGenre(5);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxHorreur() && !listIntGenre.contains(6)) {
                    tempCorrespond.setIdGenre(6);
                    correspondDao.update(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxHorreur() && listIntGenre.contains(6)) {
                    tempCorrespond.setIdGenre(6);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxComedie() && !listIntGenre.contains(7)) {
                    tempCorrespond.setIdGenre(7);
                    correspondDao.update(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxComedie() && listIntGenre.contains(7)) {
                    tempCorrespond.setIdGenre(7);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxWestern() && !listIntGenre.contains(8)) {
                    tempCorrespond.setIdGenre(8);
                    correspondDao.update(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxWestern() && listIntGenre.contains(8)) {
                    tempCorrespond.setIdGenre(8);
                    correspondDao.delete(tempCorrespond);
                }
                if (listegenreFromIdFilm.isCboxAventure() && !listIntGenre.contains(9)) {
                    tempCorrespond.setIdGenre(9);
                    correspondDao.update(tempCorrespond);
                }
                if (!listegenreFromIdFilm.isCboxAventure() && listIntGenre.contains(9)) {
                    tempCorrespond.setIdGenre(9);
                    correspondDao.delete(tempCorrespond);
                }
                getMovieData().setAll(jointureFilmDao.findAll());
                jointureFilmDao.update(selectedJoinMovie);
            }
        } else { // Pas de film sélectionné.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Pas de sélection");
            alert.setHeaderText("Aucun film sélectionné.");
            alert.setContentText("Merci de sélectionner un film");
            alert.showAndWait();
        }
    }

    /**
     * Appelée quand l'utilisateur clique sur le boutton "Effacer..."
     */
    @FXML
    private void deleteMovieJoin() {
        JointureFilm selectedMovie = movieTable.getSelectionModel().getSelectedItem();
        Dao<JointureFilm> jointureDAO = new JointureFilmDAO(ConnectionClass.connecte());
        if (selectedMovie != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Suppression de film");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer le film sélectionné? ");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                jointureDAO.delete(selectedMovie);
            }
            movieData.remove(selectedMovie);
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
}
