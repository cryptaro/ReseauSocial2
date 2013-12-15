<%-- 
    Document   : editerProfile
    Created on : 15 déc. 2013, 17:55:11
    Author     : Cryptaro
--%>

<%@page import="DAO.UtilisateurEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    UtilisateurEntity user = (UtilisateurEntity) session.getAttribute(UtilisateurEntity.nameInSession);
 
 %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profil</title>
    </head>
    <body>
        <%@ include file="entetePage.jsp" %>
        
        <h1>Modifiez vôtre profil</h1>        
        <FORM method="POST" ACTION="">            
            Mot de passe *: <INPUT Type=password value="" Name=pwd></br>
                 <style class="error">${msg_pwd}</style></br>
            Nouveau mot de passe : <INPUT Type=password Name=pwd_new1></br>
            Nouveau mot de passe (vérification) : <INPUT Type=password Name=pwd_new1>
                <style class="error">${msg_pwd_new}</style></br>
            nom : <INPUT Type=text value="${default_nom}" Name=nom>
                <style class="error">${msg_nom}></style></br>
            prenom : <INPUT Type=text value="${default_prenom}" Name=prenom>
                <style class="error">${msg_prenom}</style></br>
            naissance : <INPUT Type=date value=${default_naissance} Name=naissance>
                <style class="error">${msg_naissance}</style></br>
            sexe : <SELECT name="sexe" size=${default_sexe}>
                <OPTION>male
                <OPTION>female
            </SELECT>${msg_sexe}</style></br>
            description : <INPUT Type=textarea rows="4" cols="100" value="${default_sexe}" Name=description>
                <style class="error">${msg_decription}</style></br>
            <INPUT Type=submit VALUE="OK">
        </FORM>
        <p>
            ${msgEdition}
        </p>
        
        <%@ include file="piedPage.jsp" %>
    </body>
</html>
