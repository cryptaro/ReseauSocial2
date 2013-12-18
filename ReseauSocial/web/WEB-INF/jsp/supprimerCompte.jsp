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
        <link rel="stylesheet" href="css/monStyle.css">
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        
        <div class="content" id="supprUser">
            <h1>Souhaitez vous vraiment Supprimer votre compte ?</h1>
            <p>
                
                <FORM method="POST" ACTION="">
                    <INPUT Type=password placeholder="mot de passe" name="pwd">*</br>
                    <INPUT Type=submit class="bouton" VALUE="suppression">
                </FORM>
                ${errorMsg}
            </p>
        </div>
        
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>
