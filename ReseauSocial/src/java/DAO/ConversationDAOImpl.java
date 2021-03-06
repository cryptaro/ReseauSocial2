/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Cryptaro
 */
@Repository("ConversationDAO")
public class ConversationDAOImpl implements ConversationDAO {
    @PersistenceContext(unitName="ReseauSocialPU")
    private EntityManager em;

    @Transactional
    @Override
    public void create(ConversationEntity conv) {
        em.persist(conv);
    }
    
    @Transactional
    @Override
    public void update(ConversationEntity conv){
        em.merge(conv);
    }

    @Transactional
    @Override
    public List<ConversationEntity> getConversationByOwner(String login) {
        return (List<ConversationEntity>) em.createQuery("SELECT c FROM ConversationEntity c WHERE"
                + " c.owner = '"+ login+"'").getResultList();
    }

    @Transactional
    @Override
    public ConversationEntity getConversationById(Long id) {
        return (ConversationEntity) em.createQuery("SELECT c FROM ConversationEntity c WHERE"
                + " c.id =" + id).getSingleResult();
    }

    @Transactional
    @Override
    public List<ConversationEntity> getConversationByDate(Date d) {
        return (List<ConversationEntity>) em.createQuery("SELECT c FROM ConversationEntity c WHERE"
                + " c.date =" + d).getResultList();
    }

    @Transactional
    @Override
    public void removeConversation(ConversationEntity conversation) {
        conversation = em.merge(conversation);
        em.remove(conversation);
    }

    @Transactional
    @Override
    public List<ConversationEntity> getAllConversation() {
        return (List<ConversationEntity>) em.createQuery("SELECT c FROM ConversationEntity c ").getResultList();
    }
    
    @Transactional
    @Override
    public List<ConversationEntity> getConversationByVisibility(VisibilityEnum e){
        return (List<ConversationEntity>) em.createQuery("SELECT c FROM ConversationEntity c where"
                + " c.visibility = '" + e + "'").getResultList();
    }
    
    @Transactional
    @Override
    public List<ConversationEntity> getVisibleConversation(UtilisateurEntity u){
        return (List<ConversationEntity>) em.createQuery("SELECT c FROM ConversationEntity c WHERE"
                + " c.owner = :user OR" 
                + " c.visibility = :visibilitePublic OR" 
                + " (c.visibility = :visibilitePrivate AND c.participants = :user)"
        ).setParameter("user", u).setParameter("visibilitePublic", VisibilityEnum.Public).setParameter("visibilitePrivate", VisibilityEnum.Private).getResultList();
    }
    
    @Transactional
    @Override
    public boolean isVisibleConversation(UtilisateurEntity u, Long id) {
        return em.createQuery("SELECT c FROM ConversationEntity c where c.id = :id AND (c.owner = :user OR  c.visibility = :public "
                + " OR (c.visibility = :private AND c.participants = :user))")
                .setParameter("public", VisibilityEnum.Public).setParameter("private", VisibilityEnum.Private)
                .setParameter("id", id).setParameter("user", u).getResultList().size()==1;       

    }

    @Transactional
    @Override
    public List<ConversationEntity> getChatConversation(UtilisateurEntity u) {
        return (List<ConversationEntity>) em.createQuery("SELECT c FROM ConversationEntity c WHERE"
                + " c.visibility = :visibiliteChat AND ("
                //+ " c.owner = :user OR"
                + " c.participants = :user) "
                + " ORDER BY c.date DESC"
        ).setParameter("user", u).setParameter("visibiliteChat", VisibilityEnum.Chat).getResultList();
    }
    
    @Transactional
    @Override
    public List<ConversationEntity> getNotChatConversation(UtilisateurEntity u){
        return (List<ConversationEntity>) em.createQuery("SELECT c FROM ConversationEntity c WHERE"
                + " c.owner = :user AND( "
                + " c.visibility = :visibilitePublic OR c.visibility = :visibilityPrivate " 
                + " OR c.visibility = :visibiliteFriends )"
                //+ " c.participants = :user) "
                + " ORDER BY c.date DESC"
        ).setParameter("user", u).setParameter("visibilitePublic", VisibilityEnum.Public)
                .setParameter("visibilityPrivate", VisibilityEnum.Private)
                .setParameter("visibiliteFriends", VisibilityEnum.Friends).getResultList();
    }
}
