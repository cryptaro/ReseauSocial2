/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import DAO.UtilisateurDAO;
import DAO.UtilisateurEntity;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ctran
 */
@Service("UtilisateurService")
public class UtilisateurServiceImpl  implements UtilisateurService {
    @Autowired
    private UtilisateurDAO u_dao; 
    
    public boolean inscrire(String login, String pwd){
       UtilisateurEntity u = new UtilisateurEntity();
       u.setLogin(login);
       u.setPwd(pwd);
       return u_dao.save(u);
    }

    @Override
    public UtilisateurEntity getUser(String login, String pwd) {
         UtilisateurEntity u = u_dao.getUser(login, pwd);
         return u;
    }
    
    @Override
    public boolean setUser(String login, String pwd, String newpwd, String nom,
            String prenom, String naissance, boolean sexe, String description) {
         return u_dao.setUser(login, pwd, newpwd, nom, prenom, naissance, sexe, description);
    }
}
