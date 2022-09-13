package com.rootar.dao;

import com.rootar.metier.Pays;
import com.rootar.metier.Region;
import com.rootar.metier.RepresentationEtrangere;
import com.rootar.metier.Ville;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class RepresentationEtrangereDAO extends DAO <RepresentationEtrangere, RepresentationEtrangere> {
    private ResultSet rs;
    protected RepresentationEtrangereDAO(Connection connexion) {
        super(connexion);
    }

    public ArrayList<RepresentationEtrangere> getRepEtrangeresByPays(Pays pays) {

        ArrayList<RepresentationEtrangere> liste = new ArrayList<>();
        String SQL= " select * from representation_etrangere where id_pays =?";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,pays.getIdPays());
            rs = pstmt.executeQuery();


            while (rs.next()) {

                liste.add(new RepresentationEtrangere(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            }
            rs.close();

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
    public ArrayList<RepresentationEtrangere> getRepEtrangeresByVille(Ville ville) {

        ArrayList<RepresentationEtrangere> liste = new ArrayList<>();
        String SQL= " select * from representation_etrangere where id_ville=?";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column
            if(ville != null) {
                pstmt.setInt(1, ville.getIdVille());
                rs = pstmt.executeQuery();


                while (rs.next()) {

                    liste.add(new RepresentationEtrangere(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)));
                }
                rs.close();
            }
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
    @Override
    public RepresentationEtrangere getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<RepresentationEtrangere> getAll() {
        return null;
    }

    @Override
    public ArrayList<RepresentationEtrangere> getLike(RepresentationEtrangere objet) {
        return null;
    }

    @Override
    public boolean insert(RepresentationEtrangere objet) {
        return false;
    }

    @Override
    public boolean update(RepresentationEtrangere object) {
        return false;
    }

    @Override
    public boolean delete(RepresentationEtrangere object) {
        return false;
    }
}
