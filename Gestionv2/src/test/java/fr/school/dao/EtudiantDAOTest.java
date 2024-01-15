package fr.school.dao;


import fr.school.modele.Cours;
import fr.school.modele.Etudiant;
import fr.school.modele.Inscription;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EtudiantDAOTest {
    private EtudiantDAO etudiantDAO;

    @BeforeEach
    public void setUp() {
        etudiantDAO = new EtudiantDAOImpl();
        // Ici, vous pourriez insérer des données initiales si nécessaire
    }

    @AfterEach
    public void tearDown() {
        // Nettoyer les données insérées par les tests
        String sql = "DELETE FROM etudiant";
        // Exécutez la commande SQL pour nettoyer la base de données...
    }

    @Test
    public void testInsert() {
        Etudiant etudiant = new Etudiant(null, "Alice", "Example", LocalDate.of(2000, 1, 1), "Mathématiques");
        Long id = etudiantDAO.insert(etudiant); // Récupérer l'ID généré
        assertNotNull(id);
        System.out.println("ID: " + id);

        // Récupérez l'étudiant pour vérifier s'il a été correctement inséré
        Etudiant fetched = etudiantDAO.findById(id);
        assertNotNull(fetched);
        assertEquals("Alice", fetched.getNom()); // Vérifiez le nom de l'étudiant
    }


    @Test
    public void testFindById() {
        // Créez un nouvel étudiant
        Etudiant etudiant = new Etudiant(null, "Alice", "Example", LocalDate.of(2000, 1, 1), "Informatique");
        Long id = etudiantDAO.insert(etudiant); // Insérez l'étudiant et récupérez l'ID généré

        // Utilisez l'ID généré pour rechercher l'étudiant
        Etudiant fetched = etudiantDAO.findById(id);
        assertNotNull(fetched);
        assertEquals("Alice", fetched.getNom());
    }


    @Test
    public void testUpdate() {
        // Créez un nouvel étudiant et récupérez son ID généré
        Etudiant newEtudiant = new Etudiant(null, "Alice", "Smith", LocalDate.of(1998, 3, 10), "Physique");
        Long id = etudiantDAO.insert(newEtudiant);
        assertNotNull(id);

        // Utilisez l'ID généré pour récupérer l'étudiant et effectuer la mise à jour
        Etudiant fetchedEtudiant = etudiantDAO.findById(id);
        assertNotNull(fetchedEtudiant);
        assertEquals(id, fetchedEtudiant.getId());

        // Modifiez l'étudiant
        fetchedEtudiant.setPrenom("Bob");
        etudiantDAO.update(fetchedEtudiant);

        // Récupérez à nouveau l'étudiant pour vérifier la mise à jour
        Etudiant updatedEtudiant = etudiantDAO.findById(id);
        assertNotNull(updatedEtudiant);
        assertEquals("Bob", updatedEtudiant.getPrenom());
    }


    @Test
    public void testDelete() {
        // Supposons que l'ID 9 existe déjà dans la base de données
        Etudiant etudiant = new Etudiant(null, "John", "Doe", LocalDate.of(1995, 5, 15), "Mathématiques");
        Long id = etudiantDAO.insert(etudiant); // Récupérer l'ID généré

        etudiantDAO.delete(id);
        assertNull(etudiantDAO.findById(id));
    }

    @Test
    public void testFindAll() {
        List<Etudiant> etudiants = etudiantDAO.findAll();
        assertNotNull(etudiants);
        assertFalse(etudiants.isEmpty()); // Assurez-vous que la liste n'est pas vide
    }


}
