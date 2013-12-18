<%-- 
    Document   : wellcome
    Created on : 8 oct. 2013, 17:39:25
    Author     : ctran
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%
    UtilisateurEntity user = (UtilisateurEntity) session.getAttribute(UtilisateurEntity.nameInSession); 
    
    if(user==null){
     session.invalidate();
%>
    <jsp:forward page="accueil.htm"/>
<%
    }
    List<UtilisateurEntity> resultList = (List < UtilisateurEntity > )request.getAttribute("resultatRecherche");
    if(resultList==null){
        resultList = new ArrayList<UtilisateurEntity>();
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Resultat Recherche</title>
        <link rel="stylesheet" href="css/monStyle.css">
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        
        <div class="content">
            <h1>Résultat </h1>
            
            <% 
                if( resultList.size()>0 
                    && !(resultList.size()==1 && resultList.get(0).getLogin().compareTo(user.getLogin())==0)
                   ) {
                    for(UtilisateurEntity u: resultList) {
                        if(u.getLogin().compareTo(user.getLogin())!=0) {
            %>
                        <div class="utilisateurEntity">
                            <div class="utilisateurEntity_Info">
                                <a href="profileView.htm?profile=<%=u.getLogin()%>"><%=u.getLogin()%></a>
                            </div>
                            <div class="utilisateurEntity_Info">
                                <%=u.getNom() %>
                            </div>
                            <div class="utilisateurEntity_Info">
                                <%=u.getPrenom() %>
                            </div>
                            <div class="utilisateurEntity_Info">
                                <%= (u.getSexe()?"femme":"homme") %>
                            </div>
                        </div></br>
            <%          }
                    }
                } else {
            %>  
            <div class="utilisateurEntity">
                No user Founded.
            </div>
            <%  }%> 
        </div>
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>

