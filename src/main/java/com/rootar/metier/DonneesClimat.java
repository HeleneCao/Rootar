package com.rootar.metier;

public class DonneesClimat {

    private int idRegion ;

    private int mois;

    private String libelleMois;

    private float temperatureMin;

    private float temperatureMax;

    private float temperatureMoy;

    private int tauxHumidite;


    public DonneesClimat() {

    }

    public DonneesClimat( int mois, String libelleMois) {

        this.mois = mois;
        this.libelleMois = libelleMois;
    }

    public DonneesClimat(int idRegion,int mois, String libelleMois, float temperatureMin, float temperatureMax, float temperatureMoy, int tauxHumidite) {
        this.idRegion=idRegion;
        this.mois = mois;
        this.libelleMois = libelleMois;
        this.temperatureMin = temperatureMin;
        this.temperatureMax = temperatureMax;
        this.temperatureMoy = temperatureMoy;
        this.tauxHumidite = tauxHumidite;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public String getLibelleMois() {
        return libelleMois;
    }

    public void setLibelleMois(String libelleMois) {
        this.libelleMois = libelleMois;
    }

    public float getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(float temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public float getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(float temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public float getTemperatureMoy() {
        return temperatureMoy;
    }

    public void setTemperatureMoy(float temperatureMoy) {
        this.temperatureMoy = temperatureMoy;
    }

    public int getTauxHumidite() {
        return tauxHumidite;
    }

    public void setTauxHumidite(int tauxHumidite) {
        this.tauxHumidite = tauxHumidite;
    }
}
