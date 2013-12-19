/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import DAO.ConversationEntity;
import DAO.UtilisateurEntity;
import DAO.VisibilityEnum;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Cryptaro
 */
public interface ConversationService {
    public void create(ConversationEntity conv);
    public void update(ConversationEntity conv);
    public List<ConversationEntity> getConversationByOwner(String login);
    public ConversationEntity getConversationById(Long id);
    public List<ConversationEntity> getConversationByDate(Date d);
    public void removeConversation(ConversationEntity conversation);
    public List<ConversationEntity> getAllConversation();
    public List<ConversationEntity> getConversationByVisibility(VisibilityEnum e);
    public List<ConversationEntity> getVisibleConversation(UtilisateurEntity u);
    public boolean isVisibleConversation(UtilisateurEntity u, Long id);
}
