package com.rootar.metier;

public class Sante {
    private int idSante;
    private String libelleSante;

    public Sante(int idSante, String libelleSante) {
        this.idSante = idSante;
        this.libelleSante = libelleSante;
    }

    public int getIdSante() {
        return idSante;
    }

    public void setIdSante(int idSante) {
        this.idSante = idSante;
    }

    public String getLibelleSante() {
        return libelleSante;
    }

    public void setLibelleSante(String libelleSante) {
        this.libelleSante = libelleSante;
    }
}
