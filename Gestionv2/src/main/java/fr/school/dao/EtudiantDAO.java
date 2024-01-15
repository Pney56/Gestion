package fr.school.dao;

import fr.school.modele.Etudiant;
import fr.school.modele.Inscription;

import java.util.List;

public interface EtudiantDAO {
    Long insert(Etudiant etudiant);
    void update(Etudiant etudiant);
    void delete(Long id);
    Etudiant findById(Long id);
    List<Etudiant> findAll();


}
