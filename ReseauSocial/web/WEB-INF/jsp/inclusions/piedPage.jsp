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
        <a href="deconnexion.htm">déconnexion</a></br>
        <a href="demandeContactEnvoye.htm">mes demandes de contact en cours</a></br>
        <a href="demandeContactRecu.htm">les personnes voulant de moi comme amis </a></br>
    <% }%>
</p>