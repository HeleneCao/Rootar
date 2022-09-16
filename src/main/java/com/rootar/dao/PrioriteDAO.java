package com.rootar.dao;

import com.rootar.metier.Langues;
import com.rootar.metier.Priorite;
import com.rootar.metier.Sante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PrioriteDAO extends DAO <Priorite, Priorite> {
    private ResultSet rs;
    protected PrioriteDAO(Connection connexion) {
        super(connexion);
    }
    public Priorite getPrioriteBySante(int id) {

        Priorite priorite= new Priorite();
        String SQL= " select * from priorite where id_priorite=(select id_priorite from sante where id_sante= ?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();


            while (rs.next()) {

                priorite = new Priorite(rs.getInt(1),rs.getString(2));
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
        ArrayList<Priorite> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()){


            // Determine the column set column

            String strCmd = "SELECT ID_PRIORITE,LIBELLE_PRIORITE from PRIORITE ";
            rs = stmt.executeQuery(strCmd);


            while (rs.next()) {

                liste.add(new Priorite(rs.getInt(1),rs.getString(2)));
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
