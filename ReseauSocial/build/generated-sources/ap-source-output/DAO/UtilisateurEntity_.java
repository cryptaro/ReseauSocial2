package DAO;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2013-12-15T18:59:32")
@StaticMetamodel(UtilisateurEntity.class)
public class UtilisateurEntity_ { 

    public static volatile SingularAttribute<UtilisateurEntity, String> prenom;
    public static volatile SingularAttribute<UtilisateurEntity, Date> naissance;
    public static volatile SingularAttribute<UtilisateurEntity, String> pwd;
    public static volatile SingularAttribute<UtilisateurEntity, String> description;
    public static volatile SingularAttribute<UtilisateurEntity, Boolean> sexe;
    public static volatile SingularAttribute<UtilisateurEntity, String> login;
    public static volatile SingularAttribute<UtilisateurEntity, String> nom;

}