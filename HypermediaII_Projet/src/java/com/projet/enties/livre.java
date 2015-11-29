package com.projet.enties;

public class livre {
    private String ISBN;
    private String titre;
    private String edition;
    private int annee;
    private String motcles;
    private String nomAuteur;
    private String etat;
    private String description;
    private int nbPages;
    private double note;
    private int nbEvaluations;
    
    public livre(String ISBN, String titre, String edition, int annee, String motcles, String nomAuteur, String etat, String description, int nbPages, double note, int nbEvaluations) {
        this.ISBN = ISBN;
        this.titre = titre;
        this.edition = edition;
        this.annee = annee;
        this.motcles = motcles;
        this.nomAuteur = nomAuteur;
        this.etat = etat;
        this.description = description;
        this.nbPages = nbPages;
        this.note = note;
        this.nbEvaluations = nbEvaluations;
    }
    
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }
    public String getISBN() {
        return ISBN;
    }
    public void setTitre(String titre) {
        this.titre = titre;
    }
    public String getTitre() {
        return titre;
    }
    public void setEdition(String edition) {
        this.edition = edition;
    }
    public String getEdition() {
        return edition;
    }
    public void setAnnee(int annee) {
        this.annee = annee;
    }
    public int getAnnee() {
        return annee;
    }
    public void setMotCles(String motcles) {
        this.motcles = motcles;
    }
    public String getMotCles() {
        return motcles;
    }
    public void setNomAuteur(String nomAuteur) {
        this.nomAuteur = nomAuteur;
    }
    public String getNomAuteur() {
        return nomAuteur;
    }
    public void setEtat(String etat) {
        this.etat = etat;
    }
    public String getEtat() {
        return etat;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public void setNbPages(int nbPages) {
        this.nbPages = nbPages;
    }
    public int getNbPages() {
        return nbPages;
    }
    public void setNote(double note) {
        this.note = note;
    }
    public double getNote() {
        return note;
    }
    public void setNbEvaluations(int nbEvaluations) {
        this.nbEvaluations = nbEvaluations;
    }
    public int getNbEvaluations() {
        return nbEvaluations;
    }
}
