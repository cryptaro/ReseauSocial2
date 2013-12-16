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
import Service.UtilisateurService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public String init() {
        return "inscription";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
         
        ModelAndView mv;
        String pwd = request.getParameter("pwd");
        String log = request.getParameter("log");

        if(!s.inscrire(log, pwd)){
            mv = new ModelAndView("inscription");
            mv.addObject("errorMsg", "Ce login existe déja");
        } else{
            mv = new ModelAndView("wellcome"); // va chercher page wellcome.jsp 
            mv.addObject("wellcomeMessage", "Vous avez ete inscrit " + log);
        }
        
        return mv;
    }
}