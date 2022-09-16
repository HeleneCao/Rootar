package com.rootar.dao;

import com.rootar.metier.*;
import com.rootar.service.ServiceRootar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.BitSet;

public class SanteDAO extends DAO <Sante, Sante>{
    private ResultSet rs;
    protected SanteDAO(Connection connexion) {
        super(connexion);
    }
    public ArrayList<Sante> getSanteByPays(Pays pays) {

        ArrayList<Sante> liste = new ArrayList<>();
        Priorite priorite=new Priorite();
        String SQL= " select * from sante as s where s.id_sante in (select id_sante from exiger where id_pays=? )";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,pays.getIdPays());
            rs = pstmt.executeQuery();

            while (rs.next()) {
                priorite.setIdPriorite(rs.getInt(3));
                liste.add(new Sante(rs.getInt(1),rs.getString(2),priorite));
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
    public Sante getByID(int id) {
        String SQL= " select * from sante as s where s.id_sante =?";
        Sante sante = new Sante();
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,id);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                sante=new Sante(rs.getInt(1),rs.getString(2));

            }
            rs.close();

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }

        return sante;
    }

    @Override
    public ArrayList<Sante> getAll() {
        return null;
    }

    @Override
    public ArrayList<Sante> getLike(Sante objet) {
        return null;
    }

    @Override
    public boolean insert(Sante sante) {
        String SQL = "INSERT INTO SANTE(LIBELLE_SANTE,ID_PRIORITE) "+" VALUES(?,?)";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(SQL))
        {
            if(sante !=null) {
                pStmt.setString(1,sante.getLibelleSante());
                pStmt.setInt(2,sante.getPriorite().getIdPriorite());

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
    public boolean update(Sante sante) {

        String update = "update SANTE set LIBELLE_SANTE=?, ID_PRIORITE=? where ID_SANTE = ?";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(update))
        {
            if(sante !=null) {

                pStmt.setString(1,sante.getLibelleSante());
                pStmt.setInt(2,sante.getPriorite().getIdPriorite());


                pStmt.setInt(3,sante.getIdSante());
                System.out.println(sante.getLibelleSante());
                System.out.println(sante.getPriorite().getIdPriorite());
                System.out.println(sante.getIdSante());


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
    public boolean delete(Sante sante) {
        String delete = "DELETE FROM SANTE WHERE ID_SANTE = ?";

        try (PreparedStatement pStmt = this.connexion.prepareStatement(delete)) {
            if(sante !=null) {

                pStmt.setInt(1,sante.getIdSante());
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
