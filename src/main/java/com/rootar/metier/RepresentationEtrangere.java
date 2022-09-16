package com.rootar.metier;

public class RepresentationEtrangere {

    private int idRepEtrangere;
    private String libelleRepEtrangere;
    private String telephone;
    private String adresse;

    private Pays pays;

    private Ville ville;


    public RepresentationEtrangere() {
    }

    public RepresentationEtrangere(int idRepEtrangere, String libelleRepEtrangere) {
        this.idRepEtrangere = idRepEtrangere;
        this.libelleRepEtrangere = libelleRepEtrangere;
        pays = new Pays();
        ville = new Ville();
    }

    public RepresentationEtrangere(int idRepEtrangere, String libelleRepEtrangere, String telephone, String adresse) {
        this.idRepEtrangere = idRepEtrangere;
        this.libelleRepEtrangere = libelleRepEtrangere;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    public RepresentationEtrangere(int idRepEtrangere, String libelleRepEtrangere, String telephone, String adresse, Pays pays, Ville ville) {
        this.idRepEtrangere = idRepEtrangere;
        this.libelleRepEtrangere = libelleRepEtrangere;
        this.telephone = telephone;
        this.adresse = adresse;
        this.pays = pays;
        this.ville = ville;
    }

    public int getIdRepEtrangere() {
        return idRepEtrangere;
    }

    public void setIdRepEtrangere(int idRepEtrangere) {
        this.idRepEtrangere = idRepEtrangere;
    }

    public String getLibelleRepEtrangere() {
        return libelleRepEtrangere;
    }

    public void setLibelleRepEtrangere(String libelleRepEtrangere) {
        this.libelleRepEtrangere = libelleRepEtrangere;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return libelleRepEtrangere ;
    }
}
