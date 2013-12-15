package DAO;

import DAO.UtilisateurEntity;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2013-12-15T18:59:32")
@StaticMetamodel(MessageEntity.class)
public class MessageEntity_ { 

    public static volatile SingularAttribute<MessageEntity, Long> id;
    public static volatile ListAttribute<MessageEntity, UtilisateurEntity> likers;
    public static volatile SingularAttribute<MessageEntity, UtilisateurEntity> owner;
    public static volatile ListAttribute<MessageEntity, UtilisateurEntity> haters;
    public static volatile SingularAttribute<MessageEntity, String> msg;

}