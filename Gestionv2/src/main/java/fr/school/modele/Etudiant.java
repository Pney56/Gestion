package fr.school.modele;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Locale;

public class Etudiant {

    private Long id;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String classe;

    //Constructeur
    public Etudiant(Long id, String nom, String prenom, LocalDate dateNaissance, String classe) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.classe = classe;
    }

    public Etudiant() {

    }

    //Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    // Méthode ToString
    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", classe='" + classe + '\'' +
                '}';
    }
}
