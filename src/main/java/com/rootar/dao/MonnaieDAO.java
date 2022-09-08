package com.rootar.dao;

import com.rootar.metier.Monnaie;

import java.sql.Connection;
import java.util.ArrayList;

public class MonnaieDAO extends DAO <Monnaie, Monnaie>{
    protected MonnaieDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Monnaie getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Monnaie> getAll() {
        return null;
    }

    @Override
    public ArrayList<Monnaie> getLike(Monnaie objet) {
        return null;
    }

    @Override
    public boolean insert(Monnaie objet) {
        return false;
    }

    @Override
    public boolean update(Monnaie object) {
        return false;
    }

    @Override
    public boolean delete(Monnaie object) {
        return false;
    }
}
