package com.rootar.dao;

import com.rootar.metier.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoriesDAO extends DAO<Categories, Categories> {
    private ResultSet rs;

    protected CategoriesDAO(Connection connexion) {
        super(connexion);
    }

    public Categories getCategoriesByObjet(Objet objet) {

        Categories categories = new Categories();
        String SQL = " select * from categories where id_categories in (select id_categories from objet where id_objet=?)";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)) {


            // Determine the column set column

            pstmt.setInt(1, objet.getIdObjet());
            rs = pstmt.executeQuery();


            while (rs.next()) {


                categories = new Categories(rs.getInt(1), rs.getString(2));
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

        Categories categories = new Categories();
        String SQL = "select * from categories where id_categories = ?";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(SQL)) {

            pStmt.setInt(1, id);


            rs = pStmt.executeQuery();
            while (rs.next()) {


                categories = new Categories(rs.getInt(1), rs.getString(2));
            }
            rs.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return categories;
    }


    public ArrayList<Categories> getAll() {
        ArrayList<Categories> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()) {

            // Determine the column set column

            String strCmd = "SELECT id_categories, libelle_categories from categories";
            rs = stmt.executeQuery(strCmd);


            while (rs.next()) {

                liste.add(new Categories(rs.getInt(1), rs.getString(2)));
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
    public ArrayList<Categories> getLike(Categories categories) {
        return null;
    }

    @Override
    public boolean insert(Categories categories) {
        String SQL = "INSERT INTO CATEGORIES (LIBELLE_CATEGORIES)" + " VALUES (?)";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(SQL)) {
            if (categories != null) {
                pStmt.setString(1, categories.getLibelleCategories());
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
        String SQL = "update CATEGORIES set LIBELLE_CATEGORIES=? where ID_CATEGORIES = ?";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(SQL))
        {
            if(categories != null) {

                pStmt.setString(1,categories.getLibelleCategories());
                pStmt.setInt(2,categories.getIdCategories());

                pStmt.executeUpdate();
                pStmt.close();
            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Categories categories) {
        String delete = "DELETE FROM CATEGORIES WHERE ID_CATEGORIES= ?";

        try (PreparedStatement pStmt = this.connexion.prepareStatement(delete)) {
            if(categories !=null) {

                pStmt.setInt(1,categories.getIdCategories());
                pStmt.executeUpdate();
                pStmt.close();
            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
