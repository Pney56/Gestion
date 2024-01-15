package fr.school.dao;

import fr.school.dao.DatabaseUtil;
import fr.school.modele.Cours;
import fr.school.modele.Etudiant;
import fr.school.modele.Inscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InscriptionDAOImpl implements InscriptionDAO {

    @Override
    public void insert(Inscription inscription) {
        String sql = "INSERT INTO Inscription (etudiant_id, cours_code, date_inscription) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            Etudiant etudiant = inscription.getEtudiant();
            if (etudiant != null) {
                stmt.setLong(1, etudiant.getId());
            } else {
                // Gérer le cas où etudiant est null en lançant une exception
                throw new IllegalArgumentException("L'étudiant de l'inscription ne peut pas être null");
            }
            stmt.setString(2, inscription.getCours().getCode());
            stmt.setDate(3, java.sql.Date.valueOf(inscription.getDateInscription()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
    }





    @Override
    public Inscription findById(Long inscriptionId) {
        String sql = "SELECT * FROM Inscription i " +
                "JOIN Etudiants e ON i.etudiant_id = e.id " +
                "JOIN Cours c ON i.cours_code = c.code " +
                "WHERE i.inscription_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, inscriptionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Etudiant etudiant = new Etudiant(
                            rs.getLong("e.id"),
                            rs.getString("e.nom"),
                            rs.getString("e.prenom"),
                            rs.getDate("e.date_naissance").toLocalDate(),
                            rs.getString("e.classe")
                    );
                    Cours cours = new Cours(
                            rs.getString("c.code"),
                            rs.getString("c.libelle")
                    );
                    return new Inscription(
                            rs.getLong("i.inscription_id"),
                            etudiant,
                            cours,
                            rs.getDate("i.date_inscription").toLocalDate()
                    );
                }
            }
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Inscription> findAll() {
        List<Inscription> list = new ArrayList<>();
        String sql = "SELECT * FROM Inscription i " +
                "JOIN Etudiants e ON i.etudiant_id = e.id " +
                "JOIN Cours c ON i.cours_code = c.code";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Etudiant etudiant = new Etudiant(
                        rs.getLong("e.id"),
                        rs.getString("e.nom"),
                        rs.getString("e.prenom"),
                        rs.getDate("e.date_naissance").toLocalDate(),
                        rs.getString("e.classe")
                );
                Cours cours = new Cours(
                        rs.getString("c.code"),
                        rs.getString("c.libelle")
                );
                list.add(new Inscription(
                        rs.getLong("i.inscription_id"),
                        etudiant,
                        cours,
                        rs.getDate("i.date_inscription").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Inscription inscription) {
        String sql = "UPDATE Inscription SET etudiant_id = ?, cours_code = ?, date_inscription = ? WHERE inscription_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, inscription.getEtudiant().getId());
            stmt.setString(2, inscription.getCours().getCode());
            stmt.setDate(3, java.sql.Date.valueOf(inscription.getDateInscription()));
            stmt.setLong(4, inscription.getInscriptionId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long inscriptionId) {
        String sql = "DELETE FROM Inscription WHERE inscription_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, inscriptionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
    }


    @Override
    public List<Inscription> findInscriptionsByCourseCode(String courseCode) {
        List<Inscription> list = new ArrayList<>();
        String sql = "SELECT * FROM Inscription i " +
                "JOIN Etudiants e ON i.etudiant_id = e.id " +
                "JOIN Cours c ON i.cours_code = c.code " +
                "WHERE c.code = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseCode);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Etudiant etudiant = new Etudiant(
                            rs.getLong("e.id"),
                            rs.getString("e.nom"),
                            rs.getString("e.prenom"),
                            rs.getDate("e.date_naissance").toLocalDate(),
                            rs.getString("e.classe")
                    );
                    Cours cours = new Cours(
                            rs.getString("c.code"),
                            rs.getString("c.libelle")
                    );
                    list.add(new Inscription(
                            rs.getLong("i.inscription_id"),
                            etudiant,
                            cours,
                            rs.getDate("i.date_inscription").toLocalDate()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Inscription> findInscriptionsByStudentId(Long studentId) {
        List<Inscription> list = new ArrayList<>();
        String sql = "SELECT * FROM Inscription i " +
                "JOIN Etudiants e ON i.etudiant_id = e.id " +
                "JOIN Cours c ON i.cours_code = c.code " +
                "WHERE e.id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Etudiant etudiant = new Etudiant(
                            rs.getLong("e.id"),
                            rs.getString("e.nom"),
                            rs.getString("e.prenom"),
                            rs.getDate("e.date_naissance").toLocalDate(),
                            rs.getString("e.classe")
                    );
                    Cours cours = new Cours(
                            rs.getString("c.code"),
                            rs.getString("c.libelle")
                    );
                    list.add(new Inscription(
                            rs.getLong("i.inscription_id"),
                            etudiant,
                            cours,
                            rs.getDate("i.date_inscription").toLocalDate()
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }



}
