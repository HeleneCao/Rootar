package com.rootar.metier;

public class Ville {
    private int idVille;
    private String nomVille;

    private Region region;

    public Ville(int idVille, String nomVille) {
        this.idVille = idVille;
        this.nomVille = nomVille;
        region= new Region();
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


    @Override
    public String toString() {
        return nomVille ;
    }
}
