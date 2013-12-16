/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import DAO.UtilisateurEntity;
import java.util.Date;

/**
 *
 * @author ctran
 */
public interface UtilisateurService {
    public boolean inscrire(String login, String pwd);
    public UtilisateurEntity getUser(String login, String pwd);
    public boolean setUser(String login, String pwd, String newpwd, String nom,
            String prenom, String naissance, boolean sexe, String description);
    public void removeUser(UtilisateurEntity u);
    public UtilisateurEntity getUserByLogin(String login);
}
