package com.rootar.dao;

import com.rootar.metier.Priorite;
import com.rootar.metier.Sante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PrioriteDAO extends DAO <Priorite, Priorite> {
    private ResultSet rs;
    protected PrioriteDAO(Connection connexion) {
        super(connexion);
    }
    public Priorite getPrioriteBySante(Sante sante) {

        Priorite priorite= new Priorite();
        String SQL= " select * from priorite where id_priorite=(select id_priorite from sante where id_sante= ?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,sante.getIdSante());
            rs = pstmt.executeQuery();


            while (rs.next()) {

                sante = new Sante(rs.getInt(1),rs.getString(2));
            }
            rs.close();

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        return priorite;
    }
    @Override
    public Priorite getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Priorite> getAll() {
        return null;
    }

    @Override
    public ArrayList<Priorite> getLike(Priorite objet) {
        return null;
    }

    @Override
    public boolean insert(Priorite objet) {
        return false;
    }

    @Override
    public boolean update(Priorite object) {
        return false;
    }

    @Override
    public boolean delete(Priorite object) {
        return false;
    }
}
