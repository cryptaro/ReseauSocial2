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
 * @author Cryptaro
 */
@Controller
@RequestMapping("/supprimerCompte.htm")
public class SuppressionCompteController {
    @Autowired
    UtilisateurService service;
    
    public SuppressionCompteController() {
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String init(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        UtilisateurEntity user = (UtilisateurEntity)session.getAttribute(UtilisateurEntity.nameInSession);
        if(user!=null)
            session.setAttribute(UtilisateurEntity.nameInSession, 
                    service.maj(user)
            );
        return "supprimerCompte";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if(session!=null) {
            UtilisateurEntity u = (UtilisateurEntity)session.getAttribute(UtilisateurEntity.nameInSession);
            if(u != null){
                String pwd = request.getParameter("pwd");
                
                if(service.getUser(u.getLogin(), pwd) != null){
                    service.removeUser(u);
                    session.setAttribute(UtilisateurEntity.nameInSession, null);
                    session.invalidate();
                } else {
                    ModelAndView mv = new ModelAndView("supprimerCompte");
                    mv.addObject("errorMsg", "Mauvais mot de passe");                    
                }
            } else {
                session.invalidate();
            }
        }
        return new ModelAndView("accueil");        
    }
}
