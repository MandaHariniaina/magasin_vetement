/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import modele.Utilisateur;

/**
 *
 * @author Manda
 */
public class ServiceUtilisateur {

    public double getBalance(String email) throws Exception {
        Utilisateur user = new Utilisateur();
        user = user.select(email);
        return user.getArgent();
    }

    public void insert(String id, String nom, String prenom, String password, String sexe) throws Exception {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.insertUtilisateurIntoDB(id, nom, prenom, password, sexe);
    }

    public boolean login(String email, String password) throws Exception {
        Utilisateur user = new Utilisateur();
        try {
            user = user.select(email, password);
        } catch (Exception e) {
            e = new Exception("Login failed");
            throw e;
        }
        if (user != null) {
            return true;
        }
        return false;
    }
}
