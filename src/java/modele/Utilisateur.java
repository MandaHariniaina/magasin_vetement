/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import base.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author Mandaa
 */
public class Utilisateur {

    private String idUtilisateur;
    private String nom;
    private String prenom;
    private String password;
    private String sexe;
    private double argent;

    public double getArgent() {
        return argent;
    }

    public void setArgent(double argent) {
        this.argent = argent;
    }

    public void update(Connection con)throws Exception {
        PreparedStatement st = null;
        try{
            //System.out.println("UPDATE utilisateur SET password='"+ getPassword() +"', argent="+ getArgent() +" sexe='"+ getSexe() +"', nom='"+getNom()+"', prenom='"+ getPrenom()+"' WHERE id_utilisateur='"+ getIdutilisateur() +"'");
            st = con.prepareStatement("UPDATE utilisateur SET password='"+ getPassword() +"', argent="+ getArgent() +" sexe='"+ getSexe() +"', nom='"+getNom()+"', prenom='"+ getPrenom()+"' WHERE id_utilisateur='"+ getIdutilisateur() +"'");
        }catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            st.close();
        }
    }
    
    public Utilisateur select(String id) throws Exception {
        Connection con = null;
        Utilisateur retour = null;
        PreparedStatement st = null;
        try {
            con = DBConnection.createConnection();
            st = con.prepareStatement("select * from utilisateur where id_utilisateur='" + id + "'");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour = new Utilisateur(res.getString("id_utilisateur"), res.getString("nom"), res.getString("prenom"), res.getString("password"), res.getString("sexe"), res.getDouble("argent"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            st.close();
            con.close();
        }
        return retour;
    }
    
    public Utilisateur select(String id, String password) throws Exception {
        Connection con = null;
        Utilisateur retour = null;
        PreparedStatement st = null;
        try {
            con = DBConnection.createConnection();
            st = con.prepareStatement("SELECT * FROM utilisateur WHERE id_utilisateur='" + id + "' and password='" + password + "'");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour = new Utilisateur(res.getString("id_utilisateur"), res.getString("nom"), res.getString("prenom"), res.getString("password"), res.getString("sexe"),res.getDouble("argent"));
            }
        } catch (Exception ex) {
            ex = new Exception("Exception in select");
            throw ex;
        } finally {
            st.close();
            con.close();
        }
        return retour;
    }
    
    public void insertUtilisateurIntoDB(String id, String nom, String prenom, String password, String sexe) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBConnection.createConnection();
            st = con.prepareStatement("INSERT INTO utilisateur VALUES('" + id + "','" + nom + "','" + prenom + "','" + password + "','" + sexe + "', 15000)");
            Boolean res = st.execute();
            con.commit();
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            st.close();
            con.close();
        }
    }

    public Utilisateur() {

    }

    public Utilisateur(String id_utilisateur, String nom, String prenom, String password, String sexe, double argent) {
        this.idUtilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.password = password;
        this.sexe = sexe;
        this.argent = argent;
    }

    public String getIdutilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String id_utilisateur) {
        this.idUtilisateur = id_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

}
