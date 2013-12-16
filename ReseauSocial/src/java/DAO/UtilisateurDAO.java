/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.util.Date;

/**
 *
 * @author CTam
 */
public interface UtilisateurDAO {
    public boolean save(UtilisateurEntity e);
    public UtilisateurEntity retrieve(String log);
    public void update(UtilisateurEntity e);
    public void delete(UtilisateurEntity e);
    public UtilisateurEntity getUser(String log, String pwd);
    public boolean setUser(String log, String pwd, String newpwd, 
            String nom, String prenom, String naissance, boolean sexe,
            String description);
}
