package com.rootar.dao;

import com.rootar.metier.*;
import com.rootar.service.RootarSearch;

import java.sql.*;
import java.util.ArrayList;

public class VilleDAO extends DAO <Ville, RootarSearch>{
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

                liste.add(new Ville(rs.getInt(1),rs.getString(2),new Region(region.getIdRegion(), region.getNomRegion())));
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
        ArrayList<Ville> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()){


            // Determine the column set column

            String strCmd = "SELECT V.ID_VILLE, V.NOM_VILLE,P.ID_PAYS,P.NOM_PAYS_FR from VILLE AS V JOIN REGION AS R  ON R.ID_REGION=V.ID_REGION JOIN PAYS AS P" +
                    " ON  R.ID_PAYS=P.ID_PAYS";
            rs = stmt.executeQuery(strCmd);
            Ville villeLu = new Ville(0,"");

            while (rs.next()) {
                if(villeLu.getIdVille() != rs.getInt(1)) {
                    villeLu = new Ville(rs.getInt(1),rs.getString(2));
                    liste.add(villeLu);
                    //liste.add(new Ville(rs.getInt(1), rs.getString(2)));
                    System.out.println(villeLu.getNomVille());
                    villeLu.getPays().add(new Pays(rs.getInt(3),rs.getString(4)));
                }
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
    public ArrayList<Ville> getLike(RootarSearch rootarSearch) {
        ArrayList<Ville> liste = new ArrayList<>();
        String procedureStockee = "{call dbo.SP_RECHERCHE (?,?,?,?,?,?)}";
        try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee))
        {
            cStmt.setString(1,"nom_ville,id_ville,id_region,nom_region");
            cStmt.setString(2,rootarSearch.getLibRecherche());
            cStmt.setInt(3,rootarSearch.getPays().getIdPays());
            cStmt.setInt(4,rootarSearch.getContinent().getIdContinent());
            cStmt.setInt(5,rootarSearch.getVille().getIdVille());
            cStmt.setInt(6,rootarSearch.getTheme().getIdThemes());
            // cStmt.setInt(6,rootarSearch.getTypeClimat().getIdTypeClimat());

            cStmt.execute();
            rs = cStmt.getResultSet();
            while(rs.next()) {
              Ville newVille = new Ville();
              newVille.setIdVille(rs.getInt(2));
              newVille.setNomVille(rs.getString(1));
              newVille.setRegion(new Region(rs.getInt(3),rs.getString(4)));
              liste.add(newVille);
            }

            rs.close();
        }

        // Handle any errors that may have occurred.
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return liste;
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
                pStmt.setInt(2,ville.getRegion().getIdRegion());
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
        String delete = "DELETE FROM VILLE WHERE ID_VILLE = ?";

        try (PreparedStatement pStmt = this.connexion.prepareStatement(delete)) {
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
