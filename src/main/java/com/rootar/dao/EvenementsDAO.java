package com.rootar.dao;

import com.rootar.metier.Evenements;
import com.rootar.metier.Region;
import com.rootar.metier.Ville;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EvenementsDAO extends DAO <EvenementsDAO, EvenementsDAO>{
    private ResultSet rs;
    protected EvenementsDAO(Connection connexion) {
        super(connexion);
    }
    public ArrayList<Evenements> getEventByVille(Ville ville) {

        ArrayList<Evenements> liste = new ArrayList<>();
        String SQL= " select * from evenements where id_ville =?";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,ville.getIdVille());
            rs = pstmt.executeQuery();


            while (rs.next()) {

                liste.add(new Evenements(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
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
    public EvenementsDAO getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<EvenementsDAO> getAll() {
        return null;
    }

    @Override
    public ArrayList<EvenementsDAO> getLike(EvenementsDAO objet) {
        return null;
    }

    @Override
    public boolean insert(EvenementsDAO objet) {
        return false;
    }

    @Override
    public boolean update(EvenementsDAO object) {
        return false;
    }

    @Override
    public boolean delete(EvenementsDAO object) {
        return false;
    }
}
