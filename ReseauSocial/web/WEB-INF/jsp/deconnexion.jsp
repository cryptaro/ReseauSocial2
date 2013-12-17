<%-- 
    Document   : deconnexion
    Created on : 15 déc. 2013, 20:17:53
    Author     : Cryptaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Deconnexion</title>
        <link rel="stylesheet" href="css/monStyle.css">
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        <div class="content" id="deconnexion">
            <p>
                Souhaitez vous vraiment vous déconnecter?
                <FORM method="POST" ACTION="">            
                    <INPUT class="bouton" Type=submit VALUE="Déconnexion" >
                </FORM>
            </p>
        </div>
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>
