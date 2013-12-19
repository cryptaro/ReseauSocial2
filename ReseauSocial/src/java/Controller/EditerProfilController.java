/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.UtilisateurEntity;
import Service.HtmlUtils;
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
@RequestMapping("/editerProfil.htm")
public class EditerProfilController {
    @Autowired
    UtilisateurService service;
    
    public EditerProfilController() {
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
        return "editerProfil";
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
            ModelAndView mv = new ModelAndView("editerProfil"); // va chercher page wellcome.jsp 
            String pwd = request.getParameter("pwd");
            String pwd_new1 = request.getParameter("pwd_new1");
            String pwd_new2 = request.getParameter("pwd_new2");
            if(pwd_new1.replaceAll(" ", "").compareTo("") == 0){
                pwd_new1 = user.getPwd();
            }
            if( pwd_new1.compareTo(pwd_new2)!=0) {
                mv.addObject("msg_pwd_new", "error recopie password");
                pwd_new1 = user.getPwd();
            }
            
            String description = request.getParameter("description");
            if(description.replaceAll(" ", "").compareTo("") == 0 ) description = user.getDescription();
            
            String naissance = request.getParameter("naissance");
            if(naissance.replaceAll(" ", "").compareTo("") == 0 ) naissance = user.getNaissance().toString();
            
            String nom = request.getParameter("nom");
            if(nom.replaceAll(" ", "").compareTo("") == 0) nom = user.getNom();
            
            String prenom = request.getParameter("prenom");
            if(prenom.replaceAll(" ", "").compareTo("") == 0 ) prenom = user.getPrenom();
            
            boolean sexe = request.getParameter("sexe").compareTo("1") == 0 ? true : false;
  
            mv.addObject("default_prenom", prenom);
            mv.addObject("default_nom", nom);
            mv.addObject("default_date", naissance);
            mv.addObject("default_description", description);
            
            
            if(service.setUser(user.getLogin(), pwd, pwd_new1, HtmlUtils.toHtml(nom), HtmlUtils.toHtml(prenom), naissance, sexe, HtmlUtils.toHtml(description))){
                UtilisateurEntity u = service.getUser(user.getLogin(), pwd_new1);
                session.setAttribute(UtilisateurEntity.nameInSession, new UtilisateurEntity(u));
                mv.addObject("msgEdition", "Profil updated");
            } else {
                mv.addObject("msgEdition", "Error updating profile");
            }
            return mv;
        } else {
            session.setAttribute(UtilisateurEntity.nameInSession, null);
            session.invalidate();
            return new ModelAndView("editerProfil");
        }
        
    }
}