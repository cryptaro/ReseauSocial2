<%-- 
    Document   : profileView
    Created on : 18 déc. 2013, 19:17:05
    Author     : CTam
--%>



<%@page import="java.util.ArrayList"%>
<%@page import="DAO.MessageEntity"%>
<%@page import="java.util.List"%>
<%@page import="DAO.ConversationEntity"%>
<%@page import="org.springframework.web.bind.annotation.RequestParam"%>
<%@page import="DAO.UtilisateurEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    
    UtilisateurEntity user_profile = (UtilisateurEntity)request.getAttribute("profile");
    UtilisateurEntity user = (UtilisateurEntity) session.getAttribute(UtilisateurEntity.nameInSession);
    Boolean deja_en_contact = (Boolean)request.getAttribute("deja_en_contact");
    if(user_profile != null) {
        Cookie cook = new Cookie ("user_profile", user_profile.getLogin());
        cook.setMaxAge(3600);
        response.addCookie(cook);
    } else {
        user_profile = user;
    }
    
    List<ConversationEntity> conversations = (List<ConversationEntity>)request.getAttribute("conversationsMur");
    if(conversations==null){
        conversations = new ArrayList<ConversationEntity>();
    }
    
 %>
 
 <SCRIPT LANGUAGE="JavaScript">
    var isShiftdown = 0!=0;
    
    function valide(typeEnvoi, valeur){
            if(isShiftdown==0 && window.event.type=="keypress" && window.event.keyCode==13){// && document.ajoutMsg.newMsg.value !=""){
                  document.formulaire.action.value=typeEnvoi+"";
                  document.formulaire.valeur.value=valeur+"";
                  //document.formulaire.ajoutNewMsgButton.click() ;
                  document.formulaire.submit() ;
            }
    }
</SCRIPT>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">	
		
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
        <link rel="stylesheet" href="css/monStyle.css">
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        
        <div class="content" id="accueil">
            <h1> Profil de <%=user_profile.getLogin()%>&nbsp:</h1>
            <% if(user!=null){ %>
                <% if(user.getLogin().compareTo(user_profile.getLogin())==0){ %>
                    <a href="editerProfil.htm" >Modifier</a></br>
                <% } %>
                <FORM name="formulaire" method="POST" ACTION=""> 
                    <% if(user.getLogin().compareTo(user_profile.getLogin())!=0 && !deja_en_contact) { %>
                    <INPUT class="bouton" Type=submit VALUE="Ajouter en contact">
                    <% } %>
                </FORM> 
            <% } else{ %>
            <p>
                <a href="inscription.htm">inscription</a>
            </p>
            <% }%>
            <div id="mur">
                <h1> Mur </h1>
                <FORM name="formulaire"  method="POST" ACTION="" > 
                <% for(ConversationEntity c:conversations){ %>
                    <% if(c.getListMessage().size()>0) {%>
                <div class="conversationAffiche">
                    <div class="post">
                        <%= c.getListMessage().get(0).getMsg() %>
                    </div>
                    <ul>
                    <% }
                    if(c.getListMessage().size()>1){
                       for(MessageEntity m: c.getListMessage().subList(1, c.getListMessage().size()-1)){ %>
                        <div class="comment">
                            <li><div class='messageOwner'><%= m.getOwner().getLogin() %> :</div>
                                <div class='messageMsg'><%= m.getMsg() %></div>
                            </li>
                        </div>
                    <% } 
                    }%>
                    <TEXTAREA name="valueNewMessage" autofocus="true" onkeypress="" class="newMessagesInConversation" 
                                       placeholder="votre message" rows="4" ></TEXTAREA>
                    <INPUT id="ajoutNewMsgButton" class="bouton" name="action2"
                         onclick="document.formulaire.action.value='ajouter_Message';document.formulaire.submit();" Type=submit VALUE="envoyer">
                    </ul>
                    <% if(c.getListMessage().size()>0) {%>
                </div>
                <%     }
                   }// le for conversation %>
                   
                 <INPUT class="bouton" name="action" id="action"Type=hidden VALUE="Ajouter_Conversation">
                 </form>
            </div>
            <p>${msg}</p>
        </div>
        
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>
