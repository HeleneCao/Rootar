package com.rootar.metier;

public class
Sante {
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

        priorite =new Priorite();
    }

    public Sante(int idSante, String libelleSante, Priorite priorite) {
        this.idSante = idSante;
        this.libelleSante = libelleSante;

        this.priorite = priorite;
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

    public Boolean getValidite() {
        return validite;
    }

    public void setValidite(Boolean validite) {
        this.validite = validite;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    @Override
    public String toString() {
        return libelleSante;
    }
}
