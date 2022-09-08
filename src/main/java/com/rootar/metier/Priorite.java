package com.rootar.metier;

public class Priorite {
    private int idPriorite;
    private String libellePriorite;

    public Priorite(int idPriorite, String libellePriorite) {
        this.idPriorite = idPriorite;
        this.libellePriorite = libellePriorite;
    }

    public Priorite() {

    }

    public int getIdPriorite() {
        return idPriorite;
    }

    public void setIdPriorite(int idPriorite) {
        this.idPriorite = idPriorite;
    }

    public String getLibellePriorite() {
        return libellePriorite;
    }

    public void setLibellePriorite(String libellePriorite) {
        this.libellePriorite = libellePriorite;
    }
}
