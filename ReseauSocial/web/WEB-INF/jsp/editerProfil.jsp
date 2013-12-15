<%-- 
    Document   : editerProfile
    Created on : 15 déc. 2013, 17:55:11
    Author     : Cryptaro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profil</title>
    </head>
    <body>
        <h1>Modifiez vôtre profil</h1>        
        <FORM method="POST" ACTION="">            
            login : <INPUT Type=text value="${default_log}" Name=log></br>
            mot de passe : <INPUT Type=password value="${default_pwd}" Name=pwd></br>
            </br>
            nom : <INPUT Type=text value=${default_name} Name=name</br>
            <INPUT Type=submit VALUE="OK">
        </FORM>
    </body>
</html>
