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
 * @author Manda
 */
public class Achat {
    private int idAchat;
    private String idUtilisateur;
    private int idProduit;
    private int quantite;

    public void insert() throws Exception{
        Connection con = DBConnection.createConnection();
        PreparedStatement st = con.prepareStatement("i");
        Boolean res = st.execute();
        try {
            if (!res) {
                Exception ex = new Exception("Insert failed");
                throw ex;
            }
        } catch (Exception e) {
            con.rollback();
            st.close();
            con.close();
        }
    }
    
    public void insert(Connection con) throws Exception{
        PreparedStatement st = null;
        try {
            System.out.println("insert into achat values(seq_achat.NEXTVAL, '"+getIdUtilisateur()+"', "+getIdProduit()+", "+getQuantite()+")");
            st = st = con.prepareStatement("insert into achat values(seq_achat.NEXTVAL, '"+getIdUtilisateur()+"', "+getIdProduit()+", "+getQuantite()+")");
            Boolean res = st.execute();
            /*if (!res) {
                Exception ex = new Exception("Insert failed");
                throw ex;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
        }
        finally{
            st.close();
        }
    }
    
    public Achat select(String id) throws Exception{
        Connection con = DBConnection.createConnection();
        PreparedStatement st = con.prepareStatement("select * from Achat where id_produit='"+id+"'");
        ResultSet res = st.executeQuery();
        Achat retour = null;
        while(res.next()){
            retour = new Achat(res.getInt("id_achat"), res.getString("id_utilisateur"), res.getInt("id_produit"), res.getInt("quantite"));
        }
        st.close();
        con.close();
        return retour;
    }

    public Achat(String idUtilisateur, int idProduit, int quantite) {
        this.idUtilisateur = idUtilisateur;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    public Achat(int idAchat, String idUtilisateur, int idProduit, int quantite) {
        this.idAchat = idAchat;
        this.idUtilisateur = idUtilisateur;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    
    public Achat(){
        
    }
    

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getIdAchat() {
        return idAchat;
    }

    public void setIdAchat(int idAchat) {
        this.idAchat = idAchat;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
}
