package com.rootar.dao;

import com.rootar.metier.Region;
import com.rootar.metier.Ville;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class VilleDAO extends DAO <Ville, Ville>{
    private ResultSet rs;
    protected VilleDAO(Connection connexion) {
        super(connexion);
    }
    public ArrayList<Ville> getVilleByRegion(Region region) {

        ArrayList<Ville> liste = new ArrayList<>();
        String SQL= " select * from ville where id_region =?";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,region.getIdRegion());
            rs = pstmt.executeQuery();


            while (rs.next()) {
                //System.out.println(rs.getString(2) + "vill");
                liste.add(new Ville(rs.getInt(1),rs.getString(2)));
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
    public Ville getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Ville> getAll() {
        return null;
    }

    @Override
    public ArrayList<Ville> getLike(Ville objet) {
        return null;
    }

    @Override
    public boolean insert(Ville objet) {
        return false;
    }

    @Override
    public boolean update(Ville object) {
        return false;
    }

    @Override
    public boolean delete(Ville object) {
        return false;
    }
}
