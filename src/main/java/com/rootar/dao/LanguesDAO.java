package com.rootar.dao;

import com.rootar.metier.Langues;
import com.rootar.metier.Objet;
import com.rootar.metier.Parler;
import com.rootar.metier.Pays;
import com.rootar.service.RootarSearch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class LanguesDAO extends DAO <Langues, Langues>{
    private ResultSet rs;

    protected LanguesDAO(Connection connexion) {
        super(connexion);
    }
    public ArrayList<Langues> getLanguesByPays(Pays pays) {

        ArrayList<Langues> liste = new ArrayList<>();
        String SQL= " select * from langues as l where l.id_langues in (select id_langues from parler where id_pays=? )";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){

            // Determine the column set column

            pstmt.setInt(1,pays.getIdPays());
            rs = pstmt.executeQuery();

            while (rs.next()) {

                liste.add(new Langues(rs.getInt(1),rs.getString(2)));
            }
            rs.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
    public Langues getAllByID(String id) {
        Langues liste = new Langues();
        try (Statement stmt = connexion.createStatement()){


            // Determine the column set column

            String strCmd = "SELECT id_langues,libelle_langues from langues where id_langues ="+ id;
            rs = stmt.executeQuery(strCmd);


            while (rs.next()) {

                liste= new Langues(rs.getInt(1),rs.getString(2));
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
    public Langues getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Langues> getAll() {
        ArrayList<Langues> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()){


            // Determine the column set column

            String strCmd = "SELECT id_langues,libelle_langues from langues ";
            rs = stmt.executeQuery(strCmd);


            while (rs.next()) {

                liste.add(new Langues(rs.getInt(1),rs.getString(2)));
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
    public ArrayList<Langues> getLike(Langues objet) {
        return null;
    }



    @Override
    public boolean insert(Langues langues) {

      return false;
    }

    @Override
    public boolean update(Langues object) {
        return false;
    }

    @Override
    public boolean delete(Langues langues) {
        return false;
    }
}
