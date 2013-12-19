/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import DAO.ConversationDAO;
import DAO.ConversationEntity;
import DAO.UtilisateurDAO;
import DAO.UtilisateurEntity;
import DAO.VisibilityEnum;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cryptaro
 */
@Service("ConversationService")
public class ConversationServiceImpl implements ConversationService{
    @Autowired
    private ConversationDAO c_dao;
    
    @Autowired
    private UtilisateurDAO u_dao;

    @Override
    public void create(ConversationEntity conv) {
        conv.getParticipants().add(conv.getOwner());
        c_dao.create(conv);
        conv.getOwner().getListeConversations().add(conv);
        u_dao.update(conv.getOwner());
        
    }
    @Override
    public void update(ConversationEntity conv){
        c_dao.update(conv);
    }
    

    @Override
    public List<ConversationEntity> getConversationByOwner(String login) {
        return c_dao.getConversationByOwner(login);
    }

    @Override
    public ConversationEntity getConversationById(Long id) {
        return c_dao.getConversationById(id);
    }

    @Override
    public List<ConversationEntity> getConversationByDate(Date d) {
        return c_dao.getConversationByDate(d);
    }

    @Override
    public void removeConversation(ConversationEntity conversation) {
        c_dao.removeConversation(conversation);
        conversation.getOwner().getListeConversations().remove(conversation);
        u_dao.update(conversation.getOwner());
    }

    @Override
    public List<ConversationEntity> getAllConversation() {
        return c_dao.getAllConversation();
    }
    
    @Override
    public List<ConversationEntity> getConversationByVisibility(VisibilityEnum e){
        return c_dao.getConversationByVisibility(e);
    }

    @Override
    public List<ConversationEntity> getVisibleConversation(UtilisateurEntity u) {
        return c_dao.getVisibleConversation(u);
    }
    
    @Override
    public boolean isVisibleConversation(UtilisateurEntity u, Long id) {
        return c_dao.isVisibleConversation(u,id);
    }
    
    @Override
    public List<ConversationEntity> getChatConversation(UtilisateurEntity u){
        return c_dao.getChatConversation(u);
    }
    @Override
    public List<ConversationEntity> getNotChatConversation(UtilisateurEntity u){
        return c_dao.getNotChatConversation(u);
    }
}
