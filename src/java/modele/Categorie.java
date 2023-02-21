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
import java.util.Vector;

/**
 *
 * @author Manda
 */
public class Categorie {

    private int idCategorie;
    private String libeleCategorie;

    public Vector<Categorie> selectAll() throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        Vector<Categorie> retour =  new Vector<Categorie>(1,1);
        try {
            con = DBConnection.createConnection();
            st = con.prepareStatement("select id_categorie, libele_categorie from categorie");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour.addElement(new Categorie(res.getInt("id_categorie"), res.getString("libele_categorie")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            st.close();
            con.close();
        }
        return retour;
    }
    
    public Categorie select(String libele) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        Categorie retour = null;
        try {
            con = DBConnection.createConnection();
            st = con.prepareStatement("select id_categorie, libele_categorie from categorie where libele_categorie='" + libele +"'");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour = new Categorie(res.getInt("id_categorie"), res.getString("libele_categorie"));
            }
        } catch (Exception ex) {

        } finally {
            st.close();
            con.close();
        }
        return retour;
    }
    
    public Categorie select(int id) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        Categorie retour = null;
        try {
            con = DBConnection.createConnection();
            st = con.prepareStatement("select id_categorie, libele_categorie from categorie where id_categorie=" + id);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour = new Categorie(res.getInt("id_categorie"), res.getString("libele_categorie"));
            }
        } catch (Exception ex) {

        } finally {
            st.close();
            con.close();
        }
        return retour;
    }

    public Categorie() {

    }

    public Categorie(int idCategorie, String libeleCategorie) {
        this.idCategorie = idCategorie;
        this.libeleCategorie = libeleCategorie;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getLibeleCategorie() {
        return libeleCategorie;
    }

    public void setLibeleCategorie(String libeleCategorie) {
        this.libeleCategorie = libeleCategorie;
    }

}
