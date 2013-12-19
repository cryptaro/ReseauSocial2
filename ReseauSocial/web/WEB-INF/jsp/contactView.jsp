<%-- 
    Document   : AmiView
    Created on : 18 déc. 2013, 18:41:50
    Author     : CTam
--%>

<%@page import="java.util.Iterator"%>
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
        <link rel="stylesheet" href="css/monStyle.css">
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        
        <div class="content" id="accueil">
            <h1> Vos contacts&nbsp:</h1>
            <% if(user!=null){ 
                  if(user.getListeContact() != null && user.getListeContact().size() != 0) {%>
                   <FORM name="formulaire" method="POST" ACTION=""> 
                       <TABLE>
            <%         int cpt = 0;
                       Iterator i = user.getListeContact().iterator();
                       UtilisateurEntity u;
                       while(i.hasNext()){
                           u = (UtilisateurEntity) i.next();        %>
                           <TR>
                               <TD><a href="profileView.htm?profile=<%= u.getLogin()%>"><%= u.getLogin()%></a></TD>
                               <TD><INPUT type="checkbox" name="choix<%=cpt%>" value="<%=u.getLogin()%>"></TD>
                           </TR>
            <%             cpt++;
                       } %>
                       </TABLE>
                       <INPUT class="bouton" Type=submit VALUE="Supprimer contact">
                   </FORM>  
            <%    }else{%>
                  <p>Vous n'avez pas de contacts</p>   
            <%     }%>
            <p> ${msg} </p>
            <p> ${msg2} </p>
            <%
               }else{ 
            %>
            %>
            <p>
                <a href="inscription.htm">inscription</a>
            </p>
            
            <% }%>
        </div>
        
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>