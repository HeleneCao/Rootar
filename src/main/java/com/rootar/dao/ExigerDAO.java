package com.rootar.dao;


import com.rootar.metier.Exiger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ExigerDAO extends DAO <Exiger, Exiger>{

    private ResultSet rs;

    protected ExigerDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Exiger getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Exiger> getAll() {
        return null;
    }

    @Override
    public ArrayList<Exiger> getLike(Exiger exiger) {
        return null;
    }

    @Override
    public boolean insert(Exiger exiger) {
        String SQL = "INSERT INTO EXIGER ( ID_PAYS, ID_SANTE)" + " VALUES (?,?)";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(SQL)) {
            if (exiger!= null) {
                pStmt.setInt(1,exiger.getPays().getIdPays());
                pStmt.setInt(2,exiger.getSante().getIdSante());
                pStmt.execute();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Exiger exiger) {
        return false;
    }

    @Override
    public boolean delete(Exiger exiger) {
        String delete = "DELETE FROM EXIGER WHERE ID_PAYS= ?";

        try (PreparedStatement pStmt = this.connexion.prepareStatement(delete)) {
            if (exiger!= null) {
                pStmt.setInt(1, exiger.getPays().getIdPays());
                pStmt.executeUpdate();
                pStmt.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
