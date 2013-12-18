<%-- 
    Document   : profileView
    Created on : 18 déc. 2013, 19:17:05
    Author     : CTam
--%>



<%@page import="org.springframework.web.bind.annotation.RequestParam"%>
<%@page import="DAO.UtilisateurEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    UtilisateurEntity user_profile = (UtilisateurEntity)request.getAttribute("profile");
    UtilisateurEntity user = (UtilisateurEntity) session.getAttribute(UtilisateurEntity.nameInSession);
    Boolean deja_en_contact = (Boolean)request.getAttribute("deja_en_contact");
    Cookie cook = new Cookie ("user_profile", user_profile.getLogin());
    cook.setMaxAge(3600);
    response.addCookie(cook);
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">	
		
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
        <link rel="stylesheet" href="css/monStyle.css">
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        
        <div class="content" id="accueil">
            <h1> Profile de <%=user_profile.getLogin()%>&nbsp:</h1>
            <% if(user!=null){ %>
            <FORM name="formulaire" method="POST" ACTION=""> 
                <INPUT class="bouton" Type=submit VALUE="Ajouter en contact">
            </FORM> 
            <% }else{%>
            <p>
                <a href="inscription.htm">inscription</a>
            </p>
            <% }%>
            <p>${msg}</p>
        </div>
        
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>
