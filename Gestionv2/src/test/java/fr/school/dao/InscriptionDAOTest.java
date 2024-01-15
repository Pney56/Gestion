package fr.school.dao;

import fr.school.modele.Cours;
import fr.school.modele.Etudiant;
import fr.school.modele.Inscription;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InscriptionDAOTest {
    private InscriptionDAO inscriptionDAO;
    private EtudiantDAO etudiantDAO;
    private CoursDAO coursDAO;
    private List<Inscription> testInscriptions;


    @BeforeEach
    public void setUp() {
        inscriptionDAO = new InscriptionDAOImpl();
        etudiantDAO = new EtudiantDAOImpl();
        coursDAO = new CoursDAOImpl();
        testInscriptions = new ArrayList<>();


        // Créez 5 cours de test
        for (int i = 1; i <= 5; i++) {
            String courseCode = "COURSE" + i;
            if (!coursDAO.existsByCode(courseCode)) { // Vérifie si le cours existe déjà
                String courseLibelle = "Course Libelle " + i;
                Cours cours = new Cours(courseCode, courseLibelle);
                coursDAO.insert(cours);
            }
        }

        // Créez 5 étudiants de test une fois pour tous les tests avec des noms différents
        for (int i = 1; i <= 5; i++) {
            Etudiant etudiant = new Etudiant(null, "Nom" + i, "Prenom" + i, LocalDate.of(2000 + i, 1, 1), "Informatique");
            Long etudiantId = etudiantDAO.insert(etudiant);
            etudiant.setId(etudiantId);
        }
    }




    @AfterEach
    public void tearDown() {
        // Supprimez les inscriptions de test après chaque test
        for (Inscription inscription : testInscriptions) {
            if (inscription.getInscriptionId() != null) {
                inscriptionDAO.delete(inscription.getInscriptionId());
            }
        }
    }

    @Test
    public void testFindInscriptionsByStudentId() {
        // Créez un nouvel étudiant
        Etudiant nouvelEtudiant = new Etudiant(null, "NomTest", "PrenomTest", LocalDate.of(1990, 1, 1), "Informatique");
        Long nouvelEtudiantId = etudiantDAO.insert(nouvelEtudiant);
        nouvelEtudiant.setId(nouvelEtudiantId);

        // Vérifiez que l'insertion a réussi
        assertNotNull(nouvelEtudiantId);

        // Associez plusieurs cours à l'étudiant
        for (int i = 1; i <= 3; i++) {
            String courseCode = "COURSE" + i;
            Inscription inscription = new Inscription(null, nouvelEtudiant, coursDAO.findByCode(courseCode), LocalDate.now());
            inscriptionDAO.insert(inscription);
            testInscriptions.add(inscription);
        }

        // Appelez la méthode que vous souhaitez tester
        List<Inscription> inscriptions = inscriptionDAO.findInscriptionsByStudentId(nouvelEtudiantId);

        // Assurez-vous que la liste d'inscriptions n'est pas nulle et qu'elle contient les inscriptions attendues
        assertNotNull(inscriptions);
        assertEquals(3, inscriptions.size());
    }


    @Test
    public void testFindInscriptionsByCourseCode() {
        // Créez ou récupérez un étudiant
        Etudiant nouvelEtudiant = new Etudiant(null, "NomTest", "PrenomTest", LocalDate.of(1990, 1, 1), "Informatique");
        Long etudiantId = (etudiantDAO.findById(nouvelEtudiant.getId()) != null) ? nouvelEtudiant.getId() : etudiantDAO.insert(nouvelEtudiant);
        assertNotNull(etudiantId);

        // Liste des codes de cours
        List<String> courseCodes = Arrays.asList("COURSE1", "COURSE2", "COURSE3");

        for (String courseCode : courseCodes) {
            Cours cours = coursDAO.findByCode(courseCode);
            if (cours == null) {
                cours = new Cours(courseCode, "Libellé pour " + courseCode);
                coursDAO.insert(cours); // Supposons que cette méthode crée le cours s'il n'existe pas déjà
            }

            Inscription inscription = new Inscription(null, nouvelEtudiant, cours, LocalDate.now());
            inscriptionDAO.insert(inscription);
            testInscriptions.add(inscription);
        }

        List<Inscription> inscriptionsForCourse = inscriptionDAO.findInscriptionsByCourseCode("COURSE1");

        assertNotNull(inscriptionsForCourse);
        assertTrue(inscriptionsForCourse.size() > 0); // Vérifiez que la liste contient des inscriptions
    }




}
