<%-- 
    Document   : entetePage
    Created on : 15 déc. 2013, 21:49:52
    Author     : Cryptaro
--%>

<%@page import="Service.ConversationService"%>
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
        
        <div id="menuLogEntete">
            <a id="messagesEntete" href="conversationView.htm"></a>        
            <div id="login">Bonjour <%=_user.getLogin()%></div>    
        </div>
    </div>
    
<%} else {%>
    <div id="entete" class="container">
        <div id="menuLogEntete">
            <a id="login" href="connexion.htm">Connexion</a> 
        </div>
    </div>
<%}%>