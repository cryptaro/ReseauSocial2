/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import DAO.UtilisateurEntity;

/**
 *
 * @author ctran
 */
public interface UtilisateurService {
    public void inscrire(String login, String pwd);
    public UtilisateurEntity getUser(String login, String pwd);
}
