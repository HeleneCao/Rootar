package com.rootar.dao;

import com.rootar.metier.Visas;

import java.sql.Connection;
import java.util.ArrayList;

public class VisasDAO extends DAO <Visas, Visas>{
    protected VisasDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Visas getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Visas> getAll() {
        return null;
    }

    @Override
    public ArrayList<Visas> getLike(Visas objet) {
        return null;
    }

    @Override
    public boolean insert(Visas objet) {
        return false;
    }

    @Override
    public boolean update(Visas object) {
        return false;
    }

    @Override
    public boolean delete(Visas object) {
        return false;
    }
}
