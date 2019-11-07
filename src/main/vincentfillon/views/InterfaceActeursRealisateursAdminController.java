package vincentfillon.views;

import vincentfillon.Main;
import vincentfillon.connectivity.ConnectionClass;
import vincentfillon.dao.*;
import vincentfillon.model.ActeurRealisateur;
import vincentfillon.model.JointureActeursRealisateur;
import vincentfillon.model.JointureFilm;
import vincentfillon.model.Joue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;

public class InterfaceActeursRealisateursAdminController {

    @FXML
    private TableView<JointureActeursRealisateur> acteurRealisateurTable;
    @FXML
    private TableColumn<JointureActeursRealisateur, String> colPrenom;
    @FXML
    private TableColumn<JointureActeursRealisateur, String> colNom;

    @FXML
    private Label lblPrenom;
    @FXML
    private Label lblNom;
    @FXML
    private Label lblAnneeNaissance;
    @FXML
    private Label lblNationalite;
    @FXML
    private Label lblJoue;
    @FXML
    private Label lblRealise;


    private InterfacePrincipaleController reference;
    // Référence à l'interface principale Main
    private Main main;
    private JoueEditDialogController joueEditDialogController;

    @FXML
    private ObservableList<JointureActeursRealisateur> acteurRealisateursData = FXCollections.observableArrayList();

    public InterfaceActeursRealisateursAdminController() {
        Dao<JointureActeursRealisateur> jointureActeursRealisateurDAO = new JointureActeursRealisateurDAO(ConnectionClass.connecte());

// Pour trouver l'acteur d'indice i:
// ActeurRealisateur acteurRealisateur = acteurRealisateurDAO.find(1);
        acteurRealisateursData.setAll(jointureActeursRealisateurDAO.findAll());
// Ajout manuel pour test si besoin:
// movieJoinData.add(new JointureFilm("Les tontons flingueurs", "Les tontons flingueurs", "un ex-truand reconverti dans le négoce de matériel de travaux publics à Montauban voit sa petite vie tranquille basculer lorsque son ami d'enfance, Louis, dit le Mexicain, un gangster notoire de retour à Paris, l'appelle à son chevet.", "1963", "FR"));
    }

    public ObservableList<JointureActeursRealisateur> getActeurRealisateursData() {
        return acteurRealisateursData;
    }


    /**
     * Initialise la classe contrôleur.
     * Cette méthode est directement appelée après que la page fxml(view) ait été appelée.
     */
    @FXML
    public void initialize() {
        // Initialise le preview des acteurs/réalisateurs
        colPrenom.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        colNom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());

        // Efface les détails acteur/réalisateur.
        showActeurRealisateursDetails(null);

        //Ecoute (Se tient prêt pour) le changement (en termes de sélection) à venir. Affiche les détails de l'acteur/réalisateur quand le changement est opéré (la sélection faite)

        acteurRealisateurTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showActeurRealisateursDetails(newValue));
        // Ajoute les données contenue dans lObservableList à la TableView.

        acteurRealisateurTable.setItems(acteurRealisateursData);
    }

    /**
     Est appellée par l'application principale Main pour se donner une référence à elle-même
     *
     * @param reference
     */
    public void setActeurRealisateur(InterfacePrincipaleController reference) {
        this.reference = reference;
        //Ajoute les données contenue dans lObservableList à la TableView
        acteurRealisateurTable.setItems(getActeurRealisateursData());
    }

    /**
     * Remplie les champs de textes/Labels pour afficher les détails de l'acteur/réalisateur.
     * Si l'objet est null, alors on affiche des champs vides.
     *
     * @param jointureActeursRealisateur the movie or null
     */
    public void showActeurRealisateursDetails(JointureActeursRealisateur jointureActeursRealisateur) {

        if (jointureActeursRealisateur != null) {
            // Remplie les labels avec les données issues de l'instance de jointureActeursRealisateur la classe JointureActeurRealisateur
            lblPrenom.setText(jointureActeursRealisateur.getPrenom());
            lblNom.setText(jointureActeursRealisateur.getNom());
            lblAnneeNaissance.setText(jointureActeursRealisateur.getAnneeNaissance());
            lblNationalite.setText(jointureActeursRealisateur.getNationalite());
            lblJoue.setText(jointureActeursRealisateur.getJoue());
            lblRealise.setText(jointureActeursRealisateur.getRealise());

        } else {
            // L'acteur/réalisateur est "null", on définit comme vides (String "") tous les labels.
            lblPrenom.setText("");
            lblNom.setText("");
            lblAnneeNaissance.setText("");
            lblNationalite.setText("");
            lblJoue.setText("-");
            lblRealise.setText("-");
        }
    }


    /**
     * Appellée quand l'utilisateur appuie sur "+ Acteur/réalisateur"
     */
    @FXML
    public void addActeurRealisateur(ActionEvent actionEvent) {
        Dao<JointureActeursRealisateur> jointureActeursRealisateurDAO = new JointureActeursRealisateurDAO(ConnectionClass.connecte());
        JointureActeursRealisateur tempActeurRealisateur = new JointureActeursRealisateur();

        boolean okClicked = Main.showActeurRealisateurEditDialog(tempActeurRealisateur);

        if (okClicked) {
            getActeurRealisateursData().add(tempActeurRealisateur);
            jointureActeursRealisateurDAO.create(tempActeurRealisateur);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Ajout d'un acteur/réalisateur");
            alert.setHeaderText("L'acteur/réalisateur a bien été ajouté");
        }
    }

    /**
     * Appelée quand l'utilisateur clique sur le boutton "Effacer..."
     */
    @FXML
    private void deleteActeurRealisateur() {
        JointureActeursRealisateur selectedActeurRealisateur = acteurRealisateurTable.getSelectionModel().getSelectedItem();
        Dao<JointureActeursRealisateur> acteurRealisateurDAO = new JointureActeursRealisateurDAO(ConnectionClass.connecte());
        if (selectedActeurRealisateur != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Suppression de film");
            alert.setHeaderText("Êtes-vous sûr de vouloir supprimer le film sélectionné?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == ButtonType.OK) {
                acteurRealisateurDAO.delete(selectedActeurRealisateur);
            }
            acteurRealisateursData.remove(selectedActeurRealisateur);
        } else {
            // Pas d'élément sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Pas d'acteur/réalisateur.");
            alert.setContentText("Merci de sélectionner un acteur/réalisateur");
            alert.showAndWait();
        }
    }
    /**
     * Appelée quand l'utilisateur clique Le bouton "Editer...".
     * Ouvre une fenêtre de dialogue pour éditer les informations du film sélectionné.
     */
    @FXML
    public void editActeurRealisateur(ActionEvent actionEvent) {

        JointureActeursRealisateur selectedActeurRealisateur = acteurRealisateurTable.getSelectionModel().getSelectedItem();
        Dao<JointureActeursRealisateur> acteurRealisateurDAO = new JointureActeursRealisateurDAO(ConnectionClass.connecte());
        if (selectedActeurRealisateur != null) {
            boolean okClicked = Main.showActeurRealisateurEditDialog(selectedActeurRealisateur);
            if (okClicked) {
                showActeurRealisateursDetails(selectedActeurRealisateur);
                acteurRealisateurDAO.update(selectedActeurRealisateur);
            }

        } else {
            // Pas d'élément sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Pas d'acteur/réalisateur sélectionné");
            alert.setContentText("Merci de sélectionner un acteur/réalisateur");
            alert.showAndWait();
        }
    }

    public void initRealiseEditDialog(ActionEvent actionEvent) {
        /*Parent root;
        Stage newWindow = new Stage();
        try {
            root = FXMLLoader.load(getClass().getResource("JoueEditDialog.fxml"));
            newWindow.setScene(new Scene(root,850,642));
            newWindow.initModality(Modality.APPLICATION_MODAL);

            newWindow.show();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }


    public void addJoue() {
        JointureActeursRealisateur selectedActeur = acteurRealisateurTable.getSelectionModel().getSelectedItem();
        JointureFilm filmARecuperer = new JointureFilm();

        Dao<ActeurRealisateur> acteurRealisateurDAO = new ActeurRealisateurDAO(ConnectionClass.connecte());
        Dao<Joue> joueDao = new JoueDAO(ConnectionClass.connecte());
        Dao<JointureFilm> jointureFilmDao = new JointureFilmDAO(ConnectionClass.connecte());

        Joue tempJoue = new Joue();

        if (selectedActeur != null) {

            int idSelectedActeur = selectedActeur.getIdJointure();
            tempJoue.setIdActeursRealisateur(idSelectedActeur);
            boolean okClicked = Main.showJoueEditDialog(selectedActeur);
            if (okClicked) {
                int idFilmARecup = 0;
                try {
                    joueEditDialogController.getIdSelectedFilm();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //int idFilmARecup = filmARecuperer.getIdJointure();
                if (idFilmARecup != 0) {

                    tempJoue.setIdFilm(idFilmARecup);
                    joueDao.create(tempJoue);
                }
            }
        } else {
            // Pas d'élément sélectionné
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Pas d'acteur/réalisateur sélectionné");
            alert.setContentText("Merci de sélectionner un acteur/réalisateur");
            alert.showAndWait();
        }

    }

}
