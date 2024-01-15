package fr.school.dao;

import fr.school.modele.Cours;

import java.util.List;

public interface CoursDAO {
    void insert(Cours cours);
    Cours findByCode(String code);
    List<Cours> findAll();
    void update(Cours cours);
    void delete(String code);

    boolean existsByCode(String code);
}
