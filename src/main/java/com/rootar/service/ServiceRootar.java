package com.rootar.service;

import com.rootar.dao.DAOFactory;
import com.rootar.metier.*;

import java.util.ArrayList;

public class ServiceRootar {

    private ArrayList<Pays> paysFiltre;
    private ArrayList<Continent> continentFiltre;

    public ServiceRootar() {
        paysFiltre = DAOFactory.getPaysDAO().getAll();
        continentFiltre = DAOFactory.getContinentDAO().getAll();
    }
    public ArrayList<Pays> getFilteredPays()
    {
        return DAOFactory.getPaysDAO().getAll();

    }
    public Parler getFilteredParler(int id)
    {
        return DAOFactory.getParlerDAO().getAllByID(id);

    }
    public Langues getLanguesFilter(String id){
        return DAOFactory.getLanguesDAO().getAllByID(id);
    }
    public ArrayList<Region> getRegionFilterByPays(Pays pays){
        return DAOFactory.getRegionDAO().getRegionByPays(pays);
    }
    public ArrayList<Ville> getVilleFilterByRegion(Region region){
        return DAOFactory.getVilleDAO().getVilleByRegion(region);
    }
    public ArrayList<Evenements> getEventFilterByVille(Ville ville){
        return DAOFactory.getEvenementsDAO().getEventByVille(ville);
    }
    public TypeClimat getTypeClimatFilterByRegion(Region region){
        return DAOFactory.getTypeClimatDAO().getTypeClimatByRegion(region);
    }
    public Priorite getPrioriteFilterBySante(Sante sante){
        return DAOFactory.getPrioriteDAO().getPrioriteBySante(sante);
    }
    public ArrayList<DonneesClimat> getDonneesClimatByRegion(Region region){
        return DAOFactory.getDonneesClimatDAO().getDonneesClimatByRegion(region);
    }
    public ArrayList<Pays> getPaysFiltre()
    {
        return paysFiltre;
    }

    public ArrayList<Continent> getContinentFiltre()
    {
        return continentFiltre;
    }

    public boolean insertArticle(Pays pays) {
        return true;
    }
    public boolean updateArticle(Pays pays) {
        return true;
    }
    public boolean deleteArticle(Pays pays) {
        return true;
    }

}
