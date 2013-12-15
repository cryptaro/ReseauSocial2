/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
}
