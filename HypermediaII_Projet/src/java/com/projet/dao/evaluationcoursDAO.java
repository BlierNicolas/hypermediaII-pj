package com.projet.dao;

import com.projet.enties.evaluationcours;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class evaluationcoursDAO extends Dao<evaluationcours> {

    public evaluationcoursDAO(Connection cnx) {
        super(cnx);
    }

    @Override
    public boolean create(evaluationcours x) {
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement("INSERT INTO evaluationcours (`idLivre`, `idProf`, `idCours`, `note`, `commentaire`) VALUES (?,?,?,?,?)");
            stm.setString(1,x.getIdLivre());
            stm.setString(2,x.getIdProf());
            stm.setString(3,x.getIdCours());
            stm.setDouble(4,x.getNote());
            stm.setString(5,x.getCommentaire());
            
            if (stm.executeUpdate() > 0) {
                stm = cnx.prepareStatement("SELECT id FROM evaluationcours WHERE id = ?");
                stm.setInt(1,x.getId());
                ResultSet r = stm.executeQuery();
                if (r.next()) {
                    x.setId(r.getInt("id"));
                    stm.close();
                    return true;
                }
            }
        } catch (SQLException exp) {
			
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {                
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public evaluationcours read(String id) {
        PreparedStatement stm = null;
        //Statement stm = null;
        try {            
            stm = cnx.prepareStatement("SELECT * FROM evaluationcours WHERE id = ?");
            stm.setString(1, id);
            ResultSet r = stm.executeQuery();
            if (r.next()) {
                evaluationcours uneEvaluationcours = new evaluationcours(r.getString("idLivre"), r.getString("idProf"), r.getString("idCours"), r.getInt("note"), r.getString("commentaire"));
                uneEvaluationcours.setId(r.getInt("id"));
                r.close();
                stm.close();
                return uneEvaluationcours;
            }
        } catch (SQLException exp) {
			
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {            
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public boolean update(evaluationcours x) {
        Statement stm = null;
        try {
            stm = cnx.prepareStatement("UPDATE evaluationcours SET evaluationcours WHERE id = ?");
            String req =    "UPDATE evaluationcours SET id = '" + x.getId() + 
                            "', idLivre = '" + x.getIdLivre() + 
                            "', idProf = '" + x.getIdProf() +
                            "', idCours = '" + x.getIdCours() +
                            "', note = '" + x.getNote() +
                            "', commentaire = '" + x.getCommentaire() +
                            " WHERE id = " + x.getId() + "";
            stm = cnx.createStatement();
            int n = stm.executeUpdate(req);
            if (n > 0) {
                stm.close();
                return true;
            }
        } catch (SQLException exp) {
			
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {            
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean delete(evaluationcours x) {
        Statement stm = null;
        try {
            stm = cnx.createStatement();
            int n = stm.executeUpdate("DELETE FROM evaluationcours WHERE id = " + x.getId());
            if (n > 0) {
                stm.close();
                return true;
            }
        } catch (SQLException exp) {
        
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public List<evaluationcours> findAll() {
        List<evaluationcours> liste = new LinkedList<evaluationcours>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet r = stm.executeQuery("SELECT * FROM evaluationcours");
            while (r.next()) {
                evaluationcours uneEvaluationcours = new evaluationcours(r.getString("idLivre"), r.getString("idProf"), r.getString("idCours"), r.getInt("note"), r.getString("commentaire"));
                uneEvaluationcours.setId(r.getInt("id"));
                liste.add(uneEvaluationcours);
            }
            r.close();
            stm.close();
        } catch (SQLException exp) {
            
        }
        return liste;
    }
    
    public List<evaluationcours> findNoteDesc() {
        List<evaluationcours> liste = new LinkedList<evaluationcours>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet r = stm.executeQuery("SELECT * FROM evaluationCours ORDER BY note DESC");
            while (r.next()) {
                evaluationcours uneEvaluationcours = new evaluationcours(r.getString("idLivre"), r.getString("idProf"), r.getString("idCours"), r.getInt("note"), r.getString("commentaire"));
                uneEvaluationcours.setId(r.getInt("id"));
                liste.add(uneEvaluationcours);
            }
            r.close();
            stm.close();
        } catch (SQLException exp) {
            
        }
        return liste;
    }
}
