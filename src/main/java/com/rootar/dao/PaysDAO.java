package com.rootar.dao;

import com.rootar.metier.*;
import com.rootar.service.RootarSearch;

import java.sql.*;
import java.util.ArrayList;

public class PaysDAO extends DAO <Pays, RootarSearch>{

    private ResultSet rs;
    protected PaysDAO(Connection connexion) {
        super(connexion);
    }

    public ArrayList<Pays> getAll() {
        ArrayList<Pays> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()){


            // Determine the column set column

            String strCmd = "SELECT id_pays, code_pays,nom_pays_fr, nom_pays_ang, nationalite, nombre_habitant, superficie, devise, fete_nationale, indicatif_telephonique, " +
                  "id_continent, (select nom_continent_fr from continent where id_continent = P.id_continent),id_monnaie, (select libelle_monnaie from monnaie where id_monnaie = P.id_monnaie) , id_ville, (select nom_ville from ville where id_ville = P.id_ville) from pays as P";
            rs = stmt.executeQuery(strCmd);


            while (rs.next()) {

                liste.add(new Pays(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getInt(7),rs.getString(8),rs.getString(9),rs.getString(10),new Continent(rs.getInt(11),rs.getString(12)),new Monnaie(rs.getInt(13),rs.getString(14)), new Ville(rs.getInt(15),rs.getString(16))));
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
    public ArrayList<Pays> getLike(RootarSearch rootarSearch) {
        ArrayList<Pays> liste = new ArrayList<>();
        String procedureStockee = "{call dbo.SP_RECHERCHE_PAYS (?,?,?,?,?)}";
        try (CallableStatement cStmt = this.connexion.prepareCall(procedureStockee))
        {

            cStmt.setString(1,rootarSearch.getLibRecherche());
            cStmt.setInt(2,rootarSearch.getPays().getIdPays());
            cStmt.setInt(3,rootarSearch.getContinent().getIdContinent());
            cStmt.setInt(4,rootarSearch.getVille().getIdVille());
            cStmt.setInt(5,rootarSearch.getTheme().getIdThemes());
           // cStmt.setInt(6,rootarSearch.getTypeClimat().getIdTypeClimat());

            cStmt.execute();
            rs = cStmt.getResultSet();
            while(rs.next()) {
                Pays newPays= new Pays();
                newPays.setIdPays(rs.getInt(11));
                newPays.setNomPaysFr(rs.getString(1));
                newPays.setNomPaysAng(rs.getString(2));
                newPays.setContinent(new Continent(rs.getInt(3),rs.getString(4)));
                newPays.setCodePays(rs.getString(15));
                newPays.setNationalite(rs.getString(16));
                newPays.setNbreHabitant(rs.getInt(17));
                newPays.setSuperficie(rs.getInt(18));
                newPays.setDevise(rs.getString(19));
                newPays.setIndicatifTel(rs.getString(20));
                newPays.setFeteNationale(rs.getString(21));
                newPays.setMonnaie(new Monnaie(rs.getInt(22),rs.getString(23)));
                liste.add(newPays);
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



    public ArrayList<Pays> getPaysByObjet(Objet objet){
        ArrayList<Pays> liste = new ArrayList<>();
        String SQL= " select id_pays, nom_pays_fr from pays as p where p.id_pays in (select id_pays from emporter where id_objet=? )";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,objet.getIdObjet());
            rs = pstmt.executeQuery();


            while (rs.next()) {

                liste.add(new Pays(rs.getInt(1),rs.getString(2)));
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
    public Pays getByID(int id) {
        return null;
    }


    @Override
    public boolean insert(Pays pays) {

        String SQL = "INSERT INTO PAYS (ID_PAYS, CODE_PAYS, NOM_PAYS_FR, NOM_PAYS_ANG, NATIONALITE, NOMBRE_HABITANT, SUPERFICIE, DEVISE, FETE_NATIONALE, " +
                "INDICATIF_TELEPHONIQUE, ID_CONTINENT, ID_MONNAIE, ID_VISAS , ID_VILLE)"+" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(SQL))
        {
            if(pays !=null) {
                pStmt.setInt(1, pays.getIdPays());
                pStmt.setString(2,pays.getCodePays());
                pStmt.setString(3, pays.getNomPaysFr());
                pStmt.setString(4,pays.getNomPaysAng());
                pStmt.setString(5, pays.getNationalite());
                pStmt.setInt(6, pays.getNbreHabitant());
                pStmt.setInt(7, pays.getSuperficie());
                pStmt.setString(8, pays.getDevise());
                pStmt.setString(9, pays.getFeteNationale());
                pStmt.setString(10, pays.getIndicatifTel());
                pStmt.setInt(11, pays.getContinent().getIdContinent());
                pStmt.setInt(12, pays.getMonnaie().getIdMonnaie());
                pStmt.setNull(13, Types.NULL);
                pStmt.setInt(14, pays.getVille().getIdVille());
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
    public boolean update(Pays pays) {
        String SQL = "update PAYS set CODE_PAYS = ?,NOM_PAYS_FR=?,NOM_PAYS_ANG = ?,NATIONALITE = ?,NOMBRE_HABITANT = ?,SUPERFICIE=? ,DEVISE=?,  FETE_NATIONALE=?, INDICATIF_TELEPHONIQUE=?, ID_CONTINENT=?, ID_MONNAIE=?, ID_VISAS=?, ID_VILLE=? where ID_PAYS = ?";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(SQL))
        {
            if(pays !=null) {

                pStmt.setString(1,pays.getCodePays());
                pStmt.setString(2, pays.getNomPaysFr());
                pStmt.setString(3,pays.getNomPaysAng());
                pStmt.setString(4, pays.getNationalite());
                pStmt.setInt(6, pays.getNbreHabitant());
                pStmt.setInt(7, pays.getSuperficie());
                pStmt.setString(8, pays.getDevise());
                pStmt.setString(9, pays.getFeteNationale());
                pStmt.setString(10, pays.getIndicatifTel());
                pStmt.setInt(11, pays.getContinent().getIdContinent());
                pStmt.setInt(12, pays.getMonnaie().getIdMonnaie());
                pStmt.setNull(13, Types.NULL);
                pStmt.setInt(14, pays.getVille().getIdVille());
                pStmt.setInt(15, pays.getIdPays());
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
    public boolean delete(Pays pays) {
        try(Statement stmt = connexion.createStatement()){
            String delete = "DELETE FROM PAYS WHERE ID_PAYS = " + pays.getIdPays();
            stmt.execute(delete);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }


}
