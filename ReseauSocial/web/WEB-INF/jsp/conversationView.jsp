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
    List<MessageEntity> messagesSelectedConversation = null;
    
    Long selectedConversationId;
    
    if(selectedConversation==null && conversations.size()>0){
        selectedConversation = conversations.get(0); 
    }
    if(messagesSelectedConversation==null){
        messagesSelectedConversation = new ArrayList<MessageEntity>();
    }
    
    
    if(selectedConversation!=null){
        selectedConversationId = selectedConversation.getId();
        messagesSelectedConversation = selectedConversation.getListMessage();
    } else {
        selectedConversationId =  new Long(0);
    }
%>

<SCRIPT LANGUAGE="JavaScript">
    var isShiftdown = 0!=0;
    
    function valide(typeEnvoi){
            if(isShiftdown==0 && window.event.type=="keypress" && window.event.keyCode==13){// && document.ajoutMsg.newMsg.value !=""){
                  document.formulaire.action.value=typeEnvoi+"";
                  //document.formulaire.ajoutNewMsgButton.click() ;
                  document.formulaire.submit() ;
            }
    }
    
    function testkey(){   
            if(window.event.type=="keydown" && window.event.keyCode==16){// && document.ajoutMsg.newMsg.value !=""){
                  isShitDown = 1;
                  //document.formulaire.newMsg.value+=" downn";
            }
            
            if(window.event.type=="keyup" && window.event.keyCode==16){// && document.ajoutMsg.newMsg.value !=""){
                 isShiftdown = 0;
                 //document.formulaire.newMsg.value+=" up";
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
    <body onkeydown="testkey();" onkeyup="testkey();">
        <%@ include file="inclusions/entetePage.jsp" %>
        
        <div class="content">
            <h1>Conversations</h1>
            <div id="conversationView">
                <FORM name="formulaire" modelAttribute="ajoutConvers" method="POST" ACTION="" > 
                    
                    <div id="conversationList">
                        <INPUT name="ajoutParticipants" onkeypress="valide('ajouter_Participants')" id="ajoutParticipants" Type=text VALUE=""
                               placeholder="ajoutez des participants ex: 'user1;user2 ; user3'">
                        <INPUT class="bouton" name="action" id="action"Type=hidden VALUE="Ajouter_Conversation">
                        <INPUT class="bouton" name="action2" Type=submit VALUE="Ajouter Conversation">
                        <% if(conversations.size()==0){ %>
                        <span id="conversationTitle_empty" >No Conversation here</SPAN>
                        <% } else { %>
                        <ul>
                         <%
                                for(ConversationEntity c: conversations) {
                        %>
                                
                                    <li><a href="conversationView.htm?conversation=<%= c.getId() %>">
                                            <span class="conversationTitle<%= c.getId()==selectedConversationId ? "_selected":""  %>" 
                                                  ><% for(UtilisateurEntity u: c.getParticipants()) { %><%= u.getLogin() + " ;" %><% } %></span>

                                        </a>
                                    </li>
                                
                        <%      }%>
                        </ul>
                         <%   } %>
                                
                               
                    </div>
                    <div id="messageInConversationListGlobal">
                        
                        <div id="messageInConversationList">
                            <% if(conversations.size()>0) { %>
                            <ul>
                            <% for(MessageEntity m: messagesSelectedConversation) { %>
                                <li class = "global_messagesInConversation<%= user.getLogin().compareTo(m.getOwner().getLogin())==0 ? "_owner" :"" %>"> 
                                    <!-- si c'est un message de l'utilisateur qui est connecté, la classe est : messagesInConversation_owner  -->
                                    <span class="messagesInConversation<%= user.getLogin().compareTo(m.getOwner().getLogin())==0 ? "_owner" :"" %>"><%= m.getMsg() %></SPAN></br>
                                </li></br>
                             <% } %>
                             </ul>
                             <% } else {%>
                             <span id="messagesInConversation_empty" > no messages</span>
                             <% } %>
                        </div></br>
                        <div id='basMessage'>
                            <TEXTAREA  id="newMsg" name="valueNewMessage" autofocus="true" onkeypress="" class="newMessagesInConversation" 
                                       placeholder="votre message" rows="4" ></TEXTAREA>
                            <INPUT id="ajoutNewMsgButton" class="bouton" name="action2"
                                 onclick="document.formulaire.action.value='ajouter_Message';document.formulaire.submit();" Type=submit VALUE="envoyer">
                        </div>
                    </div>
                    <input type="hidden" id="id_convers" value="<%=selectedConversationId %>" name="id_convers" >
                </form>
            </div>
            <div class="error">${errorConversation}</div>
        </div>
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>

