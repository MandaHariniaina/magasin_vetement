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
        <link href="css/navbar-top-fixed.css" rel="stylesheet">
        <link rel="canonical" href="https://getbootstrap.com/docs/5.0/examples/navbar-fixed/">
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

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
            form input {
                margin: 20px;
            }
            form select{
                margin: 20px;
            }
        </style>

        <script src="js/bootstrap.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/bootstrap.bundle.min.js"></script>
        <script src="js/bootstrap.bundle.js"></script>
        
        <style>
            section{
                background-image: url(/MagasinVetements/images/banner.jpg);
                background-size: contain;
                background-attachment: fixed;
            }
        </style>
        <title>Accueil</title>
    </head>

    <%@page errorPage="/exception/errorListeProduit.jsp"%>

    <body>
        <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
            <div class="container col-10">
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
            <div class="col-2" style="color: white;"><% if(session.getAttribute("email") != null){ out.println(session.getAttribute("email")); }%></div>
        </nav>

        <main role="main">

            <section class="jumbotron text-center" style="height : 45vh;">
                <div class="container" >
                    <h1 style="font-size: 70px;"></h1>
                    <p class="lead text-muted"></p>
                </div>
            </section>
        </main>


        <div class="row"></div>
        <div class="row">
            <%@ include file="/produit/contenuPanier.jsp" %>
        </div>
        <div class="row" style="text-align: center;">
            <div class="col-12"></div>
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
            