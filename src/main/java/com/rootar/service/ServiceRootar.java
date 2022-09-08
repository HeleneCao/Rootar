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

    public Categories getCategoriesFilterbyObject(Objet objet){
        return DAOFactory.getCategoriesDAO().getCategoriesByObjet(objet);
    }
    public ArrayList<Pays> getPaysFiltre()
    {
        return paysFiltre;
    }
    public ArrayList<Themes> getThemesByPays(Pays pays){
        return DAOFactory.getThemesDAO().getThemesByPays(pays);
    }
    public ArrayList<Themes> getThemesByRegion(Region region){
        return DAOFactory.getThemesDAO().getThemesByRegion(region);
    }
    public ArrayList<RepresentationEtrangere> getRepEtrangeresByPays(Pays pays){
        return DAOFactory.getRepresentationEtrangereDAO().getRepEtrangeresByPays(pays);
    }
    public ArrayList<RepresentationEtrangere> getRepEtrangeresByVille(Ville ville){
        return DAOFactory.getRepresentationEtrangereDAO().getRepEtrangeresByVille(ville);
    }

    public ArrayList<Continent> getContinentFiltre()
    {
        return continentFiltre;
    }
    public ArrayList<Monnaie> getMonnaieFiltre()
    {
        return DAOFactory.getMonnaieDAO().getAll();
    }

    public ArrayList<Objet> getObjetFilterByPays(Pays pays){
        return  DAOFactory.getObjetDAO().getObjetByPays(pays);
    }
    public boolean insertPays(Pays pays) {
        return DAOFactory.getPaysDAO().insert(pays);
    }
    public boolean updatePays(Pays pays) {
        return true;
    }
    public boolean deletePays(Pays pays) {
        return DAOFactory.getPaysDAO().delete(pays);
    }

}
