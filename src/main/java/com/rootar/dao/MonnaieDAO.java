package com.rootar.dao;

import com.rootar.metier.Continent;
import com.rootar.metier.Monnaie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MonnaieDAO extends DAO <Monnaie, Monnaie>{
    private ResultSet rs;
    protected MonnaieDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Monnaie getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Monnaie> getAll() {
        ArrayList <Monnaie> liste= new ArrayList<>();
        String SQL= " select * from monnaie ";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column


            rs = pstmt.executeQuery();


            while (rs.next()) {

                liste.add(new Monnaie(rs.getInt(1),rs.getString(2)));
            }
            rs.close();

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public ArrayList<Monnaie> getLike(Monnaie objet) {
        return null;
    }

    @Override
    public boolean insert(Monnaie objet) {
        return false;
    }

    @Override
    public boolean update(Monnaie object) {
        return false;
    }

    @Override
    public boolean delete(Monnaie object) {
        return false;
    }
}
