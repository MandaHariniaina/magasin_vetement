/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ServiceProduit;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import modele.Produit;
import modele.Marque;
import modele.Categorie;

/**
 *
 * @author ACER
 */
public class ModificationProduit extends HttpServlet {

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
        response.setContentType("text/plain");
        try {
            /* TODO output your page here. You may use following sample code. */
            ServiceProduit produit = new ServiceProduit();
            Vector<Produit> listeProduits = produit.selectAll();
            request.setAttribute("listeProduits", listeProduits);
            Vector<Categorie> categories = produit.categories();
            Vector<String> marquesParProduit = produit.marqueParProduit(listeProduits);
            Vector<Marque> marques = produit.marques();
            Vector<String> categorieParProduit = produit.categorieParProduit(listeProduits);
            request.setAttribute("marques", marques);
            request.setAttribute("categories", categories);
            request.setAttribute("marqueParProduit", marquesParProduit);
            request.setAttribute("categorieParProduit", categorieParProduit);
            RequestDispatcher dispat = request.getRequestDispatcher("/admin/stockProduit.jsp");
            dispat.forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("exception", ex);
            RequestDispatcher dispat = request.getRequestDispatcher("/exception/exceptionAjoutProduit.jsp");
            dispat.forward(request, response);
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
        processRequest(request, response);
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
