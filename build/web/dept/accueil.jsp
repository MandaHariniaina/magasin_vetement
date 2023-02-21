<%-- 
    Document   : accueil
    Created on : 21 oct. 2021, 20:52:24
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Formulaire ajout de departement</h1>
        <form action="/MagasinVetements/ajouter-dept.do" method="POST">
            <label for="dept.id">Id : </label>
            <input type="number" name="dept.id" id="dept.id">
            <br>
            <label for="dept.id">Adresse : </label>
            <input type="text" name="dept.adresse" id="dept.adresse">
            <input type="submit" value="inserer">
        </form>
    </body>
</html>
