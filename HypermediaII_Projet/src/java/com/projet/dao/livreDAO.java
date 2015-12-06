package com.projet.dao;

import com.projet.enties.livre;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class livreDAO extends Dao<livre> {

    public livreDAO(Connection cnx) {
        super(cnx);
    }

    @Override
    public boolean create(livre x) {
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement("INSERT INTO livre (`ISBN`, `titre`, `edition`, `annee`, `motcles`, `nomAuteur`, `etat`, `description`, `nbPages`, `note`, `nbEvaluations`) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            stm.setString(1,x.getISBN());
            stm.setString(2,x.getTitre());
            stm.setString(3,x.getEdition());
            stm.setInt(4, x.getAnnee());
            stm.setString(5, x.getMotCles());
            stm.setString(6, x.getNomAuteur());
            stm.setString(7, x.getEtat());
            stm.setString(8, x.getDescription());
            stm.setInt(9, x.getNbPages());
            stm.setDouble(10, x.getNote());
            stm.setInt(11, x.getNbEvaluations());
            
            if (stm.executeUpdate() > 0) {
                stm = cnx.prepareStatement("SELECT ISBN FROM llivre WHERE ISBN = ?");
                stm.setString(1,x.getISBN());
                ResultSet r = stm.executeQuery();
                if (r.next()) {
                    x.setISBN(r.getString("ISBN"));
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
    public livre read(String ISBN) {
        PreparedStatement stm = null;
        //Statement stm = null;
        try {            
            stm = cnx.prepareStatement("SELECT * FROM livre WHERE ISBN = ?");
            stm.setString(1, ISBN);
            ResultSet r = stm.executeQuery();
            if (r.next()) {
                livre unLivre = new livre(r.getString("ISBN"), r.getString("Titre"), r.getString("Edition"), r.getInt("Annee"), r.getString("MotsCles"), r.getString("NomAuteur"), r.getString("etat"), r.getString("Description"), r.getInt("NbPages"), r.getDouble("note"), r.getInt("nbEvaluations"));
                r.close();
                stm.close();
                return unLivre;
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
    public boolean update(livre x) {
        Statement stm = null;
        try {
            stm = cnx.prepareStatement("UPDATE livre SET livre WHERE ISBN = ?");
            String req =    "UPDATE livre SET ISBN = '" + x.getISBN() + 
                            "', Titre = '" + x.getTitre() + 
                            "', Edition = '" + x.getEdition() +
                            "', Annee = '" + x.getAnnee() +
                            "', MotCles = '" + x.getMotCles() +
                            "', NomAuteur = '" + x.getNomAuteur() +
                            "', etat = '" + x.getEtat() +
                            "', Description = '" + x.getDescription() +
                            "', NbPages = '" + x.getNbPages() +
                            "', note = '" + x.getNote() +
                            "', nbEvaluations = '" + x.getNbEvaluations() +
                            " WHERE ISBN = " + x.getISBN() + "";
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
    public boolean delete(livre x) {
        Statement stm = null;
        try {
            stm = cnx.createStatement();
            int n = stm.executeUpdate("DELETE FROM livre WHERE ISBN = " + x.getISBN());
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
    public List<livre> findAll() {
        List<livre> liste = new LinkedList<livre>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet r = stm.executeQuery("SELECT * FROM livre");
            while (r.next()) {
                livre unLivre = new livre(r.getString("ISBN"), r.getString("Titre"), r.getString("Edition"), r.getInt("Annee"), r.getString("MotsCles"), r.getString("NomAuteur"), r.getString("etat"), r.getString("Description"), r.getInt("NbPages"), r.getDouble("note"), r.getInt("nbEvaluations"));
                liste.add(unLivre);
            }
            r.close();
            stm.close();
        } catch (SQLException exp) {
            
        }
        return liste;
    }
}
