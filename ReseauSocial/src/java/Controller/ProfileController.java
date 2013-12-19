/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.UtilisateurEntity;
import Service.ConversationService;
import Service.UtilisateurService;
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
    UtilisateurService service;
    
    @Autowired
    ConversationService serviceC;
    
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
            session.setAttribute(UtilisateurEntity.nameInSession, 
                service.maj(user)
            );
            if(profile==null || profile.length()==0){
                UtilisateurEntity user_profil = service.getUserByLogin(user.getLogin());
                mv.addObject("profile", user_profil);
                mv.addObject("deja_en_contact", service.peutDemanderContact(user, user_profil));
                mv.addObject("conversationsMur", serviceC.getNotChatConversation(user_profil));
            } else {
                UtilisateurEntity user_profil = service.getUserByLogin(profile);
                mv.addObject("profile", user_profil);
                mv.addObject("deja_en_contact", service.peutDemanderContact(user, user_profil));
                mv.addObject("conversationsMur", serviceC.getNotChatConversation(user_profil));
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
            session.setAttribute(UtilisateurEntity.nameInSession, 
                service.maj(user)
            );
           ModelAndView mv = new ModelAndView("profileView"); // va chercher page wellcome.jsp
           int i = 0;
           while(i<request.getCookies().length && !request.getCookies()[i].getName().equals("user_profile")){
               ++i;
           }
           UtilisateurEntity u = service.getUserByLogin(request.getCookies()[i].getValue());
           service.demanderContact(user, u);
           session.setAttribute(UtilisateurEntity.nameInSession, user);
           mv.addObject("msg", "demande de contact envoyée");
           mv.addObject("profile", u);
           mv.addObject("deja_en_contact", service.peutDemanderContact(user, u));
           return mv;
        } else {
            session.setAttribute(UtilisateurEntity.nameInSession, null);
            session.invalidate();
            return new ModelAndView("profileView");
        }
        
    }
}
