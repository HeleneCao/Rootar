package com.rootar.dao;

import com.rootar.metier.Posseder;

import java.sql.Connection;
import java.util.ArrayList;

public class PossederDAO extends DAO <Posseder, Posseder>{

    protected PossederDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public Posseder getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Posseder> getAll() {
        return null;
    }

    @Override
    public ArrayList<Posseder> getLike(Posseder objet) {
        return null;
    }

    @Override
    public boolean insert(Posseder objet) {
        return false;
    }

    @Override
    public boolean update(Posseder object) {
        return false;
    }

    @Override
    public boolean delete(Posseder object) {
        return false;
    }
}
