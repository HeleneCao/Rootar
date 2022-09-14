package com.rootar.dao;

import com.rootar.metier.Categories;
import com.rootar.metier.Objet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoriesDAO extends DAO <Categories, Categories>{
    private ResultSet rs;
    protected CategoriesDAO(Connection connexion) {
        super(connexion);
    }
    public Categories getCategoriesByObjet(Objet objet) {

        Categories categories= new Categories();
        String SQL= " select * from categories where id_categories in (select id_categories from objet where id_objet=?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,objet.getIdObjet());
            rs = pstmt.executeQuery();


            while (rs.next()) {


                categories = new Categories(rs.getInt(1),rs.getString(2));
            }
            rs.close();

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
    @Override
    public Categories getByID(int id) {

        return null;
    }


    public ArrayList<Categories> getAll() {
        return null;
    }

    @Override
    public ArrayList<Categories> getLike(Categories categories) {
        return null;
    }

    @Override
    public boolean insert(Categories categories) {
        String SQL = "INSERT INTO CATEGORIES (LIBELLE_CATEGORIES)" + " VALUES (?)";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(SQL)) {
            if (categories!= null) {
                pStmt.setString(1,categories.getLibelleCategories());
                pStmt.execute();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Categories categories) {
        return false;
    }

    @Override
    public boolean delete(Categories categories) {
        return false;
    }
}
