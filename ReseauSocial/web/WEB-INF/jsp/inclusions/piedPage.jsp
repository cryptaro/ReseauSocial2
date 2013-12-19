<%-- 
    Document   : piedPage
    Created on : 15 déc. 2013, 22:14:14
    Author     : Cryptaro
--%>

<%@page import="DAO.UtilisateurEntity"%>
<p>
    <!--<a href="accueil.htm">accueil</a></br>-->
    <% if(session.getAttribute(UtilisateurEntity.nameInSession)!=null) {%>
        <a href="demandeContactEnvoye.htm">mes demandes de contact</a></br>
        <a href="demandeContactRecu.htm">demandes de contact</a></br>
        <a href="contactView.htm">mes amis</a></br>
    <% }%>
</p>