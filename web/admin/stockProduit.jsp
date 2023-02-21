<%-- 
    Document   : stockProduit
    Created on : 29 juil. 2021, 16:52:13
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Vector" %>
<%@page import="modele.Produit" %>
<%@page import="modele.Marque" %>
<%@page import="modele.Categorie" %>
<% Vector<Produit> listeProduits = (Vector<Produit>) request.getAttribute("listeProduits"); %>
<% Vector<Marque> marques = (Vector<Marque>) (request.getAttribute("marques")); %>
<% Vector<Categorie> categories = (Vector<Categorie>) (request.getAttribute("categories")); %>
<% Vector<String> categorieParProduit = (Vector<String>) (request.getAttribute("categorieParProduit")); %>
<% Vector<String> marqueParProduit = (Vector<String>) (request.getAttribute("marqueParProduit")); %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/bootstrap.bundle.js"></script>

        <title>Gestion des produits</title>
    </head>

    <body>
        <div class="container col-10">
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <div class="container col-12">
                <div class="collapse navbar-collapse" id="navbarCollapse" style="margin-right: 10vw;">
                    <ul class="navbar-nav me-auto mb-2 mb-md-0" style="float: right;">
                        <span col-1><a  class="nav-link"  href="DeconnectionAdmin">Deconnection</a></span>
                        <span col-1><a class="nav-link" href="IndexAdmin">Ajout de produit</a></span>
                    </ul>
                </div>
            </div>
        </nav>
            <h1>Modification produit</h1>
            <table class="table table-striped">
                <tr style="background-color: black;color: black;">
                    <th>Nom produit</th>
                    <th>Categorie</th>
                    <th>Marque</th>
                    <th>Prix</th>
                    <th>Stock</th>
                    <th>Modifier</th>
                    <th>Supprimer</th>
                </tr>
                <% for (int i = 0; i < listeProduits.size(); i++) {%>
                <form method="POST" action="ValiderModificationProduit">
                    <tr>
                        <td><%=listeProduits.get(i).getNomProduit()%><input type="text" value="<%=listeProduits.get(i).getIdProduit() %>" name="idProduit" style="display: none;"></td>
                        <td>
                            <select name="marque" id="marque">
                                <option value="<%=marqueParProduit.get(i)%>" selected>Actuelle: <%=marqueParProduit.get(i)%></option>
                                <% for (int j = 0; j < marques.size(); j++) { %>
                                <option value="<% out.print(marques.get(j).getIdMarque()); %>">
                                    <% out.print(marques.get(j).getLibeleMarque()); %></option>
                                    <% }%>
                            </select>
                        </td>
                        <td>
                            <select name="categorie" id="categorie">
                                <option value="<%=categorieParProduit.get(i)%>" selected>Actuelle: <%=categorieParProduit.get(i)%></option>
                                <% for (int j = 0; j < categories.size(); j++) { %>
                                <option value="<% out.print(categories.get(j).getIdCategorie()); %>">
                                    <% out.print(categories.get(j).getLibeleCategorie()); %></option>
                                    <% }%>
                            </select>
                        </td>
                        <td><input type="number" name="prix" min=0 value="<%=listeProduits.get(i).getPrixUnitaire()%>" style="text-align: right;"></td>
                        <td><input type="number" name="stock" min=0 value="<%=listeProduits.get(i).getStock()%>" style="text-align: right;"></td>
                        <td><button type="submit">Modifier</button></td>
                        <td><a href="SupprimerProduit?idProduit=<%=listeProduits.get(i).getIdProduit()%>">Supprimer</a></td>
                    </tr>
                </form>
                <% }%>
            </table>
        </div>
    </body>
</html>
