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
@RequestMapping("/deconnexion.htm")
public class DeconnexionController {
    @Autowired
    UtilisateurService service;
    public DeconnexionController() {
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
        return "deconnexion";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    protected String handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        request.getSession().setAttribute(UtilisateurEntity.nameInSession, null);
        request.getSession().invalidate();
        return "accueil";        
    }
}
