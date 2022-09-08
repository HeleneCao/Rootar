package com.rootar.dao;

import com.rootar.metier.Continent;

import java.sql.Connection;
import java.util.ArrayList;

public class ContinentDAO extends DAO <Continent, Continent>{
    protected ContinentDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Continent getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Continent> getAll() {
        return null;
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
