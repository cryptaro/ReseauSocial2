/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

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
        } catch (Exception e){}
        return null;
    }
}
