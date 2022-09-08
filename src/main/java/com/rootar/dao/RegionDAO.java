package com.rootar.dao;

import com.rootar.metier.Continent;
import com.rootar.metier.Monnaie;
import com.rootar.metier.Pays;
import com.rootar.metier.Region;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RegionDAO extends DAO <Region, Region>{
    private ResultSet rs;
    protected RegionDAO(Connection connexion) {
        super(connexion);
    }

    public ArrayList<Region> getRegionByPays(Pays pays) {

        ArrayList<Region> liste = new ArrayList<>();
        String SQL= " select * from Region where id_pays =?";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,pays.getIdPays());
            rs = pstmt.executeQuery();


            while (rs.next()) {

                liste.add(new Region(rs.getInt(1),rs.getString(2)));
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
    public Region getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Region> getAll() {
        return null;
    }

    @Override
    public ArrayList<Region> getLike(Region objet) {
        return null;
    }

    @Override
    public boolean insert(Region objet) {
        return false;
    }

    @Override
    public boolean update(Region object) {
        return false;
    }

    @Override
    public boolean delete(Region object) {
        return false;
    }
}
