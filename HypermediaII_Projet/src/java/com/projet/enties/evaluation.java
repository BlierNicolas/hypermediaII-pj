package com.projet.enties;

public class evaluation {
    private int id;
    private String idProf;
    private String idLivre;
    private int note;
    private String commentaire;
    
    public evaluation(String idProf, String idLivre, int note, String commentaire) {
        this.idProf = idProf;
        this.idLivre = idLivre;
        this.note = note;
        this.commentaire = commentaire;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setIdProf(String idProf) {
        this.idProf = idProf;
    }
    public String getIdProf() {
        return idProf;
    }
    public void setIdLivre(String idLivre) {
        this.idLivre = idLivre;
    }
    public String getIdLivre() {
        return idLivre;
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
