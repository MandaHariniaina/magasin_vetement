/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleurAdmin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.ServiceProduit;
import service.ServiceTelechargement;
import javax.servlet.RequestDispatcher;
import modele.Categorie;
import modele.Marque;
import java.io.*;
import javax.servlet.annotation.*;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

/**
 *
 * @author ACER
 */
@MultipartConfig
public class AjoutProduit extends HttpServlet {

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
        response.setContentType("text/plain;charset=UTF-8");

        try {
            ServletContext contexteActuelle = this.getServletContext();
            ServletContext contexteListeProduit = contexteActuelle.getContext("/ListeProduit");

            // recuperation des champs
            String nomProduit = request.getParameter("nom");
            int marque = Integer.valueOf(request.getParameter("marque"));
            int categorie = Integer.valueOf(request.getParameter("categorie"));
            String description = request.getParameter("description");
            int stock = Integer.valueOf(request.getParameter("stock"));
            int prix = Integer.valueOf(request.getParameter("prix"));
            Part imageFile = request.getPart("image");
            String image = imageFile.getSubmittedFileName();
            // telechargement du fichier image
            ServiceTelechargement uploader = new ServiceTelechargement();
            uploader.uploadImage(imageFile, contexteActuelle.getRealPath("/"));
            // ajout du produit
            ServiceProduit serviceProduit = new ServiceProduit();
            serviceProduit.insert(nomProduit, marque, categorie, image, description, prix, stock);
            request.setAttribute("success", 1);
            Vector<Categorie> categories = null;
            Vector<Marque> marques = null;
            ServiceProduit prodTrait = new ServiceProduit();
            categories = prodTrait.categories();
            marques = prodTrait.marques();
            request.setAttribute("marques", marques);
            request.setAttribute("categories", categories);
            RequestDispatcher dispat = request.getRequestDispatcher("/admin/index.jsp");
            dispat.forward(request, response);
        } catch (Exception ex) {
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
