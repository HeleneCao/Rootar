package com.rootar.dao;

import com.rootar.metier.RepresentationEtrangere;

import java.sql.Connection;

public class DAOFactory {

    public DAOFactory() {
    }

    private static final Connection connexion = RootarConnect.getInstance();
    public static CategoriesDAO getCategoriesDAO(){
        return new CategoriesDAO(connexion);
    }
    public static ContinentDAO getContinentDAO(){
        return new ContinentDAO(connexion);
    }
    public static DonneesClimatDAO getDonneesClimatDAO(){
        return new DonneesClimatDAO(connexion);
    }
    public static EvenementsDAO getEvenementsDAO(){
        return new EvenementsDAO(connexion);
    }
    public static LanguesDAO getLanguesDAO(){
        return new LanguesDAO(connexion);
    }
    public static MonnaieDAO getMonnaieDAO(){
        return new MonnaieDAO(connexion);
    }
    public static ObjetDAO getObjetDAO(){
        return new ObjetDAO(connexion);
    }
    public static PaysDAO getPaysDAO(){
        return new PaysDAO(connexion);
    }
    public static PrioriteDAO getPrioriteDAO(){
        return new PrioriteDAO(connexion);
    }
    public static RegionDAO getRegionDAO(){
        return new RegionDAO(connexion);
    }
    public static RepresentationEtrangereDAO getRepresentationEtrangereDAO(){
        return new RepresentationEtrangereDAO(connexion);
    }
    public static SanteDAO getSanteDAO(){
        return new SanteDAO(connexion);
    }
    public static ThemesDAO getThemesDAO(){
        return new ThemesDAO(connexion);
    }
    public static TypeClimatDAO getTypeClimatDAO(){
        return new TypeClimatDAO(connexion);
    }
    public static VilleDAO getVilleDAO(){
        return new VilleDAO(connexion);
    }
    public static VisasDAO getTypeVisasDAO(){
        return new VisasDAO(connexion);
    }
    public static ParlerDAO getParlerDAO(){
        return new ParlerDAO(connexion);
    }
    public static ExigerDAO getExigerDAO(){
        return new ExigerDAO(connexion);
    }
    public static EmporterDAO getEmpoterDAO() { return new EmporterDAO(connexion);}
    public static  AeroportDAO getAeroportDAO() { return  new AeroportDAO(connexion);}
}
