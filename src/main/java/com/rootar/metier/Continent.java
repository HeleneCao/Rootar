package com.rootar.metier;

public class Continent {

    private int idContinent;
    private String nomContinentFr;
    private String getNomContinentAng;

    public Continent() {
        this.idContinent = idContinent;
        this.nomContinentFr = nomContinentFr;
        this.getNomContinentAng = getNomContinentAng;
    }

    public int getIdContinent() {
        return idContinent;
    }

    public void setIdContinent(int idContinent) {
        this.idContinent = idContinent;
    }

    public String getNomContinentFr() {
        return nomContinentFr;
    }

    public void setNomContinentFr(String nomContinentFr) {
        this.nomContinentFr = nomContinentFr;
    }

    public String getGetNomContinentAng() {
        return getNomContinentAng;
    }

    public void setGetNomContinentAng(String getNomContinentAng) {
        this.getNomContinentAng = getNomContinentAng;
    }
}
