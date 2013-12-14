/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ctran
 */
@Repository("InfoDAO")
public class InfoDAOImpl implements InfoDAO{
    @PersistenceContext(unitName="ReseauSocialPU")
    private EntityManager em;
    
    @Transactional
    @Override
    public void save(InfoEntity e){
        em.persist(e);
    }

    @Transactional
    @Override
    public void retrieve(int id) {
        em.find(InfoEntity.class, id);
    }

    @Transactional
    @Override
    public void update(InfoEntity e) {
        em.merge(e);
    }

    @Transactional
    @Override
    public void delete(InfoEntity e) {
        e = em.merge(e);
        em.remove(e);
    }
}
