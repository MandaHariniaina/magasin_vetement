<%-- 
    Document   : lister
    Created on : 24 août 2021, 16:04:17
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
        <h1>Lister</h1>
        <p><%=String.valueOf(data.get(1)) %></p>
    </body>
</html>
