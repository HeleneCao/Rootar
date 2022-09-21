package com.rootar.metier;

public class Posseder {

    private Pays pays;
    private Aeroports aeroports;

    public Posseder(Pays pays, Aeroports aeroports) {
        this.pays = pays;
        this.aeroports = aeroports;
    }

    public Posseder() {
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Aeroports getAeroports() {
        return aeroports;
    }

    public void setAeroports(Aeroports aeroports) {
        this.aeroports = aeroports;
    }
}
