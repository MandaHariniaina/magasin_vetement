/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurUtilisateur;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.ServicePanier;
import service.ServiceProduit;
import service.ServiceUtilisateur;
import java.util.Vector;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author ACER
 */
public class Panier extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            ServicePanier servicePanier = new ServicePanier();
            Vector<modele.Panier> listePaniers = null;
            Vector<modele.Produit> listeProduits = null;
            Vector<String> images = null;
            ServiceProduit serviceProduit = new ServiceProduit();
            ServiceUtilisateur serviceUtilisateur = new ServiceUtilisateur();
            double balance= 0;
            // recuperation session utilisateur
            HttpSession session = request.getSession();
            String idUser = (String) session.getAttribute("email");
            try {
                listePaniers = servicePanier.selectAll(idUser);
                listeProduits = servicePanier.selectProduit(listePaniers);
                images = serviceProduit.imagesProduits(listeProduits);
                balance = serviceUtilisateur.getBalance((String)session.getAttribute("email"));
            } catch (Exception ex) {

            }
            request.setAttribute("listePaniers", listePaniers);
            request.setAttribute("listeProduits", listeProduits);
            request.setAttribute("images", images);
            request.setAttribute("balance", balance);
            RequestDispatcher dispat = request.getRequestDispatcher("/produit/panier.jsp");
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
