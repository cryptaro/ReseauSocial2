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
        <title>Supression Compte</title>
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        
        <p>
            Souhaitez vous vraiment Supprimer votre compte ?
            <FORM method="POST" ACTION="">
                <INPUT Type=password placeholder="mot de passe" name="pwd">*</br>
                <INPUT Type=submit VALUE="suppression">
            </FORM>
            ${errorMsg}
        </p>
        
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>
