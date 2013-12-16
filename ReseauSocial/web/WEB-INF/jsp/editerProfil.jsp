<%-- 
    Document   : editerProfile
    Created on : 15 déc. 2013, 17:55:11
    Author     : Cryptaro
--%>

<%@page import="org.springframework.web.servlet.view.RedirectView"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="DAO.UtilisateurEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
    UtilisateurEntity user = (UtilisateurEntity) session.getAttribute(UtilisateurEntity.nameInSession);
    if(user==null){
%>
    <jsp:forward page="accueil.htm"/>
<%
    }
    String nomValue = request.getParameter("default_nom") == null ? "" : request.getParameter("default_nom");
    String prenomValue = request.getParameter("default_prenom")==null ? "":request.getParameter("default_prenom");
    String descriptionValue = request.getParameter("default_description") == null ? "": request.getParameter("default_description");
    String naissanceValue = request.getParameter("default_naissance") == null ? "": request.getParameter("default_naissance");
    
    nomValue = (nomValue == "") ? user.getNom(): nomValue;  
    descriptionValue = (descriptionValue =="") ? user.getDescription() : descriptionValue;    
    prenomValue = (prenomValue =="") ? user.getPrenom() : prenomValue;
    
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
        
        <h1>Modifiez vôtre profil : <%= user.getLogin()%></h1>
        <div id="formulaire">
            <FORM name="formulaire" method="POST" ACTION=""> 
                </label><INPUT Type=password value="" Name=pwd placeholder="mot de passe">*</br>
                     <style class="error">${msg_pwd}</style></br>
                     
                <INPUT Type=password Name=pwd_new1 placeholder="nouveau mot de passe"></br>
                <INPUT Type=password Name=pwd_new2 placeholder="nouveau mot de passe">(vérification)
                    <style class="error">${msg_pwd_new}</style></br></br>
                    
                <INPUT Type=text Name=nom <% if(prenomValue.replaceAll(" ", "").length()==0) { %> placeholder="nom"
                                                                <% }else { %> value="<%= nomValue %>" <% } %> >
                    <style class="error">${msg_nom}></style></br>
                    
                <INPUT Type=text Name=prenom <% if(nomValue.replaceAll(" ", "").length()==0) { %> placeholder="prenom"
                                                                <% }else { %> value="<%= prenomValue %>" <% } %> >
                    <style class="error">${msg_prenom}</style></br>
                    
                <label for="naissance">naissance : </label>
                <INPUT Type=date size="30"  value="<%= naissanceValue %>" Name=naissance id=naissance>
                    <style class="error">${msg_naissance}</style></br>
                    
                <label for="sexe">sexe : </label>
                <SELECT name="sexe" id="sexe">
                    <OPTION value="0" <%= !user.getSexe() ? "selected":"" %> >male</option>
                    <OPTION value="1" <%= user.getSexe() ? "selected":"" %> >female</option>
                </SELECT>${msg_sexe}</style></br>
                
                <TEXTAREA rows="4" cols="50" id="description" Name="description" 
                          <% if(descriptionValue.replaceAll(" ", "").length()==0) { %> placeholder="votre description"></TEXTAREA>
                                                                <% }else { %> ><%= descriptionValue %></TEXTAREA>" <% } %>
                
                    <style class="error">${msg_decription}</style></br>

                <INPUT Type=submit VALUE="OK">
            </FORM>
        </div>
        <p>
            ${msgEdition}
            
            <a href="supprimerCompte.htm">Supprimer compte</a>
        </p>
        
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>
