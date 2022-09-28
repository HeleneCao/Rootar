package com.rootar.service;

import com.rootar.metier.*;

public class RootarSearch {

    private Pays pays;
    private Continent continent;
    private Ville ville;
    private TypeClimat typeClimat;
    private Themes theme;
    private String libRecherche;


    public RootarSearch() {
        pays = new Pays();
        continent = new Continent();
        ville = new Ville();
        typeClimat = new TypeClimat();
        theme= new Themes();
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

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public TypeClimat getTypeClimat() {
        return typeClimat;
    }

    public void setTypeClimat(TypeClimat typeClimat) {
        this.typeClimat = typeClimat;
    }

    public Themes getTheme() {
        return theme;
    }

    public String getLibRecherche() {
        return libRecherche;
    }

    public void setLibRecherche(String libRecherche) {
        this.libRecherche = libRecherche;
    }

    public void setTheme(Themes theme) {
        this.theme = theme;
    }

}
