package com.rootar.metier;

public class Sante {
    private int idSante;
    private String libelleSante;
    private Boolean validite;

    private Priorite priorite;

    public Sante(int idSante, String libelleSante) {
        this.idSante = idSante;
        this.libelleSante = libelleSante;
        priorite = new Priorite();
    }

    public Sante(int idSante, String libelleSante, Boolean validite) {
        this.idSante = idSante;
        this.libelleSante = libelleSante;
        this.validite = validite;
    }

    public Sante() {
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
