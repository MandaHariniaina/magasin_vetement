<%-- 
    Document   : accueil
    Created on : 9 juil. 2021, 08:01:09
    Author     : Manda
--%>
<%@page import="javax.servlet.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <title>Accueil</title>
</head>

<body>
    
    <% response.sendRedirect("accueil-dept.do"); %>
</body>

</html>