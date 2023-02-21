/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author ACER
 */
public class AttributeurHashMap {

    public static void attribuer(HashMap donnees, Object objet) throws Exception {
        try {
            // parcrous des attributs de l'objet
            Field[] attributs = objet.getClass().getDeclaredFields();
            for (int i = 0; i < attributs.length; i++) {
                Object valeur = donnees.get(attributs[i].getName());
                if (valeur != null) {
                    ajouterValeur(objet, valeur, attributs[i]);
                }
            }
        } catch (Exception ex) {
            ex = new Exception("Aucune valeur assigné à l'objet");
            throw ex;
        }
    }

    private static void ajouterValeur(Object objet, Object valeur, Field attribut) throws Exception {
        Class classeObjet = objet.getClass();

        // recuperation du settter de l'attribut
        Method setter = null;
        try {
            setter = recupererSetter(classeObjet, attribut);
        } catch (Exception ex) {
            ex = new Exception("Setter non trouvée");
            throw ex;
        }

        // assignation de la valeur
        try {
            Object[] param = new Object[1];
            param[0] = castValeur(valeur, attribut, objet);
            Object retourFonction = setter.invoke(objet, param);
        } catch (Exception ex) {
            ex.printStackTrace();
            ex = new Exception("Valeur non assigné");
            throw ex;
        }
    }
    
    private static Object castValeur(Object valeur, Field attribut, Object objet) throws Exception{
        Object retour = null;
        Class classeAttribut = attribut.getType();
        if(classeAttribut == int.class){
            return Integer.valueOf(String.valueOf(valeur));
        }
        retour = valeur;
        return retour;
    }

    private static Method recupererSetter(Class classeObjet, Field attribut) throws Exception {
        Method retour = null;
        try {
            Class [] classParams = new Class[1];
            classParams[0] = attribut.getType();
            String nomMethode = "set" + upperCaseFirstLetter(attribut.getName());
            retour = classeObjet.getDeclaredMethod(nomMethode, classParams);
        } catch (Exception ex) {
            ex.printStackTrace();
            ex = new Exception("Methode non trouvé");
            throw ex;
        }
        return retour;
    }

    
    private static String upperCaseFirstLetter(String mot) {
        char[] charNomClasse = mot.toCharArray();
        charNomClasse[0] = Character.toUpperCase(charNomClasse[0]);
        return new String(charNomClasse);
    }
    
}
