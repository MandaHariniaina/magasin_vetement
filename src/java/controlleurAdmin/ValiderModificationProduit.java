/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ServiceProduit;

/**
 *
 * @author ACER
 */
public class ValiderModificationProduit extends HttpServlet {

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
        try  {
            /* TODO output your page here. You may use following sample code. */
            int idProduit = Integer.valueOf(request.getParameter("idProduit"));
            int stock = Integer.valueOf(request.getParameter("stock"));
            int prix = Integer.valueOf(request.getParameter("prix"));
            String marque = request.getParameter("marque");
            String categorie = request.getParameter("categorie");
            ServiceProduit produit = new ServiceProduit();
            produit.update(idProduit, marque, categorie, stock, prix);
            RequestDispatcher dispat = request.getRequestDispatcher("ModificationProduit");
            dispat.forward(request, response);
        }
        catch(Exception ex){
            ex.printStackTrace();
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
