package com.rootar.dao;

import com.rootar.metier.Pays;
import com.rootar.metier.Region;
import com.rootar.metier.Themes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ThemesDAO extends DAO <Themes, Themes> {
    private ResultSet rs;
    protected ThemesDAO(Connection connexion) {
        super(connexion);
    }
    public ArrayList<Themes> getThemesByPays(Pays pays) {

        ArrayList<Themes> liste = new ArrayList<>();
        String SQL= " select * from themes as t where t.id_theme in (select id_theme from avoir where id_pays=? )";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,pays.getIdPays());
            rs = pstmt.executeQuery();


            while (rs.next()) {

                liste.add(new Themes(rs.getInt(1),rs.getString(2)));
            }
            rs.close();

        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
    public ArrayList<Themes> getThemesByRegion(Region region) {

        ArrayList<Themes> liste = new ArrayList<>();
        String SQL= " select * from themes as t where t.id_theme in (select id_theme from appartenir where id_region=? )";
        try (PreparedStatement pstmt = connexion.prepareStatement(SQL)){


            // Determine the column set column

            pstmt.setInt(1,region.getIdRegion());
            rs = pstmt.executeQuery();


            while (rs.next()) {

                liste.add(new Themes(rs.getInt(1),rs.getString(2)));
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
    public Themes getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Themes> getAll() {
        return null;
    }

    @Override
    public ArrayList<Themes> getLike(Themes objet) {
        return null;
    }

    @Override
    public boolean insert(Themes objet) {
        return false;
    }

    @Override
    public boolean update(Themes object) {
        return false;
    }

    @Override
    public boolean delete(Themes object) {
        return false;
    }
}