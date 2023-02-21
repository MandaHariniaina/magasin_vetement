/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import modele.Panier;
import modele.Achat;
import modele.Produit;
import java.util.Vector;
import base.DBConnection;
import java.sql.Connection;
import modele.Utilisateur;

/**
 *
 * @author Manda
 */
public class ServiceAchat {

    public void transactionAchat(Vector<Panier> listePaniers) throws Exception {
        Connection con = null;
        try {
            con = DBConnection.createConnection();
            verifArgentUtilisateur(listePaniers, con);                       // verification argent utilisateur
            effectuerAchat(listePaniers, con);                              // ajout des liste d'achats
            validerPanier(listePaniers, con);                               // valider Panier
            reduireStock(listePaniers, con);
            con.commit();
            con.close();
        } catch (Exception ex) {
            //ex.printStackTrace();
            con.rollback();
        } finally {
            con.close();
        }
    }
    
    private void reduireStock(Vector<Panier> listePaniers,Connection con) throws Exception{
        for (int i = 0; i < listePaniers.size(); i++) {
            Produit p = new Produit();
            p = p.select(listePaniers.get(i).getIdProduit());
            p.setStock(p.getStock() - listePaniers.get(i).getQuantite());
            p.update(con);
        }
    }

    private void validerPanier(Vector<Panier> listePaniers, Connection con) throws Exception {
        for (int i = 0; i < listePaniers.size(); i++) {
            listePaniers.get(i).insertIntoPanierValide(listePaniers.get(i), con);
        }
    }

    private Vector<Panier> getListePanier(Vector<String> lIdPan, Connection con) throws Exception {
        Vector<Panier> retour = new Vector<Panier>(1, 1);
        Panier pan = new Panier();
        for (int i = 0; i < lIdPan.size(); i++) {
            pan = pan.select(lIdPan.get(i), con);
            retour.addElement(pan);
        }
        return retour;
    }

    private void verifArgentUtilisateur(Vector<Panier> lPan, Connection con) throws Exception {
        double montant = 0;
        Produit produit = new Produit();
        for (int i = 0; i < lPan.size(); i++) {
            int prixUnitaire = produit.select(lPan.get(i).getIdProduit(), con).getPrixUnitaire();
            montant += prixUnitaire * lPan.get(i).getQuantite();
        }
        Utilisateur util = new Utilisateur();
        util = util.select(lPan.get(0).getIdUtilisateur());
        if (montant > util.getArgent()) {
            Exception ex = new Exception("Argent insuffisant");
            throw ex;
        }
        else{
            double nouveauMontant = util.getArgent() - montant;
            util.setArgent( nouveauMontant );
            util.update(con);
        }
    }

    private void effectuerAchat(Vector<Panier> lPan, Connection con) throws Exception {
        for (int i = 0; i < lPan.size(); i++) {
            Achat a = new Achat(lPan.get(i).getIdUtilisateur(), lPan.get(i).getIdProduit(), lPan.get(i).getQuantite());
            a.insert(con);
        }
    }
}
