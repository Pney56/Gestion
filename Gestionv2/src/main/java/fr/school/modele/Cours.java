package fr.school.modele;

import java.util.ArrayList;
import java.util.List;

public class Cours {

    private String code;

    private String libelle;

    private List<Etudiant> listeEtudiants;

    //Constructeur
    public Cours(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
        this.listeEtudiants = new ArrayList<>();
    }

    public Cours() {

    }

    //Getters et Setters
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List getListeEtudiants() {
        return listeEtudiants;
    }

    public void setListeEtudiants(List listeEtudiants) {
        this.listeEtudiants = listeEtudiants;
    }

    //Méthode toString
    @Override
    public String toString() {
        return "Cours{" +
                "code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", listeEtudiants='" + listeEtudiants + '\'' +
                '}';
    }
}
