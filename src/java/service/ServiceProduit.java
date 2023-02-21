/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import modele.Produit;
import java.util.Vector;
import base.DBConnection;
import java.sql.Connection;
import modele.Marque;
import modele.Categorie;
import java.io.File;

/**
 *
 * @author Manda
 */
public class ServiceProduit {

    public Vector<Produit> selectMeilleurVente() throws Exception{
        Produit modeleProduit = new Produit();
        Vector<Produit> retour = new Vector<Produit>(1,1);
        try{
            retour = modeleProduit.selectMeilleurVente();
        }
        catch(Exception ex){
            throw ex;
        }
        return retour;
    }
    
    public void update(int idProduit, String marque, String categorie, int stock, int prix) throws Exception{
        Produit produit = new Produit();
        produit = produit.select(idProduit);
        Marque mar = new Marque();
        int idMarque = mar.select(marque).getIdMarque();
        Categorie cat = new Categorie();
        int idCategorie = cat.select( categorie).getIdCategorie();
        produit.setIdMarque(idMarque);
        produit.setIdCategorie(idCategorie);
        produit.setStock(stock);
        produit.setPrixUnitaire(prix);
        produit.update();
    }
    
    public void delete(int idProduit, String filePath) throws Exception{
        Produit produit = new Produit();
        produit = produit.select(idProduit);
        produit.delete(idProduit);
        // enlever l'image;
        File file = new File(filePath+ "\\"+ produit.getImage());
        file.delete();
    }
    
    public String informationProduit(Produit produit) throws Exception{
        String retour = "Nom produit : "+produit.getNomProduit()+";\nCategorie : "+ categorie(produit) +";\nMarque : "+marque(produit)+";\nDate arrivage : " +produit.getDateArrivage();
        return retour;
    }
    
    public Produit select(int idProduit) throws Exception {
        Produit retour = new Produit();
        retour = retour.select(idProduit);
        retour.setImage("images/produits/" + retour.getImage());
        return retour;
    }

    public void insert(String nom, int marque, int categorie, String image, String description, int prix, int stock) throws Exception {
        Produit produit = new Produit();
        produit.insert(nom, marque, categorie, image, description, prix, stock);
    }

    private String reformater(String date) {
        String[] d = date.split("-");
        return d[2] + "/" + d[1] + "/" + d[0];
    }

    private String enleverWrap(String mot) {
        return mot.substring(0, mot.length() - 2).toLowerCase();
    }

    private String creerCondition(String recherche, int prixMin, int prixMax, String marque, String sexe, String categorie, String date) {
        String retour = " where ";
        int compteurCondition = 0;
        if (recherche.length() > 0) {
            retour = retour.concat(" lower(nom_produit) LIKE '%" + recherche.toLowerCase() + "%' or lower(description_produit) LIKE '%" + recherche.toLowerCase() + "%' ");;
            compteurCondition++;
        }
        if (compteurCondition > 0) {
            retour = retour.concat(" AND ");
        }
        retour = retour.concat("prix_unitaire > " + prixMin);
        compteurCondition++;
        if (prixMax > 0) {
            if (compteurCondition > 0) {
                retour = retour.concat(" AND ");
            }
            retour = retour.concat("prix_unitaire < " + prixMax);
            compteurCondition++;
        }
        if (marque.compareToIgnoreCase("tous") != 0) {
            if (compteurCondition > 0) {
                retour = retour.concat(" AND ");
            }
            retour = retour.concat("libele_marque = '" + marque + "'");
            compteurCondition++;
        }
        if (categorie.compareToIgnoreCase("tous") != 0) {
            if (compteurCondition > 0) {
                retour = retour.concat(" AND ");
            }
            retour = retour.concat(" libele_categorie ='" + categorie + "'");
            compteurCondition++;
        }
        if (date.compareToIgnoreCase("") != 0) {
            if (compteurCondition > 0) {
                retour = retour.concat(" AND ");
            }
            retour = retour.concat("date_arrivage > '" + reformater(date) + "'");
        }
        return retour;
    }

    public Vector<Produit> select(String recherche, int prixMin, int prixMax, String marque, String sexe, String categorie, String date) throws Exception {
        Vector<Produit> retour = null;
        String requete = creerCondition(recherche, prixMin, prixMax, marque, sexe, categorie, date);
        Produit produit = new Produit();
        retour = produit.execute(requete);
        return retour;
    }

    public Vector<Marque> marques() throws Exception {
        Marque marque = new Marque();
        Vector<Marque> retour = marque.selectAll();
        return retour;
    }

    public int countAll() throws Exception {
        Produit p = new Produit();
        return p.countAll();
    }

    public Vector<Produit> selectAll() throws Exception{
        Produit p = new Produit();
        Vector<Produit> retour = new Vector<Produit>(1, 1);
        retour = p.selectAll();
        return retour;
    }
    
    public Vector<Produit> selectAll(int page) throws Exception {
        Produit p = new Produit();
        Vector<Produit> retour = new Vector<Produit>(1, 1);
        retour = p.selectAll(page);
        return retour;
    }

    public Vector<String> imagesProduits(Vector<Produit> lProd) throws Exception {
        Vector<String> retour = new Vector<String>(1, 1);
        for (int i = 0; i < lProd.size(); i++) {
            retour.addElement("images/produits/" + lProd.get(i).getImage());
        }
        return retour;
    }

    public Vector<String> descriptionProduits(Vector<Produit> lProd) throws Exception {
        Vector<String> retour = new Vector<String>(1, 1);

        for (int i = 0; i < lProd.size(); i++) {
            retour.addElement(lProd.get(i).description(DBConnection.createConnection()));
        }
        return retour;
    }
    
    public String categorie(Produit produit) throws Exception{
        String retour = "";
        Connection con = null;
        try {
            con = DBConnection.createConnection();
            retour = produit.categorie(con);
        } catch (Exception ex) {

        } finally {
            con.close();
        }
        return retour;
    }

    public String marque(Produit produit) throws Exception{
        String retour = "";
        Connection con = null;
        try {
            con = DBConnection.createConnection();
            retour = produit.marque(con);
        } catch (Exception ex) {

        } finally {
            con.close();
        }
        return retour;
    }

    public Vector<String> marqueParProduit(Vector<Produit> lProd) throws Exception {
        Vector<String> retour = new Vector<String>(1, 1);
        Connection con = null;
        try {
            con = DBConnection.createConnection();
            for (int i = 0; i < lProd.size(); i++) {
                retour.addElement(lProd.get(i).marque(con));
            }
        } catch (Exception ex) {

        } finally {
            con.close();
        }
        return retour;
    }

    public Vector<Categorie> categories() throws Exception {
        Categorie categorie = new Categorie();
        Vector<Categorie> retour = categorie.selectAll();
        return retour;
    }

    public Vector<String> categorieParProduit(Vector<Produit> lProd) throws Exception {
        Vector<String> retour = new Vector<String>(1, 1);
        Connection con = null;
        try {
            con = DBConnection.createConnection();
            for (int i = 0; i < lProd.size(); i++) {
                retour.addElement(lProd.get(i).categorie(con));
            }
        } catch (Exception ex) {

        } finally {
            con.close();
        }
        return retour;
    }
}
