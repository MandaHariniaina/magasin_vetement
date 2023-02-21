<%-- 
    Document   : template
    Created on : 9 juil. 2021, 08:01:09
    Author     : Manda
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/bootstrap.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">

        <style>
            .bd-placeholder-img {
                font-size: 1.125rem;
                text-anchor: middle;
                -webkit-user-select: none;
                -moz-user-select: none;
                user-select: none;
            }

            @media (min-width: 768px) {
                .bd-placeholder-img-lg {
                    font-size: 3.5rem;
                }
            }
        </style>

        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/bootstrap.bundle.js"></script>
        <title>Accueil</title>
    </head>


    <body>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <div class="container col-12">
                <a class="navbar-brand" href="index.jsp">Accueil</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse" style="margin-right: 10vw;">
                    <ul class="navbar-nav me-auto mb-2 mb-md-0" style="float: right;">

                        <% if (session.getAttribute("email") == null) { %>
                        <a class="nav-link" href="utilisateur/login.jsp">Connection</a>
                        <span>|</span>
                        <span col-1><a  class="nav-link"  href="utilisateur/inscription.jsp">S'incrire</a></span>
                        <% } else { %>
                        <span col-1><a  class="nav-link"  href="Panier">Panier</a></span>
                        <span>|</span>
                        <span col-1><a class="nav-link"  href="Deconnection">Se deconnecter</a></span>
                        <% }
                        %>
                    </ul>
                </div>
            </div>
        </nav>

        <main role="main">

            <section class="jumbotron text-center">
                <div class="container">
                    <h1>Boutique en ligne</h1>
                    <p class="lead text-muted"></p>
                </div>
            </section>
        </main>
        <%@page import="modele.Produit" %>
        <% Produit produit = (Produit) request.getAttribute("produit");%>
        <% String marque =  (String)request.getAttribute("marque");%>
        <% String categorie =  (String)request.getAttribute("categorie");%>

        <div class="row">
            <div class="text-center">
                <img src="<% out.print(produit.getImage()); %>" class="rounded" alt="...">
                <h1><% out.print(produit.getNomProduit()); %></h1>
                <p><% out.print(produit.getDescription()); %></p>
                <p><span style="font-size: 15px;font-weight: bold;">Marque: </span><% out.print(marque); %></p>
                <p><span style="font-size: 15px;font-weight: bold;">Categorie: </span><% out.print(categorie); %></p>
                <p><span style="font-size: 15px;font-weight: bold;">Prix: </span><% out.print(produit.getPrixUnitaire()); %></p>
            </div>
            <div class="row" style="text-align: center;">
                <a href="TelechargementInfoProduit?idProduit=<% out.println(produit.getIdProduit()); %>"  style="color: black;font-size: 20px;">Telecharger les informations du produit</a>
            </div>
        </div>
        <div class="row" style="text-align: center;">
            <div class="col-12"><h1></h1></div>
        </div>
        
        <footer class="text-muted" style="background-color: #000">
            <div class="container row" style="text-align: center;">
                <div class="col-6"><p class="float-right">
                    <a href="#" style="text-decoration: none; color: white;">Retout au début de page</a>
                </p></div>
                <div class="col-6" style="text-align: right;"><p><span style="font-weight: bold;font-size: 15px"> Contact:</span> andriamitantsoamanda@gmail.com / +261 67 043 35</p></div>
                <div class="row">@copyright 2021</div>
            </div>
        </footer>
    </body>

</html>

