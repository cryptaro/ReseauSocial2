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
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        
        <h1>Please connect</h1>
        <FORM method="POST" ACTION="">            
            login : <INPUT Type=text Name=log></br>
            mot de passe : <INPUT Type=password Name=pwd></br>
            <INPUT Type=submit VALUE="OK">
        </FORM>
        
        <p class="error">
            ${errorMsg}
        </p>
        
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>
