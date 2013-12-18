/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Cryptaro
 */
public interface MessageDAO {
    public void ecrire(MessageEntity msg);
    public MessageEntity getMsgById(Long id);
    public List<MessageEntity> getMsgByUser(String login);
    public List<MessageEntity> getMsgByConversation(ConversationEntity c);
    public List<MessageEntity> getMsgByDate(Date d);
    public void removeMsg(MessageEntity msg);
    public List<MessageEntity> getAllMsg();
}
