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
import java.sql.Date;

/**
 *
 * @author Manda
 */
public class Produit {

    private int idProduit;
    private String nomProduit;
    private int idMarque;
    private int idCategorie;
    private Date dateArrivage;
    private String image;
    private String description;
    private int prixUnitaire;
    private int stock;

    public Vector<Produit> selectMeilleurVente() throws Exception{
        Vector<Produit> retour = new Vector<Produit>(1, 1);
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBConnection.createConnection();
            st = con.prepareStatement("SELECT p.id_produit, nom_produit, p.id_marque, p.id_categorie, date_arrivage, image, description_produit, prix_unitaire, stock date FROM nombreVenteProduit a JOIN produit p on a.id_produit = p.id_produit ORDER BY nb_vente");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour.addElement(new Produit(res.getInt("id_produit"), res.getString("nom_produit"), res.getInt("id_marque"), res.getInt("id_categorie"), res.getDate("date_arrivage"), res.getString("image"), res.getString("description_produit"), res.getInt("prix_unitaire"), res.getInt("stock")));
            }
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            st.close();
            con.close();
        }
        return retour;
    }
    
    public void update() throws Exception{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBConnection.createConnection();
            st = con.prepareStatement(" UPDATE produit SET id_marque="+ this.getIdMarque() +", id_categorie="+ this.getIdCategorie() +", stock="+ this.getStock() +", prix_unitaire="+this.getPrixUnitaire()+"  WHERE id_produit="+idProduit);
            Boolean result = st.execute();
            con.commit();
        }
        catch(Exception ex){
            ex.printStackTrace();
            throw ex;
        }
        finally{
            st.close();
            con.close();
        }
    }
    
    public void delete(int idProduit) throws Exception{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBConnection.createConnection();
            st = con.prepareStatement(" DELETE FROM produit WHERE id_produit="+idProduit);
            Boolean result = st.execute();
        }
        catch(Exception ex){
            ex.printStackTrace();
            throw ex;
        }
        finally{
            st.close();
            con.close();
        }
    }
    
    public void update(Connection con) throws Exception{
        PreparedStatement st = null;
        try{
            st = con.prepareStatement("UPDATE produit SET stock="+this.getStock()+" WHERE id_produit="+this.getIdProduit());
            Boolean res = st.execute();
        }catch(Exception ex){
            throw ex;
        }
    }
    
    public void insert(String nom, int marque, int categorie,String image,String description, int prix, int stock) throws Exception{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBConnection.createConnection();
            st = con.prepareStatement("INSERT INTO produit VALUES(seq_produit.NEXTVAL,'"+nom+"', "+marque+","+categorie+", CURRENT_DATE, '"+image+"', '"+description+"', "+prix+", "+stock+")");
            Boolean result = st.execute();
        }
        catch(Exception ex){
            ex.printStackTrace();
            ex = new Exception("Insert failed");
            throw ex;
        }
        finally{
            st.close();
            con.close();
        }
    }
    
    public Vector<Produit> execute(String requete) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        Vector<Produit> retour = new Vector<Produit>(1, 1);
        try {
            con = DBConnection.createConnection();
            //System.out.println("select p.id_produit, nom_produit, p.id_marque, p.id_categorie, date_arrivage, image, description_produit, prix_unitaire, stock from produit p join categorie c on p.id_categorie = c.id_categorie join marque m on p.id_marque = m.id_marque " + requete);
            st = con.prepareStatement("select p.id_produit, nom_produit, p.id_marque, p.id_categorie, date_arrivage, image, description_produit, prix_unitaire, stock from produit p join categorie c on p.id_categorie = c.id_categorie join marque m on p.id_marque = m.id_marque " + requete);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour.addElement(new Produit(res.getInt("id_produit"), res.getString("nom_produit"), res.getInt("id_marque"), res.getInt("id_categorie"), res.getDate("date_arrivage"), res.getString("image"), res.getString("description_produit"), res.getInt("prix_unitaire"), res.getInt("stock")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            st.close();
            con.close();
        }
        return retour;
    }

    public int countAll() throws Exception {
        Connection con = DBConnection.createConnection();
        PreparedStatement st = con.prepareStatement("select count(id_produit) as nbr from produit");
        ResultSet rs = st.executeQuery();
        int retour = 0;
        while (rs.next()) {
            retour = rs.getInt("nbr");
        }
        return retour;
    }

    public String categorie(Connection con) throws Exception {
        PreparedStatement st = con.prepareStatement("select libele_categorie from produit p join categorie c on p.id_categorie=c.id_categorie where id_produit=" + this.idProduit);
        ResultSet rs = st.executeQuery();
        String retour = "";
        while (rs.next()) {
            retour = rs.getString("libele_categorie");
        }
        return retour;
    }

    public String marque(Connection con) throws Exception {
        PreparedStatement st = con.prepareStatement("select libele_marque from produit p join marque m on p.id_marque=m.id_marque where id_produit = " + this.idProduit);
        ResultSet rs = st.executeQuery();
        String retour = "";
        while (rs.next()) {
            retour = rs.getString("libele_marque");
        }
        return retour;
    }

    public String description(Connection con) throws Exception {
        PreparedStatement st = con.prepareStatement("select description_produit from produit p join description_produit dp on p.id_produit=dp.id_produit");
        ResultSet rs = st.executeQuery();
        String retour = "";
        while (rs.next()) {
            retour = rs.getString("description");
        }
        return retour;
    }

    public Produit select(int idProduit, Connection con) throws Exception {
        PreparedStatement st = null;
        Produit retour = null;
        try {
            st = con.prepareStatement("select * from produit where id_produit=" + idProduit);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour = new Produit(res.getInt("id_produit"), res.getString("nom_produit"), res.getInt("id_marque"), res.getInt("id_categorie"), res.getDate("date_arrivage"), res.getString("image"), res.getString("description_produit"), res.getInt("prix_unitaire"), res.getInt("stock"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            st.close();
        }
        return retour;
    }

    public Produit select(int idProduit) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        Produit retour = null;
        try {
            con = DBConnection.createConnection();
            st = con.prepareStatement("select * from produit where id_produit=" + idProduit);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour = new Produit(res.getInt("id_produit"), res.getString("nom_produit"), res.getInt("id_marque"), res.getInt("id_categorie"), res.getDate("date_arrivage"), res.getString("image"), res.getString("description_produit"), res.getInt("prix_unitaire"), res.getInt("stock"));
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            st.close();
            con.close();
        }
        return retour;
    }

    public Vector<Produit> selectAll() throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        Vector<Produit> retour = new Vector<Produit>(1, 1);
        try {
            con = DBConnection.createConnection();
            //System.out.println("select * from (select a.*, rownum rnum from (select * from produit)a where rownum <= "+finListe+") where rnum >= "+debutListe);
            st = con.prepareStatement("select * from produit");
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour.addElement(new Produit(res.getInt("id_produit"), res.getString("nom_produit"), res.getInt("id_marque"), res.getInt("id_categorie"), res.getDate("date_arrivage"), res.getString("image"), res.getString("description_produit"), res.getInt("prix_unitaire"), res.getInt("stock")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            st.close();
            con.close();
        }
        return retour;
    }
    
    public Vector<Produit> selectAll(int page) throws Exception {
        Connection con = null;
        PreparedStatement st = null;
        int debutListe = ((page - 1) * 10) + 1;
        int finListe = (page * 10);
        Vector<Produit> retour = new Vector<Produit>(1, 1);
        try {
            con = DBConnection.createConnection();
            //System.out.println("select * from (select a.*, rownum rnum from (select * from produit)a where rownum <= "+finListe+") where rnum >= "+debutListe);
            st = con.prepareStatement("select * from (select a.*, rownum rnum from (select * from produit)a where rownum <= " + finListe + ") where rnum >= " + debutListe);
            ResultSet res = st.executeQuery();
            while (res.next()) {
                retour.addElement(new Produit(res.getInt("id_produit"), res.getString("nom_produit"), res.getInt("id_marque"), res.getInt("id_categorie"), res.getDate("date_arrivage"), res.getString("image"), res.getString("description_produit"), res.getInt("prix_unitaire"), res.getInt("stock")));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            st.close();
            con.close();
        }
        return retour;
    }

    public Produit(int id, String nom, int idMarque, int categorie, Date datearriv, String image) {
        setIdProduit(id);
        setNomProduit(nom);
        setIdMarque(idMarque);
        setIdCategorie(categorie);
        setDateArrivage(datearriv);
        setImage(image);
    }

    public Produit(int idProduit, String nomProduit, int idMarque, int idCategorie, Date dateArrivage, String image, String description, int prixUnitaire, int stock) {
        this.idProduit = idProduit;
        this.nomProduit = nomProduit;
        this.idMarque = idMarque;
        this.idCategorie = idCategorie;
        this.dateArrivage = dateArrivage;
        this.image = image;
        this.description = description;
        this.prixUnitaire = prixUnitaire;
        this.stock = stock;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public int getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(int idMarque) {
        this.idMarque = idMarque;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Date getDateArrivage() {
        return dateArrivage;
    }

    public void setDateArrivage(Date dateArrivage) {
        this.dateArrivage = dateArrivage;
    }

    public Produit() {

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(int prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

}
