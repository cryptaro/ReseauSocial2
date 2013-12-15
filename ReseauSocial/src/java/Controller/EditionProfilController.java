/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import DAO.UtilisateurEntity;
import Service.UtilisateurService;
import java.util.Date;
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
public class EditionProfilController {
    @Autowired
    UtilisateurService service;
     
    public EditionProfilController() {
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String init(){
        return "editerProfil";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        
        if(session!=null){
            ModelAndView mv = new ModelAndView("editerProfil");
            String pwd = request.getParameter("pwd");
            UtilisateurEntity user = (UtilisateurEntity)request.getSession()
                    .getAttribute(UtilisateurEntity.nameInSession);
            String log = user.getLogin();
            UtilisateurEntity u = service.getUser(log, pwd);
            
            if(u!= null){   // si le mot de passe est le bon
                session.setAttribute(UtilisateurEntity.nameInSession, u);
                String new_pwd1 = request.getParameter("pwd_new1");
                String new_pwd2 = request.getParameter("pwd_new2");
                if(new_pwd1!=new_pwd2){ // si les 2 nouveau pwd ne sont pas identiques
                    new_pwd1=user.getPwd();
                    mv.addObject("msg_pwd_new", "mots de passe non identiques");
                }                
                String nom = request.getParameter("nom");
                if(nom=="") nom = user.getNom();
                String prenom = request.getParameter("prenom");
                if(prenom=="") prenom = user.getPrenom();
                
                Boolean sexe = request.getParameter("sexe")== "male" ? 
                        UtilisateurEntity.male : UtilisateurEntity.female;
                //Date naissance = request.getParameter("naissance");
                
                String description = request.getParameter("description").replaceAll(" ", "");
                if(description=="") description = user.getDescription();
                
                if(service.setUser(log, pwd, new_pwd2, nom, prenom, null, sexe, description)) {
                    session.setAttribute(UtilisateurEntity.nameInSession, service.getUser(log, pwd));
                    mv.addObject("msgEdition", "Modification effectuée");
                } else
                    mv.addObject("msgEdition", "erreur lors de la modification");
                return mv;
            } else {
                
            }

            mv.addObject("msg_pwd", "Erreur mot de passe");
            return mv;
        } else {
            return new ModelAndView("accueil");
        }  
    }
}
