package com.rootar.metier;

public class Visas {


        //zez

        private int idVisas;
        private int dureeValidite;
        private int delaiObtention;
        private Float prixVisas;


    public Visas(int idVisas, int dureeValidite, int delaiObtention, Float prixVisas) {
        this.idVisas = idVisas;
        this.dureeValidite = dureeValidite;
        this.delaiObtention = delaiObtention;
        this.prixVisas = prixVisas;
    }

    public int getIdVisas() {
        return idVisas;
    }

    public void setIdVisas(int idVisas) {
        this.idVisas = idVisas;
    }

    public int getDureeValidite() {
        return dureeValidite;
    }

    public void setDureeValidite(int dureeValidite) {
        this.dureeValidite = dureeValidite;
    }

    public int getDelaiObtention() {
        return delaiObtention;
    }

    public void setDelaiObtention(int delaiObtention) {
        this.delaiObtention = delaiObtention;
    }

    public Float getPrixVisas() {
        return prixVisas;
    }

    public void setPrixVisas(Float prixVisas) {
        this.prixVisas = prixVisas;
    }
}
