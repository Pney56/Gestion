package fr.school.dao;

import fr.school.modele.Cours;
import fr.school.modele.Etudiant;
import fr.school.modele.Inscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantDAOImpl implements EtudiantDAO {
    @Override
    public Long insert(Etudiant etudiant) {
        String sql = "INSERT INTO Etudiants (nom, prenom, date_naissance, classe) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setDate(3, java.sql.Date.valueOf(etudiant.getDateNaissance()));
            stmt.setString(4, etudiant.getClasse());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Création de l'étudiant a échoué, aucune ligne affectée.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getLong(1); // Récupère l'ID généré
                } else {
                    throw new SQLException("Création de l'étudiant a échoué, aucun ID obtenu.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Retourne null en cas d'exception
        }
    }


    @Override
    public Etudiant findById(Long id) {
        String sql = "SELECT * FROM Etudiants WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Etudiant(
                            id, // Utilisez l'ID passé en paramètre au lieu de celui de la base de données
                            rs.getString("nom"),
                            rs.getString("prenom"),
                            rs.getDate("date_naissance").toLocalDate(),
                            rs.getString("classe")
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
    public List<Etudiant> findAll() {
        List<Etudiant> list = new ArrayList<>();
        String sql = "SELECT * FROM Etudiants";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Etudiant(
                        rs.getLong("id"),
                        rs.getString("nom"),
                        rs.getString("prenom"),
                        rs.getDate("date_naissance").toLocalDate(),
                        rs.getString("classe")
                ));
            }
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Etudiant etudiant) {
        String sql = "UPDATE Etudiants SET nom = ?, prenom = ?, date_naissance = ?, classe = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, etudiant.getNom());
            stmt.setString(2, etudiant.getPrenom());
            stmt.setDate(3, java.sql.Date.valueOf(etudiant.getDateNaissance()));
            stmt.setString(4, etudiant.getClasse());
            stmt.setLong(5, etudiant.getId());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Mise à jour de l'étudiant a échoué, aucune ligne affectée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Etudiants WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Suppression de l'étudiant a échoué, aucune ligne affectée.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





}
