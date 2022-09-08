package com.rootar.dao;

import com.rootar.metier.RepresentationEtrangere;

import java.sql.Connection;
import java.util.ArrayList;

public class RepresentationEtrangereDAO extends DAO <RepresentationEtrangere, RepresentationEtrangere> {
    protected RepresentationEtrangereDAO(Connection connexion) {
        super(connexion);
    }

    @Override
    public RepresentationEtrangere getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<RepresentationEtrangere> getAll() {
        return null;
    }

    @Override
    public ArrayList<RepresentationEtrangere> getLike(RepresentationEtrangere objet) {
        return null;
    }

    @Override
    public boolean insert(RepresentationEtrangere objet) {
        return false;
    }

    @Override
    public boolean update(RepresentationEtrangere object) {
        return false;
    }

    @Override
    public boolean delete(RepresentationEtrangere object) {
        return false;
    }
}
