package com.rootar.metier;

public class TypeVisas {

    private int idTypeVisas;
    private String libelleTypeVisas;

    public TypeVisas(int idTypeVisas, String libelleTypeVisas) {
        this.idTypeVisas = idTypeVisas;
        this.libelleTypeVisas = libelleTypeVisas;
    }

    public int getIdTypeVisas() {
        return idTypeVisas;
    }

    public void setIdTypeVisas(int idTypeVisas) {
        this.idTypeVisas = idTypeVisas;
    }

    public String getLibelleTypeVisas() {
        return libelleTypeVisas;
    }

    public void setLibelleTypeVisas(String libelleTypeVisas) {
        this.libelleTypeVisas = libelleTypeVisas;
    }
}
