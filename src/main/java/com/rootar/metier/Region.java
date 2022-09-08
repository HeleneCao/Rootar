package com.rootar.metier;

public class Region {
    private int idRegion;
    private String nomRegion;
    private Pays pays;
    private TypeClimat typeClimat;

    public Region(int idRegion, String nomRegion) {
        this.idRegion = idRegion;
        this.nomRegion = nomRegion;
        pays = new Pays();
        typeClimat = new TypeClimat();
    }

    public Region() {
    }

    public Region(int idRegion, String nomRegion, TypeClimat typeClimat) {
        this.idRegion = idRegion;
        this.nomRegion = nomRegion;
        this.typeClimat = typeClimat;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public String getNomRegion() {
        return nomRegion;
    }

    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    @Override
    public String toString() {
        return  nomRegion ;
    }
}
