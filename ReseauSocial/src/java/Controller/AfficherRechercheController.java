/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controller;

import Service.UtilisateurService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Cryptaro
 */
@Controller
@RequestMapping("/afficherRecherche.htm")
public class AfficherRechercheController {
    
    @Autowired
    UtilisateurService service;
    
    public AfficherRechercheController() {
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView init(@RequestParam("search") String recherche){
        ModelAndView mv = new ModelAndView("afficherRecherche");
        
        mv.addObject("resultatRecherche", service.search(recherche));
        return mv;
    }
    
    @RequestMapping(method=RequestMethod.POST)
    protected String handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        return "afficherRecherche";
    }
}
