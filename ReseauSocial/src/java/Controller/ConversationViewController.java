/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.ConversationEntity;
import DAO.MessageEntity;
import DAO.UtilisateurEntity;
import Service.ConversationService;
import Service.HtmlUtils;
import Service.MessageService;
import Service.UtilisateurService;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Cryptaro
 */
@Controller
@RequestMapping("/conversationView.htm")
public class ConversationViewController {
    @Autowired
    ConversationService serviceConvers;
    
    @Autowired
    MessageService serviceMsg;
    
    @Autowired
    UtilisateurService serviceUser;
    
    public ConversationViewController() {
    }
   
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView initAjoutConversation(HttpServletRequest request, HttpServletResponse response){
        UtilisateurEntity user=null;
        HttpSession session = request.getSession(false);
        if(session==null || (user=(UtilisateurEntity)session.getAttribute(UtilisateurEntity.nameInSession)) == null) {
            session.invalidate();
            return new ModelAndView("accueil");
        }
        session.setAttribute(UtilisateurEntity.nameInSession, 
            serviceUser.maj(user)
        );
        String selectedConvers = request.getParameter("id_convers");
        ModelAndView mv = new ModelAndView("conversationView");
        
        user = serviceUser.getUserByLogin(user.getLogin());
        
        Long conversSelectedId;
        try {
            conversSelectedId = new Long(selectedConvers);
        } catch (Exception e){
           return mv;
        }
        
        String action = request.getParameter("action");
        if(action.compareToIgnoreCase("ajouter_Message")==0){
            String newMsg = HtmlUtils.toHtml(request.getParameter("valueNewMessage"));
            if(newMsg.replaceAll(" ", "").length()!=0){
                ConversationEntity conv = serviceConvers.getConversationById(conversSelectedId);
                if(conv!=null) {
                    MessageEntity message = new MessageEntity(newMsg, user, new Date(), conv);
                    serviceMsg.ecrire(message);
                    message.getConversation().getListMessage().add(message);
                    serviceConvers.update(conv);
                }
                 mv.addObject("selectedConversation", conv);
            }
            //mv.addObject("errorConversation", "ajout message done in " + conversSelectedId);
           
        } else if(action.compareToIgnoreCase("Ajouter_Conversation")==0){
            ConversationEntity convers = new ConversationEntity(user);
            serviceConvers.create(convers);
            
        } else if(action.compareToIgnoreCase("ajouter_Participants")==0){
            String msgAjout = "ajout user done of :";
            String newParticipants = request.getParameter("ajoutParticipants");
            UtilisateurEntity tmpuser = null;
            ConversationEntity conv = serviceConvers.getConversationById(conversSelectedId);
            for(String s: newParticipants.split(";")){
                tmpuser = serviceUser.getUserByLogin(s.replaceAll(" ",""));
                if(tmpuser!=null){
                    conv.getParticipants().add(tmpuser);
                    msgAjout += tmpuser.getLogin() +"; ";
                }
                tmpuser = null;
            }
            serviceConvers.update(conv);
            mv.addObject("errorConversation", msgAjout +" # " + conv);
            mv.addObject("selectedConversation", conv);
        }
        modifyMv(selectedConvers, mv, request, user);
        return mv;
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView init(@RequestParam(value="conversation", required = false) String selectedConversation,
            HttpServletRequest request, HttpServletResponse response){
        UtilisateurEntity user=null;
        HttpSession session = request.getSession(false);
        if(session==null || (user=(UtilisateurEntity)session.getAttribute(UtilisateurEntity.nameInSession)) == null) {
            session.invalidate();
            return new ModelAndView("accueil");
        }
        if(user!=null)
            session.setAttribute(UtilisateurEntity.nameInSession, 
                    serviceUser.maj(user)
            );
        ModelAndView mv = new ModelAndView("conversationView");
        if(selectedConversation==null || selectedConversation == ""){
            modifyMv("", mv, request, user);
            return mv;
        }
        user = serviceUser.getUserByLogin(user.getLogin());
        modifyMv(selectedConversation, mv, request, user);
        //mv.addObject("errorConversation","conversion num" + selectedConversation + " has been printed");
        
        return mv;
    }
    
    
    private void modifyMv(String selectedConvers, ModelAndView mv, HttpServletRequest request, UtilisateurEntity user){
        Long conversSelectedId;
       
        try {
            conversSelectedId = new Long(selectedConvers);
        } catch (Exception e){
            List<ConversationEntity> visibleConvers = serviceConvers.getVisibleConversation(user);
            
            if(visibleConvers.size()>0) {
                mv.addObject("conversations", visibleConvers);
                if(visibleConvers.size()>0){
                    mv.addObject("selectedConversation", serviceConvers.getConversationById(visibleConvers.get(0).getId()));
                    //mv.addObject("messagesSelectedConversation", serviceMsg.getMsgByConversation(visibleConvers.get(0)));
                } else {
                    mv.addObject("selectedConversation", null);
                    //mv.addObject("messagesSelectedConversation", null);
                }
            }
            return;
        }
       
        mv.addObject("conversations", serviceConvers.getVisibleConversation(user));
        /*mv.addObject("errorConversation","taille des conversation trouvée: " + serviceConvers.getVisibleConversation(user).size()+"</br>"
                + "USER EST:" + user);*/
         //mv.addObject("errorConversation","id convers in function = " + conversSelectedId);
        try {
            ConversationEntity selectedConversationEntity = serviceConvers.getConversationById(conversSelectedId);
            //mv.addObject("errorConversation","id convers in function = " + selectedConversationEntity.getId());
            mv.addObject("selectedConversation", selectedConversationEntity);
            //mv.addObject("messagesSelectedConversation", serviceMsg.getMsgByConversation(selectedConversationEntity));
        } catch(Exception e){   // conversation non trouvée
             mv.addObject("selectedConversation", null);
             //mv.addObject("messagesSelectedConversation", null);
        }
       
    }
}
