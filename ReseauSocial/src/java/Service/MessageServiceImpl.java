/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

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

    @Override
    public void ecrire(MessageEntity msg) {
        m_dao.ecrire(msg);
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
    public List<MessageEntity> getMsgByConversation(Long id) {
        return m_dao.getMsgByConversation(id);
    }

    @Override
    public List<MessageEntity> getMsgByDate(Date d) {
        return m_dao.getMsgByDate(d);
    }

    @Override
    public void removeMsg(MessageEntity msg) {
        m_dao.removeMsg(msg);
    }

    @Override
    public List<MessageEntity> getAllMsg() {
        return m_dao.getAllMsg();
    }
    
}
