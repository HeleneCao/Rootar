package com.rootar.metier;

public class Exiger {
    private Pays pays;
    private Sante sante;

    public Exiger(Pays pays, Sante sante) {
        this.pays = pays;
        this.sante = sante;
    }

    public Exiger() {
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Sante getSante() {
        return sante;
    }

    public void setSante(Sante sante) {
        this.sante = sante;
    }
}
