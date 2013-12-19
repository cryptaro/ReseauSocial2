<%-- 
    Document   : wellcome
    Created on : 8 oct. 2013, 17:39:25
    Author     : ctran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wellcome</title>
        <link rel="stylesheet" href="css/monStyle.css">
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        
        <div class="content">
            <h1>Wellcome ${wellcomeMessage}</h1>
        </div>
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>

