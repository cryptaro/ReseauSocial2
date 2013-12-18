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
 *
 * @author Cryptaro
 */
@Repository("MessageDAO")
public class MessageDAOImpl implements MessageDAO{
    @PersistenceContext(unitName="ReseauSocialPU")
    private EntityManager em;
    
    @Transactional
    @Override
    public void ecrire(MessageEntity msg) {
        em.persist(msg);
    }

    @Transactional
    @Override
    public List<MessageEntity> getMsgByUser(String login) {
        return (List<MessageEntity>) em.createQuery("SELECT m FROM MessageEntity m WHERE"
                + " m.owner = '"+ login+"'").getResultList();
    }
    
    public MessageEntity getMsgById(Long id){
        return (MessageEntity) em.createQuery("SELECT m FROM MessageEntity m WHERE"
                + " m.id = "+ id).getResultList().get(0);
    }

    @Transactional
    @Override
    public List<MessageEntity> getMsgByConversation(ConversationEntity c) {
        return (List<MessageEntity>) em.createQuery("SELECT m FROM MessageEntity m WHERE"
                + " m.conversation.id = :convers").setParameter("convers", c).getResultList();
    }

    @Transactional
    @Override
    public List<MessageEntity> getMsgByDate(Date d) {
        return (List<MessageEntity>) em.createQuery("SELECT m FROM MessageEntity m WHERE"
                + " m.date = " + d).getResultList();
    }

    @Transactional
    @Override
    public void removeMsg(MessageEntity msg) {
        msg = em.merge(msg);
        em.remove(msg);
    }

    @Transactional
    @Override
    public List<MessageEntity> getAllMsg() {
        return (List<MessageEntity>) em.createQuery("SELECT m FROM MessageEntity m ").getResultList();
    }
    
}
