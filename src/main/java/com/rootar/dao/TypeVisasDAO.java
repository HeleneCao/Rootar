package com.rootar.dao;

import com.rootar.metier.TypeVisas;

import java.sql.Connection;
import java.util.ArrayList;

public class TypeVisasDAO extends DAO <TypeVisas, TypeVisas>{
    protected TypeVisasDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public TypeVisas getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<TypeVisas> getAll() {
        return null;
    }

    @Override
    public ArrayList<TypeVisas> getLike(TypeVisas objet) {
        return null;
    }

    @Override
    public boolean insert(TypeVisas objet) {
        return false;
    }

    @Override
    public boolean update(TypeVisas object) {
        return false;
    }

    @Override
    public boolean delete(TypeVisas object) {
        return false;
    }
}
