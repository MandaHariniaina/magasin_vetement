<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>

    <head>
        <title>Accueil</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/MagasinVetements/css/bootstrap.css">
        <link rel="stylesheet" href="/MagasinVetements/css/bootstrap.min.css">
        <script src="/MagasinVetements/js/bootstrap.js"></script>
        <script src="/MagasinVetements/js/bootstrap.min.js"></script>
        <style>
            body{
                background-image: url(/MagasinVetements/images/login_background.jpg);
            }
        </style>

        <%@page import="modele.Produit" %>
        <%@page import="modele.Marque" %>
        <%@page import="modele.Categorie"  %>
        <%@page import="java.util.Vector" %>
        <% Vector<Marque> marques = (Vector<Marque>) (request.getAttribute("marques")); %>
        <% Vector<Categorie> categories = (Vector<Categorie>) (request.getAttribute("categories")); %>
        <title>Login</title>
    </head>

    <body>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <div class="container col-12">
                <a class="navbar-brand" href="/MagasinVetements/ListeProduit">Accueil</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse" style="margin-right: 10vw;">
                    <ul class="navbar-nav me-auto mb-2 mb-md-0" style="float: right;">
                        <span col-1><a  class="nav-link"  href="inscription.jsp">S'incrire</a></span>
                        <span col-1><a class="nav-link" href="/MagasinVetements/admin/login.jsp">admin</a></span>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container col-3" style="margin-top: 22vh;background-color: rgba(255, 255, 255, 0.4);padding: 2%;border-radius: 8px;">
            <form method="POST" action="/MagasinVetements/Login">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label" style="font-size: 22px;">Email </label>
                    <input type="email" class="form-control" id="exampleInputEmail1" name="email" aria-describedby="emailHelp">
                    <% if (request.getAttribute("error") != null) { %>
                    <div id="emailHelp" class="form-text" style="color: rgb(200,0,0)">Mot de passe ou email non valide</div>
                    <% }%>
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label" style="font-size: 22px;">Password</label>
                    <input type="password" name="password" class="form-control" id="exampleInputPassword1">
                </div>
                <br>
                <button type="submit" class="btn btn-primary" style="padding: 1vh 3vw;background-color: rgb(255, 127, 80);border-color: rgb(255, 127, 80); margin-left: 25%;">Submit</button>
            </form>
        </div>
    </body>
</html>