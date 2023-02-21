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
public class Marque {

    private int idMarque;
    private String libeleMarque;

    public Vector<Marque> selectAll() throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        Vector<Marque> retour = new Vector<Marque>(1, 1);
        try {
            con = DBConnection.createConnection();
            st = con.prepareStatement("select id_marque, libele_marque from marque");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour.addElement(new Marque(res.getInt("id_marque"), res.getString("libele_marque")));
            }
        } catch (Exception ex) {

        } finally {
            st.close();
            con.close();
        }
        return retour;
    }

    public Marque select(String libele) throws Exception{
        Connection con = null;
        PreparedStatement st = null;
        Marque retour = null;
        try {
            con = DBConnection.createConnection();
            st = con.prepareStatement("select id_marque, libele_marque from marque where libele_marque='" + libele +"'");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour = new Marque(res.getInt("id_marque"), res.getString("libele_marque"));
            }
        } catch (Exception ex) {

        } finally {
            st.close();
            con.close();
        }
        return retour;
    }
    
    public Marque select(int id) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        Marque retour = null;
        try {
            con = DBConnection.createConnection();
            st = con.prepareStatement("select id_marque, libele_marque from marque where id_marque=" + id );
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour = new Marque(res.getInt("id_marque"), res.getString("libele_marque"));
            }
        } catch (Exception ex) {

        } finally {
            st.close();
            con.close();
        }
        return retour;
    }

    public Marque(int id, String nom) {
        setIdMarque(id);
        setLibeleMarque(nom);
    }

    public Marque() {

    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int id_marque) {
        this.idMarque = id_marque;
    }

    public String getLibeleMarque() {
        return libeleMarque;
    }

    public void setLibeleMarque(String nom_marque) {
        this.libeleMarque = nom_marque;
    }
}
