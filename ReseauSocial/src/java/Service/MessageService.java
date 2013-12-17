/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import DAO.MessageEntity;
import DAO.UtilisateurEntity;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Cryptaro
 */
public interface MessageService {
    public void ecrire(MessageEntity msg);
    public MessageEntity getMsgById(Long id);
    public List<MessageEntity> getMsgByUser(String login);
    public List<MessageEntity> getMsgByConversation(Long id);
    public List<MessageEntity> getMsgByDate(Date d);
    public void removeMsg(MessageEntity msg);
    public List<MessageEntity> getAllMsg();
}
