<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
    </head>
    <body>
        <%@ include file="entetePage.jsp" %>
        
        <h1>INSCRIPTION</h1>
        <FORM method="POST" ACTION="">            
            login : <INPUT Type=text value="${default_log}" Name=log></br>
            mot de passe : <INPUT Type=password value="${default_pwd}" Name=pwd></br>
            <INPUT Type=submit VALUE="OK">
        </FORM>
        <%@ include file="piedPage.jsp" %>
    </body>
</html>
