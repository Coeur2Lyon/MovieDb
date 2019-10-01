package fr.vincentfillon.views;

import fr.vincentfillon.Main;
import fr.vincentfillon.connectivity.ConnectionClass;
import fr.vincentfillon.dao.*;
import fr.vincentfillon.model.ActeurRealisateur;
import fr.vincentfillon.model.JointureActeursRealisateur;
import fr.vincentfillon.model.JointureFilm;
import fr.vincentfillon.model.Joue;
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
    ;
    @FXML
    private Label lblNationalite;

    @FXML
    private Label lblJoue;

    @FXML
    private Label lblRealise;


    private InterfacePrincipaleController reference;
    // Reference to the main application.
    private Main main;
    private JoueEditDialogController joueEditDialogController;

    @FXML
    private ObservableList<JointureActeursRealisateur> acteurRealisateursData = FXCollections.observableArrayList();
/* The constructor is called before the initialize() method.
     */
    public InterfaceActeursRealisateursAdminController() {

        Dao<JointureActeursRealisateur> jointureActeursRealisateurDAO = new JointureActeursRealisateurDAO(ConnectionClass.connecte());

//Pour trouver l'acteur d'indice i:
        // ActeurRealisateur acteurRealisateur = acteurRealisateurDAO.find(1);
        acteurRealisateursData.setAll(jointureActeursRealisateurDAO.findAll());
        //acteurRealisateursData.add(new ActeurRealisateur("Villeret", "Jacques",  "1960", "FR"));
        //acteurRealisateursData.add(new ActeurRealisateur("Scorses", "Martin", "1962", "US"));
//        movieJoinData.add(new JointureFilm("L'armée des Ombres", "L'armée des Ombres", "Un ingénieur soupçonné de pensée gaullistes est arrêté par la Gestapo", "1969", "FR"));
//        movieJoinData.add(new JointureFilm("Les tontons flingueurs", "Les tontons flingueurs", "un ex-truand reconverti dans le négoce de matériel de travaux publics à Montauban voit sa petite vie tranquille basculer lorsque son ami d'enfance, Louis, dit le Mexicain, un gangster notoire de retour à Paris, l'appelle à son chevet.", "1963", "FR"));
    }

    public ObservableList<JointureActeursRealisateur> getActeurRealisateursData() {
        return acteurRealisateursData;
    }


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    public void initialize() {
        // Initialise le preview des acteurs/réalisateurs
        colPrenom.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        colNom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());

        // Clear movie details.
        showActeurRealisateursDetails(null);

        // Listen for selection changes and show the movie details when changed.
        acteurRealisateurTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showActeurRealisateursDetails(newValue));
        // Add observable list data to the table
        acteurRealisateurTable.setItems(acteurRealisateursData);
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param reference
     */
    public void setActeurRealisateur(InterfacePrincipaleController reference) {
        this.reference = reference;
        //Add observable list data to the table
        acteurRealisateurTable.setItems(getActeurRealisateursData());
    }

    /**
     * Fills all text fields to show details about the movie.
     * If the specified movie is null, all text fields are cleared.
     *
     * @param jointureActeursRealisateur the movie or null
     */
    public void showActeurRealisateursDetails(JointureActeursRealisateur jointureActeursRealisateur) {

        if (jointureActeursRealisateur != null) {
            // Fill the labels with info from the movie object.
            lblPrenom.setText(jointureActeursRealisateur.getPrenom());
            lblNom.setText(jointureActeursRealisateur.getNom());
            lblAnneeNaissance.setText(jointureActeursRealisateur.getAnneeNaissance());
            lblNationalite.setText(jointureActeursRealisateur.getNationalite());
            lblJoue.setText(jointureActeursRealisateur.getJoue());
            lblRealise.setText(jointureActeursRealisateur.getRealise());


        } else {
            // movie is null, remove all the text.
            lblPrenom.setText("");
            lblNom.setText("");
            lblAnneeNaissance.setText("");
            lblNationalite.setText("");
        }
    }


    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new movie.
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
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected movie.
     */


    /**
     * Called when the user clicks on the delete button.
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
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Pas d'acteur/réalisateur.");
            alert.setContentText("Merci de sélectionner un acteur/réalisateur");
            alert.showAndWait();
        }
    }

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
            // Nothing selected.
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
            System.out.println("La valeur de l'ID pris en compte (de manière normale et edffective actuellement) qui doit être la valeur de l'Id de l'acteur séléctionné à la base)  est:" + idSelectedActeur);
            tempJoue.setIdActeursRealisateur(idSelectedActeur);
            boolean okClicked = Main.showJoueEditDialog(selectedActeur);
            if (okClicked) {
                int idFilmARecup = 0;

                try{
                    joueEditDialogController.getIdSelectedFilm();
                }catch (Exception e){
                }
                //int idFilmARecup = filmARecuperer.getIdJointure();
                if (idFilmARecup != 0) {
                    //filmARecuperer=jointureFilmDao.find(idFilmARecup);
                    tempJoue.setIdFilm(idFilmARecup);
                    System.out.println("L'Id du film a récupérer doit être l'Id du film sélectionné: " + idFilmARecup);
                    // tempJoue.setIdFilm(6);


                    //selectedFilm=jointureFilmDao.find(idSelectedFilm);
                    //tempJoue.setIdFilm(idSelectedFilm);

                    //showActeurRealisateursDetails(selectedActeur);
                    joueDao.create(tempJoue);

                    // Nothing selected.
                    //Alert alert = new Alert(Alert.AlertType.WARNING);
                    //alert.initOwner(Main.getPrimaryStage());
                    //alert.setTitle("Aucune sélection");
                    // alert.setHeaderText("Pas de film sélectionné");
                    // alert.setContentText("Merci de sélectionner un film");
                    // alert.showAndWait();
                }
            }
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(Main.getPrimaryStage());
            alert.setTitle("Aucune sélection");
            alert.setHeaderText("Pas d'acteur/réalisateur sélectionné");
            alert.setContentText("Merci de sélectionner un acteur/réalisateur");
            alert.showAndWait();
        }

    }

}
