<%-- 
    Document   : piedPage
    Created on : 15 déc. 2013, 22:14:14
    Author     : Cryptaro
--%>

<%@page import="DAO.UtilisateurEntity"%>
<p>
    <a href="accueil.htm">accueil</a></br>
    <% if(session.getAttribute(UtilisateurEntity.nameInSession)!=null) {%>
        <a href="editerProfil.htm" >éditer profil</a></br>
        <a href="deconnexion.htm">déconnexion</a>
    <% }%>
</p>