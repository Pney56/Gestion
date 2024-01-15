package fr.school.dao;

import fr.school.modele.Cours;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class CoursDAOTest {

    private CoursDAO coursDAO;

    @BeforeEach
    void setUp() {
        // Initialisation des ressources.
        coursDAO = new CoursDAOImpl();
    }

    @AfterEach
    public void tearDown() {
        // Nettoyer les ressources.
//        String sql = "DELETE FROM cours";
//        try (Connection conn = DatabaseUtil.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            // Gestion de l'exception
//            System.err.println("Erreur lors de la suppression des données de la table 'cours': " + e.getMessage());
//        }
    }

    @Test
    void testInsert() {
        Cours cours = new Cours("MATH101", "Mathématiques de base");
        coursDAO.insert(cours);

        // Récupérez le cours pour vérifier s'il a été correctement inséré
        Cours fetched = coursDAO.findByCode("MATH101");
        assertNotNull(fetched);
        assertEquals(cours.getCode(), fetched.getCode());
        assertEquals(cours.getLibelle(), fetched.getLibelle());
    }

    @Test
    void testFindByCode() {
        Cours cours = new Cours("MATH102", "Algèbre");
        coursDAO.insert(cours);

        Cours fetched = coursDAO.findByCode("MATH102");
        assertNotNull(fetched);
        assertEquals("MATH102", fetched.getCode());
    }

    @Test
    void testFindAll() {

        //insertion d'un cours de test
        coursDAO.insert(new Cours("MATH100", "Mathématiques de base"));

        List<Cours> coursList = coursDAO.findAll();
        assertNotNull(coursList);
        assertFalse(coursList.isEmpty()); // Vérifie que la liste n'est pas vide
    }

    @Test
    void testUpdate() {
        Cours cours = new Cours("MATH103", "Géométrie");
        coursDAO.insert(cours);

        cours.setLibelle("Géométrie avancée");
        coursDAO.update(cours);

        Cours updatedCours = coursDAO.findByCode("MATH103");
        assertNotNull(updatedCours);
        assertEquals("Géométrie avancée", updatedCours.getLibelle());
    }

    @Test
    void testDelete() {
        Cours cours = new Cours("MATH104", "Statistiques");
        coursDAO.insert(cours);

        coursDAO.delete("MATH104");
        Cours deletedCours = coursDAO.findByCode("MATH104");
        assertNull(deletedCours);
    }
}
