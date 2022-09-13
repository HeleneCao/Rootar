package com.rootar.dao;

import com.rootar.metier.Continent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ContinentDAO extends DAO <Continent, Continent>{
    private ResultSet rs;
    protected ContinentDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Continent getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Continent> getAll() {
       ArrayList <Continent> continent= new ArrayList<>();
        String SQL= " select * from continent ";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column


            rs = pstmt.executeQuery();


            while (rs.next()) {

                continent.add(new Continent(rs.getInt(1),rs.getString(2)));
            }
            rs.close();

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        return continent;
    }

    @Override
    public ArrayList<Continent> getLike(Continent objet) {
        return null;
    }

    @Override
    public boolean insert(Continent objet) {
        return false;
    }

    @Override
    public boolean update(Continent object) {
        return false;
    }

    @Override
    public boolean delete(Continent object) {
        return false;
    }
}
