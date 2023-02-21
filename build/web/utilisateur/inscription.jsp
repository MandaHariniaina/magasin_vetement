<%-- 
    Document   : inscription
    Created on : 25 juil. 2021, 16:25:46
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
        <script src="/MagasinVetements/js/bootstrap.min.js"></script>
        <script>
            function verification() {
                var radioHomme = document.getElementById("radioHomme");
                var radioFemme = document.getElementById("radioFemme");
                var email = document.getElementById("inputEmail4").value;
                var nom = document.getElementById("inputAddress").value;
                var prenom = document.getElementById("inputAddress2").value;
                if (radioHomme.checked == true || radioFemme.checked == true) {
                    console.log("true");
                }
                if ((radioHomme.checked == true || radioFemme.checked == true) && compare() == true && email.length > 0 && nom.length > 0 && prenom.length > 0 && true && compare() == true) {
                    document.getElementById("validation").disabled = false;
                } else {
                    document.getElementById("validation").disabled = true;
                }
            }
            function validate() {
                var validationField = document.getElementById('validation-txt');
                var password = document.getElementById('inputPassword4');
                var content = password.value;
                var errors = [];
                if (content.length < 8) {
                    errors.push("Votre mot de passe doit contenir au moins 8 charactères \n");
                    validationField.innerHtml = "Votre mot de passe doit contenir au moins 8 charactères \n"
                }
                if (content.search(/[a-z]/i) < 0) {
                    errors.push("Votre mot de passe doit contenir au moins 1 lettre \n");
                    validationField.innerHtml = "Votre mot de passe doit contenir au moins 1 lettre \n"
                }
                if (content.search(/[0-9]/i) < 0) {
                    errors.push("Votre mot de passe doit contenir au moins un chiffre \n");
                    validationField.innerHtml = "Votre mot de passe doit contenir au moins un chiffre \n"
                }
                if (errors.length > 0) {
                    validationField.innerHTML = errors.join('');
                    return false;
                }
                validationField.innerHTML = errors.join('');
                verification();
                validationField.innerHtml = "Validé"
                return true;

            }
            function compare() {
                var validationField = document.getElementById('validation2-txt');
                var password = document.getElementById('inputPassword5');
                var passwordRef = document.getElementById('inputPassword4');
                var content = password.value;
                var contentRef = passwordRef.value;
                var errors = [];
                var success = [];
                if (content.localeCompare(contentRef) == 0) {
                    success.push("Correct");
                    validationField.style.color = "green";
                    validationField.innerHTML = success.join('');
                    return true;
                } else {
                    errors.push("Mots de passe differents");
                    validationField.style.color = "red";
                    validationField.innerHTML = errors.join('');
                    return false;
                }
                validationField.innerHtml = errors.join('');
                verification();
                validationField.innerHTML = "Validé";
                return true;
            }
        </script>
        <style>
            body{
                background-image: url(/MagasinVetements/images/login_background.jpg);
            }
        </style>
        <title>Inscription</title>
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
                        <span col-1><a  class="nav-link"  href="login.jsp">Connection</a></span>
                        <span col-1><a class="nav-link" href="/MagasinVetements/admin/login.jsp">admin</a></span>
                    </ul>
                </div>
            </div>
        </nav>
        
        <div class="container col-12" style="margin-top: 22vh;background-color: rgba(255, 255, 255, 0.4);padding: 2%;border-radius: 8px;">
            <form class="row g-3" method="POST" action="/MagasinVetements/Inscription" autocomplete="off">
                <div class="col-md-12">
                    <label for="inputEmail4" class="form-label">Email</label>
                    <input type="email" class="form-control" id="inputEmail4" name="email" onkeyup="verification()">
                </div>
                <div class="col-6">
                    <label for="inputAddress" class="form-label">Nom</label>
                    <input type="text" class="form-control" id="inputAddress" name="nom" onkeyup="verification()">
                </div>
                <div class="col-6">
                    <label for="inputAddress2" class="form-label">Prenom</label>
                    <input type="text" class="form-control" id="inputAddress2" name="prenom" onkeyup="verification()">
                </div>
                <div class="col-md-6">
                    <label for="inputPassword4" class="form-label">Mot de passe</label>
                    <input type="password" class="form-control" id="inputPassword4" onkeyup="validate()" name="password"><br>
                    <div id="validation-txt">
                    </div> 
                </div> 
                <div class="col-md-6">
                    <label for="inputPassword5" class="form-label">Retapez votre mot de passe</label>
                    <input type="password" class="form-control" id="inputPassword5" onkeyup="compare()">
                    <div id="validation2-txt">
                    </div> 
                </div>
                <div class="col-md-4">
                    <input type="radio" name="sexe" id="radioHomme" value="masculin" onclick="verification()">
                    <label for="radioHomme">Homme</label>|
                    <input type="radio" name="sexe" id="radioFemme" value="feminin" onclick="verification()">
                    <label for="radioHomme">Femme</label>
                </div>
                <%  if (request.getAttribute("error") != null) {  %>
                <div class="col-12" tyle="text-align: center;color: red;">
                    Email déja utilisé
                </div>
                <% }%>
                <div class="col-12">
                    <button id="validation"  type="submit" class="btn btn-primary" disabled>S'incrire</button>
                </div>
            </form>
        </div>
    </body>
</html>
