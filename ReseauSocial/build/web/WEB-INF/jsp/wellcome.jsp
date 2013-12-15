<%-- 
    Document   : wellcome
    Created on : 8 oct. 2013, 17:39:25
    Author     : ctran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file="entetePage.jsp" %>
    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Wellcome</title>
    </head>
    <body>
        <%@ include file="entetePage.jsp" %>
        
        <h1>Wellcome ${wellcomeMessage}</h1>
        
        <%@ include file="piedPage.jsp" %>
    </body>
</html>

