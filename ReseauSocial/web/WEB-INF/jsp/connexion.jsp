<%-- 
    Document   : connexion
    Created on : 15 déc. 2013, 16:53:34
    Author     : Cryptaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
        <link rel="stylesheet" href="css/monStyle.css">
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        
        <div class="content" id="connexion">
            <h1>Please connect</h1>
            <FORM method="POST" ACTION="">     
                <label for="log"> login :</label>
                <INPUT Type=text Name=log placeholder="login" autofocus="true"></br>
                
                <label for="pwd">mot de passe : </label>
                <INPUT Type=password Name=pwd placeholder="password"></br>
                
                <INPUT class=bouton Type=submit VALUE="OK">
            </FORM>

            <p class="error">
                ${errorMsg}
                <a href="inscription.htm">inscription</a></br>
            </p>
        </div>
        
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>
