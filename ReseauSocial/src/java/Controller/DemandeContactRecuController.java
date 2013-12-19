/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

/**
 *
 * @author CTam
 */

import DAO.UtilisateurEntity;
import Service.UtilisateurService;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
/**
 *
 * @author CTam
 */

@Controller
@RequestMapping("/demandeContactRecu.htm")
public class DemandeContactRecuController {
    @Autowired
    UtilisateurService service;
    
    public DemandeContactRecuController() {
    }
    
    @RequestMapping(method=RequestMethod.GET)
    protected ModelAndView init(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("demandeContactRecu"); 
        UtilisateurEntity user=null;
        HttpSession session = request.getSession(false);
        if(session==null || (user=(UtilisateurEntity)session.getAttribute(UtilisateurEntity.nameInSession)) == null)
            session.invalidate();
        else{
            session.setAttribute(UtilisateurEntity.nameInSession, 
                service.maj(user)
            );
            service.denotifyDemandesContact(user);
            mv.addObject("contacts_possible", service.getDemandesContactVersUser(user));
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
            ModelAndView mv = new ModelAndView("demandeContactRecu"); 
            String choix;
            String nom_radio_button;
            int i = 0;
            List<UtilisateurEntity> contacts_possible = service.getDemandesContactVersUser(user);
            Iterator it = contacts_possible.iterator();
            UtilisateurEntity contact_possible;
            String msg = "";
            while(it.hasNext()){
                contact_possible = (UtilisateurEntity) it.next();
                nom_radio_button = "contact" + i;
                choix = request.getParameter(nom_radio_button);
                if("accepter".equals(choix)){
                    service.accepterContact(user, contact_possible);
                }else if("decliner".equals(choix)){
                    service.annulerDemandesContact(contact_possible, user);
                }
                ++i;
            }
            mv.addObject("contacts_possible", service.getDemandesContactVersUser(user));
            mv.addObject("msg", msg );//"requête traitée");
            return mv;
        } else {
            session.setAttribute(UtilisateurEntity.nameInSession, null);
            session.invalidate();
            return new ModelAndView("demandeContactRecu");
        }
        
    }
}
