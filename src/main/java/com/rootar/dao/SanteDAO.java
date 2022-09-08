package com.rootar.dao;

import com.rootar.metier.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SanteDAO extends DAO <Sante, Sante>{
    private ResultSet rs;
    protected SanteDAO(Connection connexion) {
        super(connexion);
    }
    public ArrayList<Sante> getSanteByPays(Pays pays) {

        ArrayList<Sante> liste = new ArrayList<>();
        String SQL= " select * from sante as s where s.id_sante in (select id_sante from exiger where id_pays=? )";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,pays.getIdPays());
            rs = pstmt.executeQuery();


            while (rs.next()) {

                liste.add(new Sante(rs.getInt(1),rs.getString(2),rs.getBoolean(3)));
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
    public Sante getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Sante> getAll() {
        return null;
    }

    @Override
    public ArrayList<Sante> getLike(Sante objet) {
        return null;
    }

    @Override
    public boolean insert(Sante objet) {
        return false;
    }

    @Override
    public boolean update(Sante object) {
        return false;
    }

    @Override
    public boolean delete(Sante object) {
        return false;
    }
}
