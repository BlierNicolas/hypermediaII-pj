package com.projet.dao;

import com.projet.enties.evaluation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class evaluationDAO extends Dao<evaluation> {

    public evaluationDAO(Connection cnx) {
        super(cnx);
    }

    @Override
    public boolean create(evaluation x) {
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement("INSERT INTO evaluation (`idProf`, `idLivre`, `note`, `commentaire`) VALUES (?,?,?,?)");
            stm.setString(1,x.getIdProf());
            stm.setString(2,x.getIdLivre());
            stm.setDouble(3,x.getNote());
            stm.setString(4,x.getCommentaire());
            
            if (stm.executeUpdate() > 0) {
                stm = cnx.prepareStatement("SELECT id FROM evaluation WHERE id = ?");
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
    public evaluation read(String id) {
        PreparedStatement stm = null;
        //Statement stm = null;
        try {            
            stm = cnx.prepareStatement("SELECT * FROM evaluation WHERE id = ?");
            stm.setString(1, id);
            ResultSet r = stm.executeQuery();
            if (r.next()) {
                evaluation uneEvaluation = new evaluation(r.getString("idProf"), r.getString("idLivre"), r.getInt("note"), r.getString("commentaire"));
                uneEvaluation.setId(r.getInt("id"));
                r.close();
                stm.close();
                return uneEvaluation;
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
    public boolean update(evaluation x) {
        Statement stm = null;
        try {
            stm = cnx.prepareStatement("UPDATE evaluation SET evaluation WHERE id = ?");
            String req =    "UPDATE evaluation SET id = '" + x.getId() + 
                            "', idProf = '" + x.getIdProf() + 
                            "', idLivre = '" + x.getIdLivre() +
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
    public boolean delete(evaluation x) {
        Statement stm = null;
        try {
            stm = cnx.createStatement();
            int n = stm.executeUpdate("DELETE FROM evaluation WHERE id = " + x.getId());
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
    public List<evaluation> findAll() {
        List<evaluation> liste = new LinkedList<evaluation>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet r = stm.executeQuery("SELECT * FROM user");
            while (r.next()) {
                evaluation uneEvaluation = new evaluation(r.getString("idProf"), r.getString("idLivre"), r.getInt("note"), r.getString("commentaire"));
                uneEvaluation.setId(r.getInt("id"));
                liste.add(uneEvaluation);
            }
            r.close();
            stm.close();
        } catch (SQLException exp) {
            
        }
        return liste;
    }
    
}
