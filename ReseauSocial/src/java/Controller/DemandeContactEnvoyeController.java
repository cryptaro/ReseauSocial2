/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.UtilisateurEntity;
import Service.UtilisateurService;
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
@RequestMapping("/demandeContactEnvoye.htm")
public class DemandeContactEnvoyeController {
    @Autowired
    UtilisateurService service;
    
    public DemandeContactEnvoyeController() {
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String init(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if(session != null){
            UtilisateurEntity user = (UtilisateurEntity)session.getAttribute(UtilisateurEntity.nameInSession);
            if(user!=null)
                session.setAttribute(UtilisateurEntity.nameInSession, 
                        service.maj(user)
                );
        }
        return "demandeContactEnvoye";
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
            ModelAndView mv = new ModelAndView("demandeContactEnvoye"); 
            String nom_contact_a_annule;
            String nom_check_box;
            int i;
            for(i = 0; i<=user.getDemandesContact().size()+1; ++i){
                nom_check_box = "choix" + i;
                nom_contact_a_annule = request.getParameter(nom_check_box);
                if(nom_contact_a_annule != null)
                   service.annulerDemandesContact(user, service.getUserByLogin(nom_contact_a_annule));
            }
            session.setAttribute(UtilisateurEntity.nameInSession, user);
            mv.addObject("msg", "demande(s) annulée(s)");
            return mv;
        } else {
            session.setAttribute(UtilisateurEntity.nameInSession, null);
            session.invalidate();
            return new ModelAndView("demandeContactEnvoye");
        }
        
    }
}