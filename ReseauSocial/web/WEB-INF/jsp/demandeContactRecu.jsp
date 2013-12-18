<%-- 
    Document   : demandeContactReçu
    Created on : 17 déc. 2013, 19:41:21
    Author     : CTam
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@page import="DAO.UtilisateurEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
     UtilisateurEntity user = (UtilisateurEntity) session.getAttribute(UtilisateurEntity.nameInSession);
     List<UtilisateurEntity> contacts_possible = null;
     if(user != null)
        contacts_possible = (List<UtilisateurEntity>)request.getAttribute("contacts_possible");
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
            <h1> Les demandes de personnes voulant être en contact avec moi&nbsp:</h1>
            
            <FORM>

</FORM>
            
            <% if(user!=null){ 
                  if(contacts_possible!= null && contacts_possible.size() != 0) {%>
                   <FORM name="formulaire" method="POST" ACTION=""> 
                       <TABLE>
            <%         int cpt = 0;
                       Iterator i = contacts_possible.iterator();
                       UtilisateurEntity u;
                       while(i.hasNext()){
                           u = (UtilisateurEntity) i.next();          %>
                           <TR>
                               <TD><label for="login_new_demande" <%=cpt%> > <%=u.getLogin()%> voudrait entrer en contact avec vous : </label> </TD>
                               <TD><INPUT type= "radio" name="contact<%=cpt%>" value="accepter"> accepter</TD>
                               <TD><INPUT type= "radio" name="contact<%=cpt%>" value="decliner"> decliner</TD>
                               <TD><INPUT type= "radio" name="contact<%=cpt%>" value="voir_plus_tard" checked>voir plus tard</TD>
                           </TR>
            <%             cpt++;
                       } %>
                       </TABLE>
                       <INPUT class="bouton" Type=submit VALUE="Valider">
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