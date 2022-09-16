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
    public Sante getByIDSante(int id){
        return DAOFactory.getSanteDAO().getByID(id);
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
    public ArrayList<Priorite> getAllPriorite(){
        return DAOFactory.getPrioriteDAO().getAll();
    }
    public TypeClimat getTypeClimatFilterByRegion(Region region){
        return DAOFactory.getTypeClimatDAO().getTypeClimatByRegion(region);
    }
    public Priorite getPrioriteBySante(int id){
        return DAOFactory.getPrioriteDAO().getPrioriteBySante(id);
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
    public ArrayList<Pays> getAllPays(){return DAOFactory.getPaysDAO().getAll();}
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
    public ArrayList<Langues> getLanguesByPays(Pays pays){
       return DAOFactory.getLanguesDAO().getLanguesByPays(pays);
    }
    public ArrayList<Langues> getAll(){
        return DAOFactory.getLanguesDAO().getAll();
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
    public ArrayList<Sante> getSantebyPays(Pays pays){
        return DAOFactory.getSanteDAO().getSanteByPays(pays);
    }
    public boolean insertPays(Pays pays) {
        return DAOFactory.getPaysDAO().insert(pays);
    }
    public boolean updatePays(Pays pays) {
        return DAOFactory.getPaysDAO().update(pays);
    }
    public boolean deletePays(Pays pays) {
        return DAOFactory.getPaysDAO().delete(pays);
    }
    public boolean insertVille(Ville ville) {
        return DAOFactory.getVilleDAO().insert(ville);
    }
    public boolean updateVille(Ville ville) {
        return DAOFactory.getVilleDAO().update(ville);
    }
    public boolean deleteVille(Ville ville) { return DAOFactory.getVilleDAO().delete(ville);}

    public boolean insertEvent(Evenements evenements) {return DAOFactory.getEvenementsDAO().insert(evenements);
    }
    public boolean updateEvent(Evenements evenements) {
        return DAOFactory.getEvenementsDAO().update(evenements);
    }
    public boolean deleteEvent(Evenements evenements) { return DAOFactory.getEvenementsDAO().delete(evenements);}
    public boolean insertParler(Parler parler){
        return DAOFactory.getParlerDAO().insert(parler);
    }
    public boolean deleteParler(Parler parler){
        return DAOFactory.getParlerDAO().delete(parler);
    }
    public boolean insertCategories(Categories categories){
        return DAOFactory.getCategoriesDAO().insert(categories);
    }
    public boolean updateCategories(Categories categories){
        return DAOFactory.getCategoriesDAO().update(categories);
    }
    public boolean deleteCategories(Categories categories){
        return DAOFactory.getCategoriesDAO().delete(categories);
    }

    public boolean insertSante(Sante sante){
        return DAOFactory.getSanteDAO().insert(sante);
    }
    public boolean updateSante(Sante sante){
        return DAOFactory.getSanteDAO().update(sante);
    }
    public boolean deleteSante(Sante sante){
        return DAOFactory.getSanteDAO().delete(sante);
    }
    public boolean deleteExiger(Exiger exiger){
        return DAOFactory.getExigerDAO().delete(exiger);
    }

}
