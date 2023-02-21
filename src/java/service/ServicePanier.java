/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import modele.Panier;
import java.util.Vector;
import modele.Produit;
import base.DBConnection;
import java.sql.Connection;
/**
 *
 * @author Manda
 */
public class ServicePanier {

    public double getSommePanier(int idPanier) throws Exception{
        Panier panier = new Panier();
        panier = panier.select(idPanier);
        Vector<Panier> lPan = new Vector<Panier>(1,1);
        lPan.addElement(panier);
        Produit produit = selectProduit(lPan).get(0);
        return panier.getQuantite() * produit.getPrixUnitaire();
    }
    
    public double getPrixTotal (String email) throws Exception{
        Panier panier = new Panier();
        Vector<Panier> listePaniers = panier.selectParUtilisateur(email);
        Produit produit = new Produit();
        Vector<Produit> lProduits = new Vector<Produit>(1,1);
        Connection con = DBConnection.createConnection();
        for(int i = 0; i < listePaniers.size(); i++){
            lProduits.addElement(produit.select(listePaniers.get(i).getIdProduit(),con));
        }
        double retour = 0;
        for(int i = 0; i<listePaniers.size(); i++){
            retour += listePaniers.get(i).getQuantite() * lProduits.get(i).getPrixUnitaire();
        }
        return retour;
    }
    
    public void updateQuantite(int idPanier, int quantite) throws Exception{
        Panier panier = new Panier();
        panier = panier.select(idPanier);
        panier.setQuantite(quantite);
        panier.updateQuantite();
    }
    
    public void cancel(int idPanier) throws Exception{
        Panier panier = new Panier();
        panier.cancel(idPanier);
    }
    
    public void insert(String idUtilisateur, int idProduit) throws Exception{
        Panier panier = new Panier();
        panier.insert(idUtilisateur, idProduit);
    }
    
    public Vector<Produit> selectProduit(Vector<Panier> listePaniers) throws Exception{
        Produit produit = new Produit();
        Vector<Produit> retour = new Vector<Produit>(1,1);
        Connection con = DBConnection.createConnection();
        for(int i = 0; i < listePaniers.size(); i++){
            retour.addElement(produit.select(listePaniers.get(i).getIdProduit(),con));
        }
        return retour;
    }
    
    public Vector<Panier> selectAll(String idUser) throws Exception{
        Panier panier = new Panier();
        Vector<Panier> retour = panier.selectParUtilisateur(idUser);
        return retour;
    }

}
