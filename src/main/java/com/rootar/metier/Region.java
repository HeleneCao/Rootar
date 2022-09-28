package com.rootar.metier;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class Region {
    private int idRegion;
    private String nomRegion;
    private Pays pays;
    private TypeClimat typeClimat;
    private ArrayList<Pays> arrayListpays;

    public Region(int idRegion, String nomRegion) {
        this.idRegion = idRegion;
        this.nomRegion = nomRegion;
        pays = new Pays();
        typeClimat = new TypeClimat();
        arrayListpays = new ArrayList<>();
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
    public StringProperty nomRegionProperty()
    {
        return new SimpleStringProperty(nomRegion);
    }
    public void setNomRegion(String nomRegion) {
        this.nomRegion = nomRegion;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public TypeClimat getTypeClimat() {
        return typeClimat;
    }

    public void setTypeClimat(TypeClimat typeClimat) {
        this.typeClimat = typeClimat;
    }

    public ArrayList<Pays> getArrayListpays() {
        return arrayListpays;
    }

    public void setArrayListpays(ArrayList<Pays> arrayListpays) {
        arrayListpays = arrayListpays;
    }

    @Override
    public String toString() {
        return  nomRegion ;
    }
}
