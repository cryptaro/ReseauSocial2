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
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cryptaro
 */
@Service("ConversationService")
public class ConversationServiceImpl implements ConversationService{
    @Autowired
    private ConversationDAO c_dao;

    @Override
    public void create(ConversationEntity conv) {
        c_dao.create(conv);
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
    }

    @Override
    public List<ConversationEntity> getAllConversation() {
        return c_dao.getAllConversation();
    }
    
}