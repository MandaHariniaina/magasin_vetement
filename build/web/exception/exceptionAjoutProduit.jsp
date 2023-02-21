<%-- 
    Document   : errorLogin
    Created on : 27 juil. 2021, 10:57:12
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
    </head>
    <body>
        <% Exception ex = (Exception)request.getAttribute("exception"); %>
        <h2>Message d'erreur : <%=ex.getMessage() %></h2>
        <a href="/MagasinVetements/admin/login.jsp">Revenir a l'accueil</a>
    </body>
</html>
