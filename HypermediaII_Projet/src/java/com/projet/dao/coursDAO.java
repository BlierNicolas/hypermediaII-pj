package com.projet.dao;

import com.projet.enties.cours;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class coursDAO extends Dao<cours> {

    public coursDAO(Connection cnx) {
        super(cnx);
    }

    @Override
    public boolean create(cours x) {
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement("INSERT INTO cours (`numero`, `nom`, `duree`) VALUES (?,?,?)");
            stm.setString(1,x.getNumero());
            stm.setString(2,x.getNom());
            stm.setInt(3,x.getDuree());
            
            if (stm.executeUpdate() > 0) {
                stm = cnx.prepareStatement("SELECT numero FROM cours WHERE numero = ?");
                stm.setString(1,x.getNumero());
                ResultSet r = stm.executeQuery();
                if (r.next()) {
                    x.setNumero(r.getString("numero"));
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
    public cours read(String numero) {
        PreparedStatement stm = null;
        //Statement stm = null;
        try {            
            stm = cnx.prepareStatement("SELECT * FROM cours WHERE numero = ?");
            stm.setString(1, numero);
            ResultSet r = stm.executeQuery();
            if (r.next()) {
                cours unCours = new cours(r.getString("numero"), r.getString("nom"), r.getInt("duree"));
                r.close();
                stm.close();
                return unCours;
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
    public boolean update(cours x) {
        Statement stm = null;
        try {
            stm = cnx.prepareStatement("UPDATE cours SET cours WHERE numero = ?");
            String req =    "UPDATE cours SET numero = '" + x.getNumero() + 
                            "', nom = '" + x.getNom() + 
                            "', duree = '" + x.getDuree() +
                            " WHERE numero = " + x.getNumero() + "";
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
    public boolean delete(cours x) {
        Statement stm = null;
        try {
            stm = cnx.createStatement();
            int n = stm.executeUpdate("DELETE FROM cours WHERE numeor = " + x.getNumero());
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
    public List<cours> findAll() {
        List<cours> liste = new LinkedList<cours>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet r = stm.executeQuery("SELECT * FROM cours");
            while (r.next()) {
                cours unCours = new cours(r.getString("numero"), r.getString("nom"), r.getInt("duree"));
                liste.add(unCours);
            }
            r.close();
            stm.close();
        } catch (SQLException exp) {
            
        }
        return liste;
    }
    
}
