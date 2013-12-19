<%@page import="java.util.Iterator"%>
<<<<<<< HEAD
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
    function valide(typeEnvoi, valeur){
           // if(isShiftdown==0 && window.event.type=="keypress" && window.event.keyCode==13){// && document.ajoutMsg.newMsg.value !=""){
                  document.action.value=typeEnvoi+"";
                  document.valeur.value=valeur+"";
                  //document.formulaire.ajoutNewMsgButton.click() ;
                  document.getElementById("formulaire").submit() ;
           // }
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
            <FORM name="formulaire" id="formulaire" method="POST" ACTION=""> 
                
                <!--   DEBUT DE LA ZONE DE MODIF -->
                
           <h1> Profil de <%=user_profile.getLogin()%>&nbsp;:</h1>
            <% if(user!=null){ %>
                <% if(user.getLogin().compareTo(user_profile.getLogin())==0){ %>
                    <a href="editerProfil.htm" >Modifier</a></br>
                <% } %>
                <TABLE>
                    <TR>
                        <TD> 
                             <TABLE style="width: 600px">
                                <TR><TD style="height: 50px"> <p>Nom : <%= user_profile.getNom() %></p></TD> 
                                    <TD style="height: 50px"> <p>Prénom : <%= user_profile.getPrenom()%></p></TD>
                                </TR>
                                <TR><TD style="height: 50px"> <p>Date de naissance : <%= user_profile.getNaissance()%></p></TD> 
                                    <TD style="height: 50px"> Sexe : <%if(user_profile.getSexe()){%> homme <%}else{%>femme<%}%></TD>
                                </TR>
                                <TR>
                                    <TD rowspan="2" colspan="2" style="height: 50px"> <p>Description : <%= user_profile.getDescription()%> </p></TD>
                                </TR><TR></TR>
                            </TABLE>
                        </TD>
                        <TD> 
                            <TABLE style="padding-left: 200px">
                                 <TR><TD><h2>sa liste de contact&nbsp;:</h2></TR>
                            <%if(user_profile.getListeContact().size() == 0){%>
                                 <TR><TD><p> n'a pas de contact </p></TD> </TR>
                           <%}else{%>
                           <%Iterator it = user_profile.getListeContact().iterator();
                                while(it.hasNext()){  
                                    UtilisateurEntity u = (UtilisateurEntity) it.next();%>
                                    <TR><TD><a href="profileView.htm?profile=<%= u.getLogin()%>"><%= u.getLogin()%></a></TD> </TR>
                                    
                            <%  }
                            }%>
                            </TABLE>
                            
                        </TD>
                    </TR>
               </TABLE>
                  
                
                    <% if(user.getLogin().compareTo(user_profile.getLogin())!=0 && !deja_en_contact) { %>
                    <INPUT class="bouton" Type=submit VALUE="Ajouter en contact">
                    <% } %>
            <% } else{ %>
            <p>
                <a href="inscription.htm">inscription</a>
            </p>
            <% }%>
            
            <!--   FIN DE LA ZONE DE MODIF -->
            
            <div id="mur">
                <h1> Mur </h1>
                <% for(ConversationEntity c:conversations){ %>
                    <% if(c.getListMessage().size()>0) {%>
                <div class="conversationAffiche">
                    <div class="post">
                        <%= c.getListMessage().get(0).getMsg() %>
                    </div>
                    <ul><br/>
                    <% }
                    if(c.getListMessage().size()>1){
                       for(MessageEntity m: c.getListMessage().subList(1, c.getListMessage().size()-1)){ %>
                        <div class="comment">
                            <li><div class='messageOwner'><%= m.getOwner().getLogin() %> :</div>
                                <div class='messageMsg'><%= m.getMsg() %></div>
                            </li>
                        </div><br/>
                    <% } 
                    }%>
                    <% if(c.getListMessage().size()>0) {%>
                        <li><TEXTAREA name="valueComment" onkeypress="" class="newComment" placeholder="votre commentaire" rows="3" ></TEXTAREA></br>
                        <INPUT id="ajoutNewMsgButton" class="bouton" name="action" type="button"
                             onclick="  
                                        document.getElementById('valeur').value= '<%=c.getId()+"" %>)';
                                        this.form.submit();"  VALUE="envoyer">
                        </li>
                     <% } %>
                    </ul>
                    <% if(c.getListMessage().size()>0) {%>
                </div>
                <%     }
                   }// le for conversation %>
                   
                 <!--<INPUT class="text" name="action" id="action" Type=hidden VALUE="ajout_contact">-->
                 <INPUT class="text" name="valeur" id="valeur" Type=hidden VALUE="">
                 <INPUT class="cache" id="soumettre" Type=submit VALUE="">
                 </form>
            </div>
            <p>${msg}</p>
        </div>
        
        <%@ include file="inclusions/piedPage.jsp" %>
    </body>
</html>