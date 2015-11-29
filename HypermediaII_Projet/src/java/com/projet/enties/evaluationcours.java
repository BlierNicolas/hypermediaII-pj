package com.projet.enties;

public class evaluationcours {
    private int id;
    private String idLivre;
    private String idProf;
    private String idCours;
    private int note;
    private String commentaire;
    
    public evaluationcours(String idLivre, String idProf, String idCours, int note, String commentaire) {
        this.idLivre = idLivre;
        this.idProf = idProf;
        this.idCours = idCours;
        this.note = note;
        this.commentaire = commentaire;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setIdLivre(String idLivre) {
        this.idLivre = idLivre;
    }
    public String getIdLivre() {
        return idLivre;
    }
    public void setIdProf(String idProf) {
        this.idProf = idProf;
    }
    public String getIdProf() {
        return idProf;
    }
    public void setIdCours(String idCours) {
        this.idCours = idCours;
    }
    public String getIdCours() {
        return idCours;
    }
    public void setNote(int note) {
        this.note = note;
    }
    public int getNote() {
        return note;
    }
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    public String getCommentaire() {
        return commentaire;
    }
}
