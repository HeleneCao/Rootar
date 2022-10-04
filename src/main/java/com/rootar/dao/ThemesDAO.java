package com.rootar.dao;

import com.rootar.metier.*;
import com.rootar.service.RootarSearch;

import java.sql.*;
import java.util.ArrayList;

public class ThemesDAO extends DAO <Themes, RootarSearch> {
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
        ArrayList<Themes> liste = new ArrayList<>();
        try (Statement stmt = connexion.createStatement()){


            // Determine the column set column

            String strCmd = "SELECT * FROM THEMES";
            rs = stmt.executeQuery(strCmd);


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
    public ArrayList<Themes> getLike(RootarSearch rootarSearch) {
        ArrayList<Themes> liste = new ArrayList<>();
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
                Themes newThemes= new Themes();
                newThemes.setIdThemes(rs.getInt(9));
                newThemes.setLibelleThemes(rs.getString(8));
                liste.add(newThemes);
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
