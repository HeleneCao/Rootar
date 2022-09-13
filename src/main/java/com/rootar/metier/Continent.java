package com.rootar.metier;

public class Continent {

    private int idContinent;
    private String nomContinentFr;
    private String nomContinentAng;

    public Continent() {

    }

    public Continent(int idContinent, String nomContinentFr) {
        this.idContinent = idContinent;
        this.nomContinentFr = nomContinentFr;

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
        return nomContinentAng;
    }

    public void setGetNomContinentAng(String nomContinentAng) {
        this.nomContinentAng = nomContinentAng;
    }

    @Override
    public String toString() {
        return nomContinentFr;
    }
}
