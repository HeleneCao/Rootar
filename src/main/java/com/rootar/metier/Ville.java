package com.rootar.metier;

import java.util.ArrayList;

public class Ville {
    private int idVille;
    private String nomVille;
    private ArrayList<Pays> pays;
    private Region region;

    public Ville(int idVille, String nomVille) {
        this.idVille = idVille;
        this.nomVille = nomVille;
        region= new Region();
        pays= new ArrayList<>();
    }

    public Ville() {
    }

    public Ville(int idVille, String nomVille, Region region) {
        this.idVille = idVille;
        this.nomVille = nomVille;
        this.region=region;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public String getNomVille() {
        return nomVille;
    }

    public void setNomVille(String nomVille) {
        this.nomVille = nomVille;
    }

    public ArrayList<Pays> getPays() {
        return pays;
    }

    @Override
    public String toString() {
        return nomVille ;
    }
}
