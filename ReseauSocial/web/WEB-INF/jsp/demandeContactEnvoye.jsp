<%-- 
    Document   : demandeContactEnvoye
    Created on : 17 déc. 2013, 19:41:05
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
            <h1> Demande de contact en cours&nbsp:</h1>
            <% if(user!=null){ 
                  if(user.getDemandesContact() != null || user.getDemandesContact().size() != 0) {%>
                   <FORM name="formulaire" method="POST" ACTION=""> 
                       <TABLE>
            <%         int cpt = 0;
                       Iterator i = user.getDemandesContact().iterator();
                       UtilisateurEntity u;
                       while(i.hasNext()){
                           u = (UtilisateurEntity) i.next();          %>
                           <TR>
                               <TD><label for="login_new_demande" <%=cpt%> > <%=u.getLogin()%> </label> </TD>
                               <TD><INPUT type="checkbox" name="choix<%=cpt%>" value="<%=u.getLogin()%>"></TD>
                           </TR>
            <%             cpt++;
                       } %>
                       </TABLE>
                       <INPUT class="bouton" Type=submit VALUE="Annuler demande de contact"  style="width:230px">
                     </FORM>  
            <%    }else{%>
                  <p>Aucune demande en cours</p>   
            <%     }%>
            <p> ${msg} </p>
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