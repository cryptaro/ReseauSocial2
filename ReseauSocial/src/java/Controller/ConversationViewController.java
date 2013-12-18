/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.ConversationEntity;
import DAO.UtilisateurEntity;
import Service.ConversationService;
import Service.MessageService;
import Service.UtilisateurService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    public ConversationViewController() {
    }
   
    
    @RequestMapping(method=RequestMethod.POST)
    public ModelAndView init(@RequestParam("conversation") String selectedConversation,HttpServletRequest request,
            HttpServletResponse response){
        UtilisateurEntity user=null;
        HttpSession session = request.getSession(false);
        if(session==null || (user=(UtilisateurEntity)session.getAttribute(UtilisateurEntity.nameInSession)) == null) {
            session.invalidate();
            return new ModelAndView("accueil");
        }
        ModelAndView mv = new ModelAndView("conversationView");
        Long conversSelectedId;
        try {
            conversSelectedId = new Long(selectedConversation);
        } catch (Exception e){
            List<ConversationEntity> visibleConvers = serviceConvers.getVisibleConversation(user.getLogin());
            
            if(visibleConvers.size()>0) {
                mv.addObject("conversations", visibleConvers);
                mv.addObject("selectedConversation", serviceConvers.getConversationById(visibleConvers.get(0).getId()));
                mv.addObject("messagesSelectedConversation", serviceMsg.getMsgByConversation(visibleConvers.get(0).getId()));
            }
            return mv;
        }
        
        mv.addObject("conversations", serviceConvers.getVisibleConversation(user.getLogin()));
        mv.addObject("selectedConversation", serviceConvers.getConversationById(conversSelectedId));
        mv.addObject("messagesSelectedConversation", serviceMsg.getMsgByConversation(conversSelectedId));
        
        return mv;
    }
    
    
    @RequestMapping(method=RequestMethod.GET)
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        UtilisateurEntity user=null;
        HttpSession session = request.getSession(false);
        if(session==null || (user=(UtilisateurEntity)session.getAttribute(UtilisateurEntity.nameInSession)) == null) {
            session.invalidate();
            return new ModelAndView("accueil");
        }
        ModelAndView mv = new ModelAndView("conversationView");
        try {
            mv.addObject("conversations", serviceConvers.getVisibleConversation(user.getLogin()));
        } catch (Exception e){
            mv.addObject("errorConversation",e.toString());
            mv.addObject("conversations", null);
        }       
        
        return mv;
    }
}
