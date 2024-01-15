package fr.school.dao;

import fr.school.modele.Inscription;

import java.util.List;

public interface InscriptionDAO {
    void insert(Inscription inscription);
    Inscription findById(Long inscriptionId);
    List<Inscription> findAll();
    void update(Inscription inscription);
    void delete(Long inscriptionId);

    List<Inscription> findInscriptionsByCourseCode(String courseCode);

    List<Inscription> findInscriptionsByStudentId(Long studentId);
}
