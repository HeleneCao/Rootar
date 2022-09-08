package com.rootar.metier;

public class Evenements {

    private int idEvenements;

    private String libelleEvenements;

    private String dateDebutEvenements;

    private String dateFinEvenements;

    private String descriptionEvenements;

    private Ville ville;

    public Evenements() {
        this.idEvenements = idEvenements;
        this.libelleEvenements = libelleEvenements;
        this.dateDebutEvenements = dateDebutEvenements;
        this.dateFinEvenements = dateFinEvenements;
        this.descriptionEvenements = descriptionEvenements;
        ville = new Ville();
    }

    public Evenements(int idEvenements, String libelleEvenements) {
        this.idEvenements = idEvenements;
        this.libelleEvenements = libelleEvenements;
    }

    public Evenements(int idEvenements, String libelleEvenements, String dateDebutEvenements, String dateFinEvenements, String descriptionEvenements) {
        this.idEvenements = idEvenements;
        this.libelleEvenements = libelleEvenements;
        this.dateDebutEvenements = dateDebutEvenements;
        this.dateFinEvenements = dateFinEvenements;
        this.descriptionEvenements = descriptionEvenements;
    }

    public int getIdEvenements() {
        return idEvenements;
    }

    public void setIdEvenements(int idEvenements) {
        this.idEvenements = idEvenements;
    }

    public String getLibelleEvenements() {
        return libelleEvenements;
    }

    public void setLibelleEvenements(String libelleEvenements) {
        this.libelleEvenements = libelleEvenements;
    }

    public String getDateDebutEvenements() {
        return dateDebutEvenements;
    }

    public void setDateDebutEvenements(String dateDebutEvenements) {
        this.dateDebutEvenements = dateDebutEvenements;
    }

    public String getDateFinEvenements() {
        return dateFinEvenements;
    }

    public void setDateFinEvenements(String dateFinEvenements) {
        this.dateFinEvenements = dateFinEvenements;
    }

    public String getDescriptionEvenements() {
        return descriptionEvenements;
    }

    public void setDescriptionEvenements(String descriptionEvenements) {
        this.descriptionEvenements = descriptionEvenements;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return libelleEvenements;
    }
}
