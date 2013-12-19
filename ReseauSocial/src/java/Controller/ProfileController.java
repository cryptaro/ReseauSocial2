/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.ConversationEntity;
import DAO.MessageEntity;
import DAO.UtilisateurEntity;
import DAO.VisibilityEnum;
import Service.ConversationService;
import Service.HtmlUtils;
import Service.MessageService;
import Service.UtilisateurService;
import java.util.Date;
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
 * @author CTam
 */

@Controller
@RequestMapping("/profileView.htm")
public class ProfileController {
    @Autowired
    UtilisateurService serviceUser;
    
    @Autowired
    ConversationService serviceConversation;
    
    @Autowired
    MessageService serviceMsg;
    
    
    public ProfileController() {
    }
   
    @RequestMapping(method=RequestMethod.GET)
    protected ModelAndView init(
            @RequestParam(value="profile", required = false) String profile,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        ModelAndView mv = new ModelAndView("profileView");
        UtilisateurEntity user = null;
        if(session!=null && (user=(UtilisateurEntity)session.getAttribute(UtilisateurEntity.nameInSession))!=null){
            session.setAttribute(UtilisateurEntity.nameInSession, serviceUser.maj(user) );
            if(profile==null || profile.length()==0){
                UtilisateurEntity user_profil = serviceUser.getUserByLogin(user.getLogin());
                mv.addObject("profile", user_profil);
                mv.addObject("deja_en_contact", serviceUser.peutDemanderContact(user, user_profil));
                mv.addObject("conversationsMur", serviceConversation.getNotChatConversation(user_profil));
            } else {
                UtilisateurEntity user_profil = serviceUser.getUserByLogin(profile);
                mv.addObject("profile", user_profil);

                mv.addObject("deja_en_contact", serviceUser.peutDemanderContact(user, user_profil));
                mv.addObject("conversationsMur", serviceConversation.getNotChatConversation(user_profil));

            }
        }
        return mv;
    }
       
    @RequestMapping(method=RequestMethod.POST)
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        UtilisateurEntity user;
        if(session!=null && (user=(UtilisateurEntity)session.getAttribute(UtilisateurEntity.nameInSession))!=null){
            session.setAttribute(UtilisateurEntity.nameInSession, serviceUser.maj(user));
            ModelAndView mv = new ModelAndView("profileView"); // va chercher page wellcome.jsp
           
            String action = request.getParameter("action");
            int i = 0;
            while(i<request.getCookies().length && !request.getCookies()[i].getName().equals("user_profile")){
                ++i;
            }
            UtilisateurEntity u = serviceUser.getUserByLogin(request.getCookies()[i].getValue());;
            
            if(action.compareToIgnoreCase("ajout_contact")==0){
                serviceUser.demanderContact(user, u);
                session.setAttribute(UtilisateurEntity.nameInSession, user);
                
                mv.addObject("msg", "demande de contact envoyée");
                mv.addObject("profile", u);
                mv.addObject("deja_en_contact", serviceUser.peutDemanderContact(user, u));
                
            } else if(action.compareToIgnoreCase("addComment")==0){
                
                String idConversString = request.getParameter("valeur");
                 //mv.addObject("msg", "message : " + " ## convers : "+idConversString);
                Long idConvers;
                try{
                    idConvers = new Long(idConversString);
                }catch(Exception e){
                    mv.addObject("conversationsMur", serviceConversation.getNotChatConversation(u));
                    return mv;
                }
                String comment = HtmlUtils.toHtml(request.getParameter("valueComment"+idConversString));
                ConversationEntity conversation = serviceConversation.getConversationById(idConvers);
                if(conversation==null){
                    mv.addObject("conversationsMur", serviceConversation.getNotChatConversation(u));
                    return mv;
                }
                
                MessageEntity mesg = new MessageEntity(comment, user, new Date(), conversation);
                serviceMsg.ecrire(mesg);
                mv.addObject("conversationsMur", serviceConversation.getNotChatConversation(u));
                //mv.addObject("msg", "message : " + comment + " ## convers : "+idConvers);
                
                
            } else if(action.compareToIgnoreCase("nouveauPost")==0){
                
                String userWallLogin = request.getParameter("valeur");
                UtilisateurEntity userProfil= serviceUser.getUserByLogin(userWallLogin);
                if(userProfil!=null){
                    String comment = request.getParameter("nouveau_Post");
                    ConversationEntity conversation = new ConversationEntity(userProfil, VisibilityEnum.Public);
                    //userProfil car est créé sur son mur a lui !!
                    serviceConversation.create(conversation);
                    MessageEntity mesg = new MessageEntity(comment, user, new Date(), conversation);
                    serviceMsg.ecrire(mesg);
                    

                    mv.addObject("conversationsMur", serviceConversation.getNotChatConversation(u));
                    //mv.addObject("msg", "message : " + comment + " ## convers : "+conversation.getId());
                }
            }
            return mv;
        } else {
            session.setAttribute(UtilisateurEntity.nameInSession, null);
            session.invalidate();
            return new ModelAndView("profileView");
        }
        
    }
    
}
