package com.rootar.service;

import com.rootar.metier.Continent;
import com.rootar.metier.Pays;

public class RootarSearch {

    private Pays pays;
    private Continent continent;

    public RootarSearch() {
        pays = new Pays();
        continent = new Continent();
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }
}
