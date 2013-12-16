<%-- 
    Document   : accueil
    Created on : 15 déc. 2013, 16:40:40
    Author     : Cryptaro
--%>

<%@page import="DAO.UtilisateurEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UtilisateurEntity user = (UtilisateurEntity) session.getAttribute(UtilisateurEntity.nameInSession); 
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">	
		
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        
        <h1> Bienvenue sur nôtre réseau social</h1>
        <% if(user==null) {%>
        <p>
            <a href="inscription.htm">inscription</a></br>
            <a href="connexion.htm">connexion</a>
        </p>
        <% } else {%>
        <p>
            Bienvenue sur votre mur !!
        </p>
        <% }%>
        
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>