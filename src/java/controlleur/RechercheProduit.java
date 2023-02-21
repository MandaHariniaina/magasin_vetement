/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ServiceProduit;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import modele.Categorie;
import modele.Marque;
import modele.Produit;

/**
 *
 * @author Manda
 */
public class RechercheProduit extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            // verification des champs et formulation des requetes
            String recherche = request.getParameter("recherche");
            String marque = request.getParameter("marque");
            String sexe = request.getParameter("sexe");
            int prixMin = 0;
            int prixMax = 0;
            try{
                prixMin =  Integer.valueOf(request.getParameter("prixMin"));
                prixMax = Integer.valueOf(request.getParameter("prixMax"));
            }
            catch(Exception ex){
            }
            String categorie = request.getParameter("categorie");
            String date = request.getParameter("dateSortie");
            // requete des produit
            ServiceProduit prodTrait = new ServiceProduit();
            Vector<Produit> listeProduits = null;
            Vector<Categorie> categories = null;
            Vector<String> categorieParProduit = null;
            Vector<String> marquesParProduit = null;
            Vector<Marque> marques = null;
            Vector<String> images = null;
            try {
                listeProduits = prodTrait.select(recherche, prixMin, prixMax, marque, sexe, categorie, date);
                marquesParProduit = prodTrait.marqueParProduit(listeProduits);
                categories = prodTrait.categories();
                marques = prodTrait.marques();
                images = prodTrait.imagesProduits(listeProduits);
                categorieParProduit = prodTrait.categorieParProduit(listeProduits);
            } catch (Exception ex) {

            }
            // ajout des attributs au requete
            request.setAttribute("nombreProduits", listeProduits.size());
            request.setAttribute("listeProduits", listeProduits);
            request.setAttribute("content", "productList.jsp");
            request.setAttribute("marqueParProduit", marquesParProduit);
            request.setAttribute("marques", marques);
            request.setAttribute("categorieParProduit", categorieParProduit);
            request.setAttribute("categories", categories);
            request.setAttribute("images", images);
            RequestDispatcher dispat = request.getRequestDispatcher("/produit/rechercheProduit.jsp");
            dispat.forward(request, response);
        }
    }
}
