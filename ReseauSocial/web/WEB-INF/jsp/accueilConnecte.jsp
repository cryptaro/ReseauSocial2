<%-- 
    Document   : accueilConnecte
    Created on : 15 déc. 2013, 18:14:34
    Author     : Cryptaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Social Network</title>
        <link rel="stylesheet" href="css/monStyle.css">
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        <div class="content">
            <h1>Vous êtes connecté ${userName}</h1>
        </div>
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>
