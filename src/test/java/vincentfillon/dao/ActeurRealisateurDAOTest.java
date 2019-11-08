package vincentfillon.dao;

import org.junit.Test;
import vincentfillon.model.ActeurRealisateur;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ActeurRealisateurDAOTest  {

    @Test
    public void should_create_actor() throws SQLException {
        Connection mockedConnection = mock(Connection.class);
        Statement statement = mock(Statement.class);
        ActeurRealisateurDAO acteurRealisateurDAO = new ActeurRealisateurDAO(mockedConnection);

        when(mockedConnection.createStatement()).thenReturn(statement);

        ActeurRealisateur acteurRealisateur = new ActeurRealisateur();
        acteurRealisateur.setPrenom("Jim");
        acteurRealisateur.setNom("Carrey");
        acteurRealisateur.setAnneeNaissance("1962");
        acteurRealisateur.setNationalite("Canada");

        acteurRealisateurDAO.create(acteurRealisateur);

        String expectedInsertQuery = "INSERT INTO moviedb.ACTEUR_REALISATEUR (Nom, Prenom, AnneeNaissance, NationaliteAR) VALUES('Carrey','Jim','1962', 'Canada')";
        verify(statement).executeUpdate(expectedInsertQuery);
    }

}
