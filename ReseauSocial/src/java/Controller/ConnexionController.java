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
@RequestMapping("/connexion.htm")
public class ConnexionController {
    @Autowired
    UtilisateurService service;
    
    public ConnexionController() {
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String init(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return "connexion";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        
        if(session!=null){
            ModelAndView mv = new ModelAndView("accueilConnecte"); // va chercher page wellcome.jsp 
            String pwd = request.getParameter("pwd");
            String log = request.getParameter("log");
            UtilisateurEntity u = service.getUser(log, pwd);
            
            if(u!= null){
                session.setAttribute(UtilisateurEntity.nameInSession, new UtilisateurEntity(u));
                mv.addObject("userName", log);
                return mv;
            }
            
        }
        ModelAndView mv = new ModelAndView("connexion");
        mv.addObject("errorMsg", "Erreur de login ou mot de passe");
        return mv;        
    }
}
