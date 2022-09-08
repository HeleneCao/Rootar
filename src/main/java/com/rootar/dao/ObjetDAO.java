package com.rootar.dao;

import com.rootar.metier.Objet;
import com.rootar.metier.Pays;
import com.rootar.metier.Sante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ObjetDAO extends DAO <Objet, Objet>{
    private ResultSet rs;
    protected ObjetDAO(Connection connexion) {
        super(connexion);
    }
    public ArrayList<Objet> getObjetByPays(Pays pays) {

        ArrayList<Objet> liste = new ArrayList<>();
        String SQL= " select * from objet as o where o.id_objet in (select id_objet from emporter where id_pays=? )";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,pays.getIdPays());
            rs = pstmt.executeQuery();


            while (rs.next()) {

                liste.add(new Objet(rs.getInt(1),rs.getString(2)));
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
    public Objet getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Objet> getAll() {
        return null;
    }

    @Override
    public ArrayList<Objet> getLike(Objet objet) {
        return null;
    }

    @Override
    public boolean insert(Objet objet) {
        return false;
    }

    @Override
    public boolean update(Objet object) {
        return false;
    }

    @Override
    public boolean delete(Objet object) {
        return false;
    }
}
