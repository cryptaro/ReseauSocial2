package DAO;

import DAO.UtilisateurEntity;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.0.v20130507-rNA", date="2013-12-14T21:16:07")
@StaticMetamodel(UtilisateurEntity.class)
public class UtilisateurEntity_ { 

    public static volatile SingularAttribute<UtilisateurEntity, String> prenom;
    public static volatile SingularAttribute<UtilisateurEntity, Date> naissance;
    public static volatile ListAttribute<UtilisateurEntity, UtilisateurEntity> amiList;
    public static volatile SingularAttribute<UtilisateurEntity, String> pwd;
    public static volatile SingularAttribute<UtilisateurEntity, String> description;
    public static volatile SingularAttribute<UtilisateurEntity, Boolean> sexe;
    public static volatile SingularAttribute<UtilisateurEntity, String> login;
    public static volatile SingularAttribute<UtilisateurEntity, String> nom;

}