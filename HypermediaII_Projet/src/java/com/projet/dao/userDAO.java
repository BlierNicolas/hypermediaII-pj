package com.projet.dao;

import com.projet.enties.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class userDAO extends Dao<user> {

    public userDAO(Connection cnx) {
        super(cnx);
    }

    @Override
    public boolean create(user x) {
        PreparedStatement stm = null;
        try {
            stm = cnx.prepareStatement("INSERT INTO user (`username`, `nom_prenom`, `password`) VALUES (?,?,?)");
            stm.setString(1,x.getUsername());
            stm.setString(2,x.getNomPrenom());
            stm.setString(3,x.getPassword());
            
            if (stm.executeUpdate() > 0) {
                stm = cnx.prepareStatement("SELECT username FROM user WHERE username = ?");
                stm.setString(1,x.getUsername());
                ResultSet r = stm.executeQuery();
                if (r.next()) {
                    x.setUsername(r.getString("username"));
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
    public user read(String username) {
        PreparedStatement stm = null;
        //Statement stm = null;
        try {            
            stm = cnx.prepareStatement("SELECT * FROM user WHERE username = ?");
            stm.setString(1, username);
            ResultSet r = stm.executeQuery();
            if (r.next()) {
                user unUser = new user(r.getString("username"), r.getString("nomPrenom"), r.getString("password"));
                r.close();
                stm.close();
                return unUser;
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
    public boolean update(user x) {
        Statement stm = null;
        try {
            stm = cnx.prepareStatement("UPDATE user SET user WHERE username = ?");
            String req =    "UPDATE user SET username = '" + x.getUsername() + 
                            "', nom_prenom = '" + x.getNomPrenom() + 
                            "', password = '" + x.getPassword() +
                            " WHERE username = " + x.getUsername() + "";
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
    public boolean delete(user x) {
        Statement stm = null;
        try {
            stm = cnx.createStatement();
            int n = stm.executeUpdate("DELETE FROM user WHERE username = " + x.getUsername());
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
    public List<user> findAll() {
        List<user> liste = new LinkedList<user>();
        try {
            Statement stm = cnx.createStatement();
            ResultSet r = stm.executeQuery("SELECT * FROM user");
            while (r.next()) {
                user unUser = new user(r.getString("username"), r.getString("nom_prenom"), r.getString("password"));
                liste.add(unUser);
            }
            r.close();
            stm.close();
        } catch (SQLException exp) {
            
        }
        return liste;
    }
}