package com.rootar.metier;

public class Themes {

    private int idThemes;
    private String libelleThemes;

    public Themes(int idThemes, String libelleThemes) {
        this.idThemes = idThemes;
        this.libelleThemes = libelleThemes;
    }

    public int getIdThemes() {
        return idThemes;
    }

    public void setIdThemes(int idThemes) {
        this.idThemes = idThemes;
    }

    public String getLibelleThemes() {
        return libelleThemes;
    }

    public void setLibelleThemes(String libelleThemes) {
        this.libelleThemes = libelleThemes;
    }
}
