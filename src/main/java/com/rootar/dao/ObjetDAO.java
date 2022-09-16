package com.rootar.dao;

import com.rootar.metier.Categories;
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

    public Objet lastId(){
        String SQL = "select max(id_objet) from objet";
        Objet objet = new Objet();
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            rs = pstmt.executeQuery();


            while (rs.next()) {

              int id=rs.getInt(1);
                System.out.println(id);
                objet.setIdObjet(id);
            }
            rs.close();

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        return objet;
    }


    public ArrayList<Objet> getObjetByPays(Pays pays) {

        ArrayList<Objet> liste = new ArrayList<>();
        String SQL= " select * from objet as o where o.id_objet in (select id_objet from emporter where id_pays=? )";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,pays.getIdPays());
            rs = pstmt.executeQuery();


            while (rs.next()) {
                Categories categories= new Categories();
                categories.setIdCategories(rs.getInt(3));
                liste.add(new Objet(rs.getInt(1),rs.getString(2),categories));
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
        String SQL = "INSERT INTO OBJET (LIBELLE_OBJET, ID_CATEGORIES) "+" VALUES (?,?)";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(SQL))
        {
            if(objet !=null) {
                pStmt.setString(1,objet.getLibelleObjet());
                pStmt.setInt(2,objet.getCategories().getIdCategories());
                pStmt.execute();
            }
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Objet objet) {
       String SQL = "UPDATE OBJET SET LIBELLE_OBJET=? , ID_CATEGORIES=? WHERE ID_OBJET=?";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(SQL))
        {
            if(objet !=null) {

                pStmt.setString(1,objet.getLibelleObjet());
                pStmt.setInt(2,objet.getCategories().getIdCategories());
                pStmt.setInt(3,objet.getIdObjet());
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
    public boolean delete(Objet objet) {
        String delete = "DELETE FROM OBJET WHERE ID_OBJET = ?";

        try (PreparedStatement pStmt = this.connexion.prepareStatement(delete)) {
            if(objet !=null) {

                pStmt.setInt(1,objet.getIdObjet());
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
