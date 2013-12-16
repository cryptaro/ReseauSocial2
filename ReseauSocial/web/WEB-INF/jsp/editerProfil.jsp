<%-- 
    Document   : editerProfile
    Created on : 15 déc. 2013, 17:55:11
    Author     : Cryptaro
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.UtilisateurEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    UtilisateurEntity user = (UtilisateurEntity) session.getAttribute(UtilisateurEntity.nameInSession);

    String nomValue = request.getParameter("default_nom") == null ? "" : request.getParameter("default_nom");
    String prenomValue = request.getParameter("default_prenom")==null ? "":request.getParameter("default_prenom");
    String descriptionValue = request.getParameter("default_description") == null ? "": request.getParameter("default_description");
    String naissanceValue = request.getParameter("default_naissance") == null ? "": request.getParameter("default_naissance");
    
    int jour = user.getNaissance().getDate();
    int mois = user.getNaissance().getMonth()+1;
    naissanceValue = naissanceValue == "" ? (user.getNaissance().getYear()+1900) + "-" + (mois<10? "0":"")
            + mois + "-" + (jour<10? "0":"")+ jour : naissanceValue;
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profil</title>
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        
        <h1>Modifiez vôtre profil</h1>        
        <FORM method="POST" ACTION="">            
            <label>Mot de passe *: </label><INPUT Type=password value="" Name=pwd></br>
                 <style class="error">${msg_pwd}</style></br>
            <label>Nouveau mot de passe : </label><INPUT Type=password Name=pwd_new1></br>
            <label>Nouveau mot de passe (vérification) : </label><INPUT Type=password Name=pwd_new1>
                <style class="error">${msg_pwd_new}</style></br>
            <label>nom : </label><INPUT Type=text value="<%= (nomValue =="") ? user.getNom() : nomValue %>" Name=nom>
                <style class="error">${msg_nom}></style></br>
            <label>prenom : </label><INPUT Type=text value="<%= (prenomValue =="") ? user.getPrenom() : prenomValue %>" Name=prenom>
                <style class="error">${msg_prenom}</style></br>
            <label>naissance : </label><INPUT Type=date size="30"  value="<%= naissanceValue %>" Name=naissance>
                <style class="error">${msg_naissance}</style></br>
            <label>sexe : </label><SELECT name="sexe">
                <OPTION value="0" <%= !user.getSexe() ? "selected":"" %> >male</option>
                <OPTION value="1" <%= user.getSexe() ? "selected":"" %>>female</option>
            </SELECT>${msg_sexe}</style></br>
            <label>description : </label><INPUT Type=textarea rows="4" cols="100" value="<%= user.toString() %>" Name=description>
                <style class="error">${msg_decription}</style></br>

            <INPUT Type=submit VALUE="OK">
        </FORM>
        <p>
            ${msgEdition}
        </p>
        
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>
