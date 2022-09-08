package com.rootar.dao;

import com.rootar.metier.Themes;

import java.sql.Connection;
import java.util.ArrayList;

public class ThemesDAO extends DAO <Themes, Themes> {
    protected ThemesDAO(Connection connexion) {
        super(connexion);
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
