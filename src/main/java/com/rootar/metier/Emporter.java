package com.rootar.metier;

public class Emporter {

    private int idObjet;

    private int idPays;

    public Emporter(int idObjet, int idPays) {
        this.idObjet = idObjet;
        this.idPays = idPays;
    }

    public Emporter() {
    }

    public int getIdObjet() {
        return idObjet;
    }

    public void setIdObjet(int idObjet) {
        this.idObjet = idObjet;
    }

    public int getIdPays() {
        return idPays;
    }

    public void setIdPays(int idPays) {
        this.idPays = idPays;
    }
}
