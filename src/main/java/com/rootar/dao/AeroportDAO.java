package com.rootar.dao;

import com.rootar.metier.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AeroportDAO extends DAO <Aeroports, Aeroports>{

    private ResultSet rs;

    protected AeroportDAO(Connection connexion) {
        super(connexion);
    }

    public ArrayList<Aeroports> getAeroportByVille(Ville ville) {

        ArrayList<Aeroports> liste = new ArrayList<>();
        String SQL= " select * from aeroport as a where a.id_ville in (select id_ville from ville where id_ville=? )";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){

            // Determine the column set column

            pstmt.setInt(1, ville.getIdVille());
            rs = pstmt.executeQuery();
            rs.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    @Override
    public Aeroports getByID(int id) {
        String SQL= " select * from aeroport as a where a.id_aeroport =?";
        Aeroports aeroports = new Aeroports();
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                aeroports=new Aeroports(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4));

            }
            rs.close();

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }

        return aeroports;

    }

    @Override
    public ArrayList<Aeroports> getAll() {
        return null;
    }

    @Override
    public ArrayList<Aeroports> getLike(Aeroports objet) {
        return null;
    }

    @Override
    public boolean insert(Aeroports objet) {
        return false;
    }

    @Override
    public boolean update(Aeroports object) {
        return false;
    }

    @Override
    public boolean delete(Aeroports object) {
        return false;
    }
}
