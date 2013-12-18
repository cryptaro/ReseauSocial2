<%-- 
    Document   : wellcome
    Created on : 8 oct. 2013, 17:39:25
    Author     : ctran
--%>

<%@page import="DAO.MessageEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="DAO.ConversationEntity"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UtilisateurEntity user = (UtilisateurEntity) session.getAttribute(UtilisateurEntity.nameInSession);
    if(user==null){
%>
    <jsp:forward page="accueil.htm"/>
<%
    }
    
    List<ConversationEntity> conversations = (List<ConversationEntity>)request.getAttribute("conversations");
    if(conversations==null){
        conversations = new ArrayList<ConversationEntity>();
    }
    
    ConversationEntity selectedConversation = (ConversationEntity)request.getAttribute("selectedConversation");
    if(selectedConversation==null && conversations.size()>0){
        selectedConversation = conversations.get(0);
    }
    List<MessageEntity> messagesSelectedConversation = (List<MessageEntity>)request.getAttribute("messagesSelectedConversation");
    if(messagesSelectedConversation==null){
        messagesSelectedConversation = new ArrayList<MessageEntity>();
    }
%>

<SCRIPT LANGUAGE="JavaScript">
    function valideMessage(){   
            if(window.event.type=="keypress" && window.event.keyCode==13){// && document.ajoutMsg.newMsg.value !=""){
                  //document.ajoutMsg.newMsg.value="coucou";
                  document.ajoutMsg.submit();
            }
    }
</SCRIPT>

<!DOCTYPE html>
<html>    
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Conversations</title>
        <link rel="stylesheet" href="css/monStyle.css">
    </head>
    <body>
        <%@ include file="inclusions/entetePage.jsp" %>
        
        <div class="content">
            <h1>Conversations</h1>
            <div id="conversationView">
                
                <div id="conversationList">
                    <FORM name="ajoutConvers" method="POST" ACTION="" > 
                    <% if(conversations.size()==0){ %>
                        <input type="text" value="No Conversation here" class="conversationTitle" readonly="readonly" >
                    <% } else { 
                            for(ConversationEntity c: conversations) {
                    %>
                            <ul>
                                <li><a href="conversationView.htm?conversation<%= c.getId() %>">
                                        <input type="text" value="<% for(UtilisateurEntity u: selectedConversation.getParticipants()) { %>
                                               <%= u.getLogin() + " ;" %>
                                        <% } %>" class="conversationTitle" readonly="readonly" >

                                    </a>
                                </li>
                            </ul>
                    <%      }
                        } %>
                        <INPUT class="bouton" Type=submit VALUE="+">
                    </form>
                </div>

                <div id="messageInConversationList">
                    <FORM name="ajoutMsg" method="POST" ACTION="" onSubmit="return valideMessage(this.form);"> 
                    <% if(conversations.size()>0) { %>
                    <ul>
                    <% for(MessageEntity m: messagesSelectedConversation) { %>
                        <li> 
                            <!-- si c'est un message de l'utilisateur qui est connecté, la classe est : messagesInConversation_owner  -->
                            <input type="text" class="messagesInConversation<%= user.getLogin().compareTo(m.getOwner().getLogin())==0 ? "_owner" :"" %>" 
                                   readonly="readonly" value="<%= m.getMsg() %>">
                        </li>
                        <input type="text" id="newMsg" onkeydown="valideMessage();"class="messagesInConversation"  placeholder="votre message">
                     <% } %>
                     </ul>
                     <% } else {%>
                        <input type="text" class="messagesInConversation" readonly="readonly" value="no messages">
                     <% } %>
                     <input type="text" id="newMsg" onkeypress="valideMessage();"class="newMessagesInConversation"  placeholder="votre message">
                    </form>
                </div>
            </div>
            <div class="error">${errorConversation}</div>
        </div>
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>

