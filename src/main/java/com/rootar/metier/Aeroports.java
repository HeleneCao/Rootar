package com.rootar.metier;

public class Aeroports {

    private int idAeroport;
    private String libelleAeroport;
    private String adresse;
    private String telephone;

    public Aeroports() {
    }

    public Aeroports(int idAeroport, String libelleAeroport, String adresse, String telephone) {
        this.idAeroport = idAeroport;
        this.libelleAeroport = libelleAeroport;
        this.adresse = adresse;
        this.telephone = telephone;
    }

    public int getIdAeroport() {
        return idAeroport;
    }

    public void setIdAeroport(int idAeroport) {
        this.idAeroport = idAeroport;
    }

    public String getLibelleAeroport() {
        return libelleAeroport;
    }

    public void setLibelleAeroport(String libelleAeroport) {
        this.libelleAeroport = libelleAeroport;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return libelleAeroport ;
    }
}
