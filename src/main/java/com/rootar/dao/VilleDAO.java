package com.rootar.dao;

import com.rootar.metier.Region;
import com.rootar.metier.Ville;

import java.sql.*;
import java.util.ArrayList;

public class VilleDAO extends DAO <Ville, Ville>{
    private ResultSet rs;
    protected VilleDAO(Connection connexion) {
        super(connexion);
    }
    public ArrayList<Ville> getVilleByRegion(Region region) {

        ArrayList<Ville> liste = new ArrayList<>();
        String SQL= " select * from ville where id_region =?";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,region.getIdRegion());
            rs = pstmt.executeQuery();


            while (rs.next()) {
                //System.out.println(rs.getString(2) + "vill");
                liste.add(new Ville(rs.getInt(1),rs.getString(2)));
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
    public Ville getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Ville> getAll() {
        return null;
    }

    @Override
    public ArrayList<Ville> getLike(Ville objet) {
        return null;
    }

    @Override
    public boolean insert(Ville ville) {
        String SQL = "INSERT INTO ville(nom_ville,id_region) "+" VALUES (?,?)";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(SQL))
        {
            if(ville !=null) {
                pStmt.setString(1,ville.getNomVille());
                pStmt.setInt(2,ville.getRegion().getIdRegion());
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
    public boolean update(Ville ville) {
        String SQL = "update ville set nom_ville=?, id_region=?  where id_ville = ?";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(SQL))
        {
            if(ville !=null) {

                pStmt.setString(1,ville.getNomVille());
                pStmt.setInt(2,ville.getIdRegion());
                pStmt.setInt(3,ville.getIdVille());
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
    public boolean delete(Ville ville) {
        String delete = "DELETE FROM VILLE WHERE ID_PAYS = ?";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(delete))
        {
            if(ville !=null) {



                pStmt.setInt(1,ville.getIdVille());
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
