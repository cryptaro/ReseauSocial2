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
    public boolean save(UtilisateurEntity e){
        try {
            em.persist(e);
        } catch(Exception except){
            return false;
        }
        return true;
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

    @Transactional
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
    
    @Transactional
    @Override
    /**
     * @return false si pas d'utilisateur correspondant, true si la modification
     *      a réussi.
     */
    public boolean setUser(String log, String pwd, String newpwd, 
            String nom, String prenom, String naissance, boolean sexe,
            String description) {
        //try {
            return em.createQuery("UPDATE UtilisateurEntity u SET u.nom='"+nom+"', u.prenom='"+prenom+"',"
                    + "u.sexe="+(sexe?1:0)+", u.description='"+description+"',"
                    + "u.pwd='"+newpwd+"', u.naissance='"+naissance+"'"
                    + " WHERE u.login = '"+log+"' and u.pwd = '"+pwd+"'").executeUpdate() == 1; 
        /*} catch (Exception e){
            return false;
        }*/
    }
}
