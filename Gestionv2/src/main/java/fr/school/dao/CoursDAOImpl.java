package fr.school.dao;

import fr.school.modele.Cours;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoursDAOImpl implements CoursDAO {

    @Override
    public void insert(Cours cours) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DatabaseUtil.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO cours (code, libelle) VALUES (?, ?)");
            preparedStatement.setString(1, cours.getCode());
            preparedStatement.setString(2, cours.getLibelle());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
    }

    @Override
    public Cours findByCode(String code) {
        String sql = "SELECT * FROM Cours WHERE code = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cours(rs.getString("code"), rs.getString("libelle"));
                }
            }
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cours> findAll() {
        List<Cours> list = new ArrayList<>();
        String sql = "SELECT * FROM Cours";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new Cours(rs.getString("code"), rs.getString("libelle")));
            }
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Cours cours) {
        String sql = "UPDATE Cours SET libelle = ? WHERE code = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, cours.getLibelle());
            stmt.setString(2, cours.getCode());
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String code) {
        String sql = "DELETE FROM Cours WHERE code = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Gestion de l'exception
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsByCode(String code) {
        String sql = "SELECT COUNT(*) FROM Cours WHERE code = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, code);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

