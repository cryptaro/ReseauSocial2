/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import DAO.UtilisateurEntity;
import java.util.List;

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
    public List<UtilisateurEntity> getAllUser();
    public List<UtilisateurEntity> search(String s);
    public void demanderContact(UtilisateurEntity demandeur, UtilisateurEntity contact_demande);
    public void annulerDemandesContact(UtilisateurEntity u, UtilisateurEntity contact_demande_annule);
    public  List<UtilisateurEntity> getDemandesContactVersUser(UtilisateurEntity u);
    public  void accepterContact(UtilisateurEntity u1, UtilisateurEntity u2);
    public boolean peutDemanderContact(UtilisateurEntity u1, UtilisateurEntity u2);
    public void supprimerContact(UtilisateurEntity u1, UtilisateurEntity u2);
    public void denotifyDemandesContact(UtilisateurEntity u1);
    public UtilisateurEntity maj(UtilisateurEntity u);
}
