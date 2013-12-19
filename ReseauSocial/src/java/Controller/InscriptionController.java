/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

/**
 * @author ctran
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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

@Controller
@RequestMapping("/inscription.htm")
public class InscriptionController{
    @Autowired
    UtilisateurService s;
    
    public InscriptionController() {
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String init(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        UtilisateurEntity user = (UtilisateurEntity)session.getAttribute(UtilisateurEntity.nameInSession);
        if(user!=null)
            session.setAttribute(UtilisateurEntity.nameInSession, 
                    s.maj(user)
            );
        return "inscription";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ModelAndView mv;
        String pwd = request.getParameter("pwd");
        String log = HtmlUtils.toHtml(request.getParameter("log").replaceAll("@", "").replaceAll(";", "").replaceAll(",", "").replaceAll(" ", "_"));

        if(s.getUserByLogin(log)!=null || !s.inscrire(log, pwd)){
            mv = new ModelAndView("inscription");
            mv.addObject("errorMsg", "Ce login existe déja");
        } else{
            mv = new ModelAndView("wellcome"); // va chercher page wellcome.jsp 
            mv.addObject("wellcomeMessage", "Vous avez ete inscrit avec ce login : " + log);
        }
        return mv;
    }
}