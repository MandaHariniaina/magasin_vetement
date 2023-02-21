/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import framework.*;
import java.lang.reflect.Method;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author ACER
 */
public class AppeleurMethode {

    public HashMap methodCall(HttpServletRequest request) throws Exception {
        String url = (request.getRequestURI().split("/")[2]).split(".do")[0];
        HashMap retour = new HashMap();
        String nomMethode = "";
        String nomControlleur = "";
        String nomModele = "";
        if (url.contains("-")) {
            try {
                // rcuperation du nom de la methode et du controlleur
                nomMethode = url.split("-")[0];
                nomModele = url.split("-")[1];
                nomControlleur = formerNomControlleur(nomModele);

                // declaration de la l'objet de meme type que le controlleur
                Class classeCtlr = Class.forName(nomControlleur);
                Object ctlr = classeCtlr.newInstance();

                // recuperation des parametres
                HashMap parametres = null;
                try {
                    parametres = getParametres(request, nomModele);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                // recuperation de la methode
                Method methode = null;
                try {
                    methode = recupererMethode(nomMethode, classeCtlr, parametres);
                } catch (Exception ex) {
                    ex = new Exception("Methode non trouvé");
                    throw ex;
                }
                
                // appelation de la methode 
                try {
                    Object [] params = new Object[1];
                    params[0] = parametres;
                    if( methode.getParameterCount() == 0 ){
                        retour = (HashMap)methode.invoke(ctlr);
                    } else {
                        retour = (HashMap)methode.invoke(ctlr, params);
                    }
                }
                catch(Exception ex){
                    ex.printStackTrace();
                    //ex = new Exception("Exception survenue lors d'un appel de methode");
                    throw ex;
                }
                
            } catch (Exception ex) {
                throw ex;
            }
        } else {
            Exception ex = new Exception("Forme de l'url non valide");
            throw ex;
        }
        return retour;
    }
    
    private Method recupererMethode(String nomMethode, Class classeCtlr, HashMap params) throws Exception{
        Method retour = null;
        try {
            if(params == null){
                retour = classeCtlr.getDeclaredMethod(nomMethode);
            }
            else{
                Class [] typeParams = new Class[1];
                typeParams[0] = HashMap.class;
                retour = classeCtlr.getDeclaredMethod(nomMethode, typeParams);
            }
        }
        catch(Exception ex){
            ex = new Exception("Methode non trouvé");
            throw ex;
        }
        return retour;
    }

    
    private HashMap getParametres(HttpServletRequest request, String nomModele) throws Exception {
        Enumeration<String> listNomParam = request.getParameterNames();
        HashMap retour = null;
        String splitter = nomModele + ".";
        while (listNomParam.hasMoreElements()) {
            String nomParam = listNomParam.nextElement();
            if(nomParam.contains(".")){
                if(retour == null) retour = new HashMap();
                retour.put(nomParam.split(splitter)[1], request.getParameter(nomParam));
            }
        }
        return retour;
    }

    private String upperCaseFirstLetter(String mot) {
        char[] charNomClasse = mot.toCharArray();
        charNomClasse[0] = Character.toUpperCase(charNomClasse[0]);
        return new String(charNomClasse);
    }

    private String formerNomControlleur(String url) throws Exception {
        char[] charNomClasse = url.toCharArray();
        charNomClasse[0] = Character.toUpperCase(charNomClasse[0]);
        String retour = "controlleur." + new String(charNomClasse) + "Controller";
        return retour;
    }


    private Class classeAttribut(String attr, Class cls) throws Exception {
        Class retour = null;
        try {
            retour = cls.getDeclaredField(attr).getType();
        } catch (Exception ex) {
            ex = new Exception("Aucun attribut de ce nom");
        }
        return retour;
    }


}
