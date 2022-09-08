package com.rootar.dao;

import com.rootar.metier.DonneesClimat;
import com.rootar.metier.Pays;
import com.rootar.metier.Region;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DonneesClimatDAO extends DAO <DonneesClimat,DonneesClimat>{
    private ResultSet rs;
    protected DonneesClimatDAO(Connection connexion) {
        super(connexion);
    }
    public ArrayList<DonneesClimat> getDonneesClimatByRegion(Region region) {

        ArrayList<DonneesClimat> liste = new ArrayList<>();
        String SQL= " select * from donnees_climat where id_region=?";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,region.getIdRegion());
            rs = pstmt.executeQuery();


            while (rs.next()) {
                //System.out.println(rs.getInt(1) + " hh "+rs.getint2));
                liste.add(new DonneesClimat(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getFloat(4),rs.getFloat(5),rs.getFloat(6),rs.getInt(7)));
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
    public DonneesClimat getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<DonneesClimat> getAll() {
        return null;
    }

    @Override
    public ArrayList<DonneesClimat> getLike(DonneesClimat objet) {
        return null;
    }

    @Override
    public boolean insert(DonneesClimat objet) {
        return false;
    }

    @Override
    public boolean update(DonneesClimat object) {
        return false;
    }

    @Override
    public boolean delete(DonneesClimat object) {
        return false;
    }
}
