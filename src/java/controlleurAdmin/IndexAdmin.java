/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modele.Categorie;
import modele.Marque;
import service.ServiceAdmin;
import service.ServiceProduit;

/**
 *
 * @author ACER
 */
public class IndexAdmin extends HttpServlet {

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
            // Recuperation des inputs
            HttpSession session = request.getSession();
            String password = (String)session.getAttribute("password");
            // verification par la couche metier
            ServiceAdmin u = new ServiceAdmin();
            Vector<Categorie> categories = null;
            Vector<Marque> marques = null;
            ServiceProduit prodTrait = new ServiceProduit();
            categories = prodTrait.categories();
            marques = prodTrait.marques();
            // redirection vers la page accueil
            if (u.login(password)) {
                session.setAttribute("page", 1);
                request.setAttribute("marques", marques);
                request.setAttribute("categories", categories);
                session.setAttribute("loginAdmin",1);
                RequestDispatcher dispat = request.getRequestDispatcher("/admin/index.jsp");
                dispat.forward(request, response);
            } else {
                RequestDispatcher dispat = request.getRequestDispatcher("/admin/login.jsp");
                request.setAttribute("error", 1);
                dispat.forward(request, response);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("exception", ex);
            RequestDispatcher dispat = request.getRequestDispatcher("/exception/exceptionAjoutProduit.jsp");
            //dispat.forward(request, response);
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
