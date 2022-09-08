package com.rootar.dao;

import com.rootar.metier.Continent;
import com.rootar.metier.Monnaie;
import com.rootar.metier.Pays;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PaysDAO extends DAO <Pays, Pays>{

    private ResultSet rs;
    protected PaysDAO(Connection connexion) {
        super(connexion);
    }

    public ArrayList<Pays> getAll() {
        ArrayList<Pays> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()){


            // Determine the column set column

            String strCmd = "SELECT id_pays, code_pays,nom_pays_fr, nom_pays_ang, nationalite, capitale, nombre_habitant, superficie, devise, fete_nationale, indicatif_telephonique, " +
                  "id_continent, (select nom_continent_fr from continent where id_continent = P.id_continent),id_monnaie, (select libelle_monnaie from monnaie where id_monnaie = P.id_monnaie) from pays as P";
            rs = stmt.executeQuery(strCmd);


            while (rs.next()) {

                liste.add(new Pays(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getInt(7),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getString(11),new Continent(rs.getInt(12),rs.getString(13)),new Monnaie(rs.getInt(14),rs.getString(15))));
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
    public ArrayList<Pays> getLike(Pays objet) {
        return null;
    }

    @Override
    public boolean insert(Pays objet) {
        return false;
    }

    @Override
    public boolean update(Pays object) {
        return false;
    }

    @Override
    public boolean delete(Pays object) {
        return false;
    }
}
