/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import DAO.ConversationEntity;
import DAO.MessageDAO;
import DAO.MessageEntity;
import DAO.UtilisateurDAO;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cryptaro
 */
@Service("MessageService")
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageDAO m_dao;
    
    @Autowired
    private UtilisateurDAO u_dao;
    @Autowired
    private ConversationService c_dao;

    @Override
    public void ecrire(MessageEntity msg) {
        m_dao.ecrire(msg);
        msg.getConversation().getListMessage().add(msg);
        c_dao.update(msg.getConversation());
        msg.getOwner().getListMessage().add(msg);
        u_dao.update(msg.getOwner());
    }

    @Override
    public List<MessageEntity> getMsgByUser(String login) {
        return m_dao.getMsgByUser(login);
    }
    
    @Override
    public MessageEntity getMsgById(Long id) {
        return m_dao.getMsgById(id);
    }

    @Override
    public List<MessageEntity> getMsgByConversation(ConversationEntity c) {
        return m_dao.getMsgByConversation(c);
    }

    @Override
    public List<MessageEntity> getMsgByDate(Date d) {
        return m_dao.getMsgByDate(d);
    }

    @Override
    public void removeMsg(MessageEntity msg) {
        msg.getOwner().getListMessage().remove(msg);
        u_dao.update(msg.getOwner());
        m_dao.removeMsg(msg);
    }

    @Override
    public List<MessageEntity> getAllMsg() {
        return m_dao.getAllMsg();
    }
    
}
