/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletContext;
import service.ServiceProduit;
import modele.Produit;
import modele.Categorie;
import java.util.Vector;
import modele.Marque;

/**
 *
 * @author Manda
 */
@WebServlet(name = "selectProduitAccueil", urlPatterns = {"/selectProduitAccueil"})
public class ListeProduit extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ServiceProduit prodTrait = new ServiceProduit();
            // liste des produits
            // page du liste de produit
            HttpSession session = request.getSession();
            if (session.getAttribute("page") == null) {
                session.setAttribute("page", 1);
            } else if (request.getParameter("page") != null) {
                session.setAttribute("page", Integer.valueOf(request.getParameter("page")));
            }
            // marque et description de chaque produit;
            Vector<Produit> lProduits = new Vector<Produit>(1, 1);
            Vector<Categorie> categories = null;
            Vector<String> categorieParProduit = null;
            Vector<String> marquesParProduit = null;
            Vector<Marque> marques = null;
            Vector<String> images = null;
            // nombre total de produits
            int nombreProduits = 0;
            try {
                lProduits = prodTrait.selectAll((int) session.getAttribute("page"));
                marquesParProduit = prodTrait.marqueParProduit(lProduits);
                categories = prodTrait.categories();
                marques = prodTrait.marques();
                categorieParProduit = prodTrait.categorieParProduit(lProduits);
                images = prodTrait.imagesProduits(lProduits);
                nombreProduits = prodTrait.countAll();

                // ajout des attributs au requete
                request.setAttribute("nombreProduits", nombreProduits);
                request.setAttribute("listeProduits", lProduits);
                request.setAttribute("content", "/contenuListeProduit.jsp");
                request.setAttribute("marqueParProduit", marquesParProduit);
                request.setAttribute("marques", marques);
                request.setAttribute("categorieParProduit", categorieParProduit);
                request.setAttribute("categories", categories);
                request.setAttribute("images", images);

                ServletContext contexte = this.getServletContext();
                if (contexte.getAttribute("categories") == null && contexte.getAttribute("marques") == null) {
                    contexte.setAttribute("categories", categories);
                    contexte.setAttribute("marques", marques);
                }

                // redirection requete
                RequestDispatcher dispat = request.getRequestDispatcher("/produit/listeProduit.jsp");
                dispat.forward(request, response);
            } catch (Exception ex) {
                request.setAttribute("exception", ex);
                RequestDispatcher dispat = request.getRequestDispatcher("/exception/exceptionPage.jsp");
                dispat.forward(request, response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
