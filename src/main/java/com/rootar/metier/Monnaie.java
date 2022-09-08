package com.rootar.metier;

public class Monnaie {

    private int idMonnaie;

    private String libelleMonnaie;

    public Monnaie() {
    }

    public Monnaie(int idMonnaie, String libelleMonnaie) {
        this.idMonnaie = idMonnaie;
        this.libelleMonnaie = libelleMonnaie;
    }

    public int getIdMonnaie() {
        return idMonnaie;
    }

    public void setIdMonnaie(int idMonnaie) {
        this.idMonnaie = idMonnaie;
    }

    public String getLibelleMonnaie() {
        return libelleMonnaie;
    }

    public void setLibelleMonnaie(String libelleMonnaie) {
        this.libelleMonnaie = libelleMonnaie;
    }
}
