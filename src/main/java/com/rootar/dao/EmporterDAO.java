package com.rootar.dao;

import com.rootar.metier.Emporter;
import com.rootar.metier.Parler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class EmporterDAO extends DAO <Emporter, Emporter>{

    private ResultSet rs;

    protected EmporterDAO(Connection connexion){
        super(connexion);
    }

    @Override
    public Emporter getByID(int id) {
        Emporter liste = new Emporter();
        try (Statement stmt = connexion.createStatement()){


            // Determine the column set column

            String strCmd = "SELECT id_objet,id_pays from emporter as P where id_objet ="+ id;
            rs = stmt.executeQuery(strCmd);


            while (rs.next()) {

                liste= new Emporter(rs.getInt(1),rs.getInt(2));
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
    public ArrayList<Emporter> getAll() {
        return null;
    }

    @Override
    public ArrayList<Emporter> getLike(Emporter emporter) {
        return null;
    }

    @Override
    public boolean insert(Emporter emporter) {
        String SQL = "INSERT INTO emporter (id_objet, id_pays)" + " VALUES (?,?)";
        try (PreparedStatement pStmt = this.connexion.prepareStatement(SQL)) {
            if (emporter!= null) {
                pStmt.setInt(1,emporter.getIdObjet());
                pStmt.setInt(2,emporter.getIdPays());
                pStmt.execute();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Emporter object) {
        return false;
    }

    @Override
    public boolean delete(Emporter emporter) {
        String delete = "DELETE FROM Emporter WHERE ID_OBJET= ?";

        try (PreparedStatement pStmt = this.connexion.prepareStatement(delete)) {
            if (emporter!= null) {
                pStmt.setInt(1, emporter.getIdObjet());
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
