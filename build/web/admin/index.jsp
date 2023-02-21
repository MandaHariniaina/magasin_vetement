<%-- 
    Document   : index
    Created on : 25 juil. 2021, 16:46:33
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="/MagasinVetements/css/bootstrap.css">
        <link rel="stylesheet" href="/MagasinVetements/css/bootstrap.min.css">
        <script src="/MagasinVetements/js/bootstrap.js"></script>
        <title>Menu de gestion des produits</title>
    </head>

    <script>
        function verifierChamps() {
            var nom = document.getElementById("nom").value;
            var marque = document.getElementById("marque").value;
            var categorie = document.getElementById("categorie").value;
            var description = document.getElementById("description").value;
            var stock = document.getElementById("stock").value;
            var prix = document.getElementById("prix").value;
            var image = document.getElementById("file").value;
            if (nom != null && marque != null && categorie != null && description != null && stock != null && prix != null) {
                console.log("afficher");
                document.getElementById("validation").disabled = false;
            }
        }
    </script>

    <%@page import="java.util.Vector" %>
    <%@page import="modele.Marque" %>
    <%@page import="modele.Categorie"  %>
    <% Vector<Marque> marques = (Vector<Marque>) (request.getAttribute("marques")); %>
    <% Vector<Categorie> categories = (Vector<Categorie>) (request.getAttribute("categories")); %>

    <body>
        <div class="container col-12">
            <h1>Menu de gestion </h1>
            <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
                <div class="container col-12">
                    <div class="collapse navbar-collapse" id="navbarCollapse" style="margin-right: 10vw;">
                        <ul class="navbar-nav me-auto mb-2 mb-md-0" style="float: right;">
                            <span col-1><a  class="nav-link"  href="DeconnectionAdmin">Deconnection</a></span>
                            <span col-1><a class="nav-link" href="ModificationProduit">Modifier le stock produit</a></span>
                        </ul>
                    </div>
                </div>
            </nav>

            <form class="row g-3" method="POST" action="/MagasinVetements/AjoutProduit" autocomplete="off" enctype="multipart/form-data">
                <div class="col-md-4">
                    <label for="inputText1" class="form-label">Nom produit</label>
                    <input type="text" class="form-control" id="nom" name="nom" onchange="verifierChamps()">
                </div>
                <div class="col-4">
                    <label for="inputText2" class="form-label">Marque</label>
                    <select name="marque" id="marque"  class="form-control" onchange="verifierChamps()">
                        <% for (int i = 0; i < marques.size(); i++) { %>
                        <option value="<% out.print(marques.get(i).getIdMarque()); %>">
                            <% out.print(marques.get(i).getLibeleMarque()); %></option>
                            <% } %>
                    </select>
                </div>
                <div class="col-4">
                    <label for="inputText3" class="form-label">Categorie</label>
                    <select name="categorie" id="categorie" class="form-control" onchange="verifierChamps()">
                        <% for (int i = 0; i < categories.size(); i++) { %>
                        <option value="<% out.print(categories.get(i).getIdCategorie()); %>">
                            <% out.print(categories.get(i).getLibeleCategorie()); %></option>
                            <% }%>
                    </select><br>
                </div>
                <div class="col-md-12">
                    <label for="inputPassword4" class="form-label">Description</label>
                    <input type="text" class="form-control" id="description" name="description"  onchange="verifierChamps()">
                </div>
                <div class="col-md-4">
                    <label for="inputPassword5" class="form-label">Stock</label>
                    <input type="number" class="form-control" id="stock" min=0 name="stock" onchange="verifierChamps()">
                </div>
                <div class="col-md-4">
                    <label for="image" class="form-label">Image</label>
                    <input type="file" class="form-control" id="file" name="image" onchange="verifierChamps()">
                </div>
                <div class="col-md-4">
                    <label for="inputPassword5" class="form-label">Prix unitaire</label>
                    <input type="number" class="form-control" id="prix" min=0 name="prix" onchange="verifierChamps()">
                </div>
                <div class="col-12">
                    <button id="validation"  type="submit" class="btn btn-primary" disabled>Ajouter</button>
                </div>
            </form>
        </div>
    </body>
</html>
