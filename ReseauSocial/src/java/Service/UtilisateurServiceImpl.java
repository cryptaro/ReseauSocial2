/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import DAO.UtilisateurDAO;
import DAO.UtilisateurEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
       UtilisateurEntity u = new UtilisateurEntity(login, pwd);
       return u_dao.save(u);
    }

    @Override
    public UtilisateurEntity getUser(String login, String pwd) {
         UtilisateurEntity u = u_dao.getUser(login, pwd);
         return u;
    }
    
    @Override
    public List<UtilisateurEntity> getAllUser() {
         return u_dao.getAllUser();
    }
    
    @Override
    public boolean setUser(String login, String pwd, String newpwd, String nom,
            String prenom, String naissance, boolean sexe, String description) {
         return u_dao.setUser(login, pwd, newpwd, nom, prenom, naissance, sexe, description);
    }

    @Override
    public void removeUser(UtilisateurEntity u) {
        u_dao.delete(u);
    }

    @Override
    public UtilisateurEntity getUserByLogin(String login) {
        return u_dao.retrieve(login);
    }
    
    @Override
    public List<UtilisateurEntity> search(String s){
        return u_dao.search(s);
    }
    
    @Override
    public void demanderContact(UtilisateurEntity demandeur, UtilisateurEntity contact_demande) {
        demandeur.getDemandesContact().add(contact_demande);
        u_dao.update(demandeur);
        contact_demande.getDemandesContactUnchecked().add(demandeur);
        u_dao.update(contact_demande);
    }

    @Override
    public void annulerDemandesContact(UtilisateurEntity u, UtilisateurEntity contact_demande_annule) {
        u.getDemandesContact().remove(contact_demande_annule);
        u_dao.update(u);
        contact_demande_annule.getDemandesContactUnchecked().remove(u);
        u_dao.update(contact_demande_annule);
    }

    @Override
    public List<UtilisateurEntity> getDemandesContactVersUser(UtilisateurEntity u) {
        return u_dao.getDemandesContactVersUser(u);
    }
    
    @Override
    public  void accepterContact(UtilisateurEntity u1, UtilisateurEntity u2){
        u1.getListeContact().add(u2);
        u_dao.update(u1);
        u2.getDemandesContact().remove(u1);
        u2.getListeContact().add(u1);
        u_dao.update(u2);
    }
    
    @Override
    public boolean peutDemanderContact(UtilisateurEntity u1, UtilisateurEntity u2){
        return u1.getDemandesContact().contains(u2) || u1.getListeContact().contains(u2) 
                || u2.getDemandesContact().contains(u1);
    }
    
    @Override
    public void supprimerContact(UtilisateurEntity u1, UtilisateurEntity u2){
        u1.getListeContact().remove(u2);
        u_dao.update(u1);
        u2.getListeContact().remove(u1);
        u_dao.update(u2);
    }
    
    @Override
    public void denotifyDemandesContact(UtilisateurEntity u1){
        u1.setDemandesContactUnchecked(new ArrayList<UtilisateurEntity>());
        u_dao.update(u1);
    }
    
    @Override
    public UtilisateurEntity maj(UtilisateurEntity u){
        return u_dao.retrieve(u.getLogin());
    }
    
    @Override
    public void update(UtilisateurEntity e){
        u_dao.update(e);
    }
}
