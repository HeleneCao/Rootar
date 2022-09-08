package com.rootar.metier;

public class Langues {

    private int idLangues;

    private String libelleLangues;

    public Langues() {
    }

    public Langues(int idLangues, String libelleLangues) {
        this.idLangues = idLangues;
        this.libelleLangues = libelleLangues;
    }

    public int getIdLangues() {
        return idLangues;
    }

    public void setIdLangues(int idLangues) {
        this.idLangues = idLangues;
    }

    public String getLibelleLangues() {
        return libelleLangues;
    }

    public void setLibelleLangues(String libelleLangues) {
        this.libelleLangues = libelleLangues;
    }

    @Override
    public String toString() {
        return libelleLangues;
    }
}

