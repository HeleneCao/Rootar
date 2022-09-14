package com.rootar.metier;

public class Parler {

    private int idPays;
    private int idLangues;

    public Parler(int idPays, int idLangues) {
        this.idPays = idPays;
        this.idLangues = idLangues;
    }

    public Parler() {
    }

    public int getIdPays() {
        return idPays;
    }

    public void setIdPays(int idPays) {
        this.idPays = idPays;
    }

    public int getIdLangues() {
        return idLangues;
    }

    public void setIdLangues(int idLangues) {
        this.idLangues = idLangues;
    }
}
