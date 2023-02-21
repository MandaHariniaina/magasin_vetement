/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import base.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Vector;

/**
 *
 * @author Manda
 */
public class Panier {

    private int idPanier;
    private String idUtilisateur;
    private int idProduit;
    private int quantite;

    public void updateQuantite() throws Exception{
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBConnection.createConnection();
            st = con.prepareStatement("UPDATE panier SET quantite="+this.getQuantite()+" WHERE id_panier="+this.getIdPanier());          
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
    
    // ajout au table panierEnleve
    public void cancel(int idPanier) throws Exception{
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBConnection.createConnection();
            //System.out.println("insert into panier values(seq_panier.NEXTVAL,'" + id_util + "'," + id_prod + ",1 )");
            st = con.prepareStatement("INSERT INTO panierAnnule VALUES("+idPanier+")");
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
    
    public void insertIntoPanierValide(Panier panier, Connection con) throws Exception{
        PreparedStatement st = null;
        try {
            //System.out.println("INSERT INTO panierValide VALUES("+panier.getIdPanier()+")");
            st = con.prepareStatement("INSERT INTO panierValide VALUES("+panier.getIdPanier()+")");
            Boolean res = st.execute();
            //System.out.println(res);
            /*if (!res) {
                Exception ex = new Exception("Insert failed");
                throw ex;
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            st.close();
        }
    }

    public Vector<Panier> selectParUtilisateur(String id) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        Vector<Panier> retour = new Vector<Panier>(1, 1);
        try {
            con = DBConnection.createConnection();
            st = con.prepareStatement("select * from panierValable where id_utilisateur='" + id + "'");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour.addElement(new Panier(res.getInt("id_panier"), res.getString("id_utilisateur"), res.getInt("id_produit"), res.getInt("quantite")));
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
        } finally {
            st.close();
            con.close();
        }
        return retour;
    }

    public void insert(String id_util, int id_prod) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBConnection.createConnection();
            //System.out.println("insert into panier values(seq_panier.NEXTVAL,'" + id_util + "'," + id_prod + ",1 )");
            st = con.prepareStatement("insert into panier values(seq_panier.NEXTVAL,'" + id_util + "'," + id_prod + ",1 )");
            Boolean res = st.execute();
            con.commit();
            System.out.println("insertion panier reussie");
        } catch (Exception e) {
            e.printStackTrace();
            con.rollback();
        } finally {
            st.close();
            con.close();
        }
    }

    public Panier select(int id) throws Exception {
        Connection con = DBConnection.createConnection();
        Statement st = con.createStatement();
        ResultSet res = st.executeQuery("select * from panier where id_panier=" + id);
        Panier retour = null;
        while (res.next()) {
            retour = new Panier(res.getInt("id_panier"), res.getString("id_utilisateur"), res.getInt("id_produit"), res.getInt("quantite"));
        }
        st.close();
        con.close();
        return retour;
    }

    public Panier select(String id, Connection con) throws Exception {
        Statement st = con.createStatement();
        ResultSet res = st.executeQuery("select * from panier where id_panier='" + id + "'");
        Panier retour = null;
        while (res.next()) {
            retour = new Panier(res.getInt("id_panier"), res.getString("id_utilisateur"), res.getInt("id_produit"), res.getInt("quantite"));
        }
        st.close();
        return retour;
    }

    public Panier() {

    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Panier(int idPanier, String idUtilisateur, int idProduit, int quantite) {
        this.idPanier = idPanier;
        this.idUtilisateur = idUtilisateur;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    public String getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(String idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public int getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(int idPanier) {
        this.idPanier = idPanier;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

}
