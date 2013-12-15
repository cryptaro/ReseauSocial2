/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author CTam
 */
@Repository("UtilisateurDAO")
public class UtilisateurDAOImpl implements UtilisateurDAO{
    @PersistenceContext(unitName="ReseauSocialPU")
    private EntityManager em;
    
    @Transactional
    @Override
    public void save(UtilisateurEntity e){
        em.persist(e);
    }

    @Transactional
    @Override
    public void retrieve(String log) {
        em.find(InfoEntity.class, log);
    }

    @Transactional
    @Override
    public void update(UtilisateurEntity e) {
        em.merge(e);
    }

    @Transactional
    @Override
    public void delete(UtilisateurEntity e) {
        e = em.merge(e);
        em.remove(e);
    }

    @Override
    public UtilisateurEntity getUser(String log, String pwd) {
        try {
            UtilisateurEntity user = (UtilisateurEntity) em.createQuery("SELECT util FROM UtilisateurEntity util WHERE"
                + " util.login = :login and util.pwd = :pwd").setParameter("login", log)
                .setParameter("pwd", pwd).getResultList().get(0); 
            return user;
        } catch (Exception e){
            // to catch the exception of no user found
        }
        return null;
    }
    
    @Override
    /**
     * @return false si pas d'utilisateur correspondant, true si la modification
     *      a réussi.
     */
    public boolean setUser(String log, String pwd, String newpwd, 
            String nom, String prenom, Date naissance, boolean sexe,
            String description) {
        //try {
            em.createQuery("UPDATE UtilisateurEntity u SET u.nom=:nom, u.prenom=:prenom,"
                    + "u.naissance=:naissance, u.sexe=:sexe, u.description=:description,"
                    + "u.pwd=:newpwd"
                    + " WHERE u.login = :login and u.pwd = :pwd").setParameter("login", log)
                .setParameter("pwd", pwd).setParameter("newpwd", newpwd).setParameter("nom", nom)
                    .setParameter("prenom", prenom).setParameter("description", description)
                    .setParameter("naissance", naissance); 
       /* } catch (Exception e){
            return false;
        }*/
        return true;
    }
}
