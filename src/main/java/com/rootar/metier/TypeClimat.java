package com.rootar.metier;

public class TypeClimat {

    private int idTypeClimat;
    private String libelleTypeClimat;

    public TypeClimat(int idTypeClimat, String libelleTypeClimat) {
        this.idTypeClimat = idTypeClimat;
        this.libelleTypeClimat = libelleTypeClimat;
    }

    public TypeClimat() {
    }

    public int getIdTypeClimat() {
        return idTypeClimat;
    }

    public void setIdTypeClimat(int idTypeClimat) {
        this.idTypeClimat = idTypeClimat;
    }

    public String getLibelleTypeClimat() {
        return libelleTypeClimat;
    }

    public void setLibelleTypeClimat(String libelleTypeClimat) {
        this.libelleTypeClimat = libelleTypeClimat;
    }
}
