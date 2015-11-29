package com.projet.enties;

public class cours {
    private String numero;
    private String nom;
    private int duree;
    
    public cours(String numero, String nom, int duree) {
        this.numero = numero;
        this.nom = nom;
        this.duree = duree;
    }
    
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getNumero() {
        return this.numero;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getNom() {
        return this.nom;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public int getDuree() {
        return this.duree;
    }
}
