package com.projet.enties;

public class user {
    private String username;
    private String nom_prenom;
    private String password;
    
    public user(String username, String nom_prenom, String password) {
        this.username = username;
        this.nom_prenom = nom_prenom;
        this.password = password;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getUsername() {
        return username;
    }
    public void setNomPrenom(String nom_prenom) {
        this.nom_prenom = nom_prenom;
    }
    public String getNomPrenom() {
        return nom_prenom;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
}
