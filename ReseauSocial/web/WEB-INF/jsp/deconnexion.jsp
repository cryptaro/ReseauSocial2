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
    </head>
    <body>
        <%@ include file="entetePage.jsp" %>
        
        <p>
            Souhaitez vous vraiment vous déconnecter?
            <FORM method="POST" ACTION="">            
                <INPUT Type=submit VALUE="déconnexion">
            </FORM>
        </p>
        
        <%@ include file="piedPage.jsp" %>
    </body>
</html>
