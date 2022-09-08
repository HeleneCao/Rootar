package com.rootar.dao;

import com.rootar.metier.Pays;
import com.rootar.metier.Region;
import com.rootar.metier.TypeClimat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TypeClimatDAO extends DAO <TypeClimat, TypeClimat>{
    private ResultSet rs;
    protected TypeClimatDAO(Connection connexion) {
        super(connexion);
    }
    public TypeClimat getTypeClimatByRegion(Region region) {

        TypeClimat typeClimat = new TypeClimat() ;
        String SQL= " select * from type_climat where id_type_climat =(select id_type_climat from region where id_region= ?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,region.getIdRegion());
            rs = pstmt.executeQuery();


            while (rs.next()) {

                typeClimat = new TypeClimat(rs.getInt(1),rs.getString(2));
            }
            rs.close();

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        return typeClimat;
    }
    @Override
    public TypeClimat getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<TypeClimat> getAll() {
        return null;
    }

    @Override
    public ArrayList<TypeClimat> getLike(TypeClimat objet) {
        return null;
    }

    @Override
    public boolean insert(TypeClimat objet) {
        return false;
    }

    @Override
    public boolean update(TypeClimat object) {
        return false;
    }

    @Override
    public boolean delete(TypeClimat object) {
        return false;
    }
}
