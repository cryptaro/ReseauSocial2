/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 * @author ctran
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Service.HelloService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/index.htm")
public class HelloController{
    
    @Autowired
    HelloService s;
    
        public HelloController() {
    }
    
    @RequestMapping(method=RequestMethod.GET)
    public String init()
    {
        return "index";
    }
    
    @RequestMapping(method=RequestMethod.POST)
    protected ModelAndView handleRequestInternal(
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
         
        ModelAndView mv = new ModelAndView("wellcome"); // va chercher page wellcome.jsp 
        String nom = request.getParameter("nom");
        String result = "Bonjou " + nom;
        s.saveHello(nom);
        mv.addObject("wellcomeMessage", result);
        
        return mv;
    }
}
