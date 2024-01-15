package fr.school.modele;

import fr.school.modele.Cours;
import fr.school.modele.Etudiant;

import java.time.LocalDate;

public class Inscription {

    private Long inscriptionId;
    private Etudiant etudiant;
    private Cours cours;
    private LocalDate dateInscription;

    //Constructeur
    public Inscription(Long inscriptionId, Etudiant etudiant, Cours cours, LocalDate dateInscription) {
        this.inscriptionId = inscriptionId;
        this.etudiant = etudiant;
        this.cours = cours;
        this.dateInscription = dateInscription;
    }

    // Getters et Setters
    public Long getInscriptionId() {
        return inscriptionId;
    }

    public void setInscriptionId(Long inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public LocalDate getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(LocalDate dateInscription) {
        this.dateInscription = dateInscription;
    }

    // Méthode toString
    @Override
    public String toString() {
        return "Inscription{" +
                "inscriptionId=" + inscriptionId +
                ", etudiant=" + etudiant +
                ", cours=" + cours +
                ", dateInscription=" + dateInscription +
                '}';
    }
}
