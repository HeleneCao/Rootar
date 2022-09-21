package com.rootar.metier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pays {
    private int idPays;
    private String nomPaysFr;
    private String codePays;
    private String nomPaysAng;
    private String nationalite;
    private String capitale;
    private int superficie;
    private int nbreHabitant;
    private String devise;
    private String feteNationale;
    private String indicatifTel;
    private Continent continent;
    private Monnaie monnaie;
    private Visas visas;


    public Pays() {
    }

    public Pays(int idPays, String nomPaysFr) {
        this.idPays = idPays;
        this.nomPaysFr = nomPaysFr;
        continent = new Continent();
        monnaie = new Monnaie();
        visas = new Visas();
    }

    public Pays(int idPays, String codePays,String nomPaysFr,  String nomPaysAng, String nationalite, String capitale,int nbreHabitant,  int superficie, String devise, String feteNationale, String indicatifTel,Continent continent,Monnaie monnaie) {
        this.idPays = idPays;
        this.nomPaysFr = nomPaysFr;
        this.codePays = codePays;
        this.nomPaysAng = nomPaysAng;
        this.nationalite = nationalite;
        this.capitale = capitale;
        this.nbreHabitant = nbreHabitant;
        this.superficie = superficie;
        this.devise = devise;
        this.feteNationale = feteNationale;
        this.indicatifTel = indicatifTel;
        this.continent=continent;
        this.monnaie=monnaie;
    }

    public Pays(int idPays, String nomPaysFr, Continent continent) {
        this.idPays = idPays;
        this.nomPaysFr = nomPaysFr;
        this.continent = continent;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Monnaie getMonnaie() {
        return monnaie;
    }

    public void setMonnaie(Monnaie monnaie) {
        this.monnaie = monnaie;
    }

    public Visas getVisas() {
        return visas;
    }

    public void setVisas(Visas visas) {
        this.visas = visas;
    }

    public StringProperty codePaysProperty()
    {
        return new SimpleStringProperty(codePays);
    }
    public StringProperty nomPaysFrProperty()
    {
        return new SimpleStringProperty(nomPaysFr);
    }
    public StringProperty nomContinentProperty()
    {
        return new SimpleStringProperty(this.continent.getNomContinentFr());
    }
    public StringProperty libelleMonnaieProperty()
    {
        return new SimpleStringProperty(this.monnaie.getLibelleMonnaie());
    }
    public StringProperty nationaliteProperty()
    {
        return new SimpleStringProperty(nationalite);
    }
    public int getIdPays() {
        return idPays;
    }

    public void setIdPays(int idPays) {
        this.idPays = idPays;
    }

    public String getNomPaysFr() {
        return nomPaysFr;
    }

    public void setNomPaysFr(String nomPaysFr) {
        this.nomPaysFr = nomPaysFr;
    }

    public String getCodePays() {
        return codePays;
    }

    public void setCodePays(String codePays) {
        this.codePays = codePays;
    }

    public String getNomPaysAng() {
        return nomPaysAng;
    }

    public void setNomPaysAng(String nomPaysAng) {
        this.nomPaysAng = nomPaysAng;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getCapitale() {
        return capitale;
    }

    public void setCapitale(String capitale) {
        this.capitale = capitale;
    }

    public int getSuperficie() {
        return superficie;
    }

    public void setSuperficie(int superficie) {
        this.superficie = superficie;
    }

    public int getNbreHabitant() {
        return nbreHabitant;
    }

    public void setNbreHabitant(int nbreHabitant) {
        this.nbreHabitant = nbreHabitant;
    }

    public String getDevise() {
        return devise;
    }

    public void setDevise(String devise) {
        this.devise = devise;
    }

    public String getFeteNationale() {
        return feteNationale;
    }

    public void setFeteNationale(String feteNationale) {
        this.feteNationale = feteNationale;
    }

    public String getIndicatifTel() {
        return indicatifTel;
    }

    public void setIndicatifTel(String indicatifTel) {
        this.indicatifTel = indicatifTel;
    }

    @Override
    public String toString() {
        return nomPaysFr;
    }
}
