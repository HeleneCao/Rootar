package com.rootar.metier;

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

    public Pays(int idPays, String nomPaysFr) {
        this.idPays = idPays;
        this.nomPaysFr = nomPaysFr;
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
}
