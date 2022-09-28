package com.rootar.dao;

import com.rootar.metier.Continent;
import com.rootar.metier.Pays;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ContinentDAO extends DAO <Continent, Continent>{
    private ResultSet rs;
    protected ContinentDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Continent getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Continent> getAll() {
       ArrayList <Continent> liste= new ArrayList<>();
        try (Statement stmt = connexion.createStatement())
        {

            // Determine the column set column

            String strCmd = "SELECT C.id_continent,nom_continent_fr ,id_pays,nom_pays_fr from continent as C join pays on C.id_continent=pays.ID_CONTINENT order by nom_continent_fr,nom_pays_fr";
            ResultSet rs = stmt.executeQuery(strCmd);
            Continent continentLu = new Continent(0,"");
            while (rs.next())
            {
                if (continentLu.getIdContinent() != rs.getInt(1))
                {
                    continentLu = new Continent(rs.getInt(1), rs.getString(2));
                    liste.add(continentLu);
                }
                continentLu.getPays().add(new Pays(rs.getInt(3),rs.getString(4)));
            }
            rs.close();
        }

        // Handle any errors that may have occurred.
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return liste;
    }


    @Override
    public ArrayList<Continent> getLike(Continent objet) {
        return null;
    }

    @Override
    public boolean insert(Continent objet) {
        return false;
    }

    @Override
    public boolean update(Continent object) {
        return false;
    }

    @Override
    public boolean delete(Continent object) {
        return false;
    }
}
