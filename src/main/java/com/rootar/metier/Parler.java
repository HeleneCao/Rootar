package com.rootar.metier;

public class Parler {

    private int idPays;
    private String idLangues;

    public Parler(int idPays, String idLangues) {
        this.idPays = idPays;
        this.idLangues = idLangues;
    }

    public int getIdPays() {
        return idPays;
    }

    public void setIdPays(int idPays) {
        this.idPays = idPays;
    }

    public String getIdLangues() {
        return idLangues;
    }

    public void setIdLangues(String idLangues) {
        this.idLangues = idLangues;
    }
}
