package com.rootar.metier;

import java.util.ArrayList;

public class Continent {

    private int idContinent;
    private String nomContinentFr;
    private String nomContinentAng;
    private ArrayList<Pays> pays;

    public Continent() {

    }

    public Continent(int idContinent, String nomContinentFr) {
        this.idContinent = idContinent;
        this.nomContinentFr = nomContinentFr;
        pays=new ArrayList<>();
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

    public ArrayList<Pays> getPays() {
        return pays;
    }

    @Override
    public String toString() {
        return nomContinentFr;
    }
}
