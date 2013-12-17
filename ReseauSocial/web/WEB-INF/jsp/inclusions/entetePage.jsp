<%-- 
    Document   : entetePage
    Created on : 15 déc. 2013, 21:49:52
    Author     : Cryptaro
--%>

<%@page import="DAO.UtilisateurEntity"%>

<% 
    UtilisateurEntity _user = (UtilisateurEntity) session.getAttribute(UtilisateurEntity.nameInSession);
    if(_user !=null) {
%>
    <div id="entete" class="container">
        
        <FORM method="GET" ACTION="afficherRecherche.htm">
            <div class="ensembleRecherche">
                <INPUT id="enteteRechercheChamp" Type=text Name=search placeholder="Rechercher">
                <INPUT id="enteteRechercheButton" Type=submit VALUE="" >
            </div>
        </FORM>
        
        <div id="login">Bonjour <%=_user.getLogin()%></div>        
    </div>
    
<%} else {%>
    <div id="entete" class="container">
        <div id="login"><a href="connexion.htm">Connexion</a></div>
    </div>
<%}%>