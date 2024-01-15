package fr.school.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtil {
    // Paramètres de connexion à la base de données MySQL
    private static final String URL = "jdbc:mysql://localhost:3306/gestion";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";



    static {
        try {
            // Assurez-vous que le driver JDBC MySQL est chargé
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Le driver JDBC MySQL n'a pas pu être chargé.", e);
        }
    }

    // Méthode pour établir la connexion à la base de données
    public static Connection getConnection() {
        try {
            // Retourne la connexion
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            // Affichage de l'erreur SQL dans la console
            System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        try {
            // Fermeture de la connexion
            connection.close();
        } catch (SQLException e) {
            // Affichage de l'erreur SQL dans la console
            System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
        }
    }

}
