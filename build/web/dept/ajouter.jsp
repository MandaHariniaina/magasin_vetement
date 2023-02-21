<%-- 
    Document   : ajouter
    Created on : 21 oct. 2021, 20:35:22
    Author     : ACER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <%@page import="java.util.HashMap" %>
    <% HashMap data = (HashMap)request.getAttribute("data"); %>
    <body>
        <h1>Ajouter dept</h1>
        adresse <%=data.get("adresse")%>
    </body>
</html>
