package com.rootar.dao;


import com.rootar.metier.Parler;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ParlerDAO extends DAO <Parler,Parler>{
    private ResultSet rs;
    protected ParlerDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Parler getByID(int id) {
        return null;
    }
    @Override
    public ArrayList<Parler> getAll(){
        return null;
    }

    public Parler getAllByID(int id) {
        Parler liste = new Parler(1,"");
        try (Statement stmt = connexion.createStatement()){


            // Determine the column set column

            String strCmd = "SELECT id_pays,id_langues from parler as P where id_pays ="+ id;
            rs = stmt.executeQuery(strCmd);


            while (rs.next()) {

               liste= new Parler(rs.getInt(1),rs.getString(2));
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
    public ArrayList<Parler> getLike(Parler parler) {
        return null;
    }

    @Override
    public boolean insert(Parler parler) {
        return false;
    }

    @Override
    public boolean update(Parler parler) {
        return false;
    }

    @Override
    public boolean delete(Parler parler) {
        return false;
    }
}
