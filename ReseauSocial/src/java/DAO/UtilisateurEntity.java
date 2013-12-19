/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author Cryptaro
 */
@Entity(name="UtilisateurEntity")
@Table(name="Utilisateur")
/*@NamedQueries({
    @NamedQuery(name = "Utilisateur.findAll", query = "SELECT u FROM Utilisateur u"),
    @NamedQuery(name = "Utilisateur.findByLogin", query = "SELECT u FROM Utilisateur u WHERE u.login = :login"),
    @NamedQuery(name = "Utilisateur.findByLogAndPwd", query = "SELECT u FROM Utilisateur WHERE u.login = :login and u.pwd = :pwd"),
    })*/
public class UtilisateurEntity implements Serializable {
    public static String nameInSession = "utilisateur";
    
    private static final long serialVersionUID = 1L;
    @Id
    private String login;
    
    @Column
    private String nom="";
    
    @Column
    private String prenom="";
    
    @Column
    private String pwd="";
    
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date naissance = new Date();
    
    @Column
    private boolean sexe;
    
    public static boolean female = true;
    public static boolean male = !female;
    
    @Column
    private String description="";    
    
    @ManyToMany()
    private List<UtilisateurEntity> demandes_contact = new ArrayList<UtilisateurEntity>();
    
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_CONTACT", 
            joinColumns = { @JoinColumn(name = "user_id")}, 
            inverseJoinColumns={@JoinColumn(name="liste_contact_id")})  
    private List<UtilisateurEntity> liste_contact = new ArrayList<UtilisateurEntity>();
    
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "USER_DEMANDE_CONTACT_UNCHECKED", 
            joinColumns = { @JoinColumn(name = "user_id")}, 
            inverseJoinColumns={@JoinColumn(name="liste_contact_id")})  
    private List<UtilisateurEntity> demandes_contact_unchecked = new ArrayList<UtilisateurEntity>();
  
    @OneToMany(mappedBy="owner") 
    private List<ConversationEntity> liste_conversations = new ArrayList<ConversationEntity>();    
    
    public UtilisateurEntity(){ }
    
    public UtilisateurEntity(String _log, String _pwd){
        login = _log;
        pwd = _pwd;
    }
    
    public UtilisateurEntity(String _log, String _pwd, String _nom,
          String _prenom, Date _naissance, boolean _sexe, String _description){
        login = _log;
        pwd = _pwd;
        prenom = _prenom;
        nom = _nom;
        description = _description;
        naissance = _naissance;
    }
    
    public UtilisateurEntity(UtilisateurEntity user){
        login = user.login;
        pwd = user.pwd;
        prenom = user.prenom;
        nom = user.nom;
        description = user.description;
        naissance = user.naissance;
        sexe = user.sexe;
        demandes_contact = user.demandes_contact;
        liste_contact = user.liste_contact;
        liste_conversations = user.liste_conversations;
        demandes_contact_unchecked = user.demandes_contact_unchecked;
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Date getNaissance() {
        return naissance;
    }

    public void setNaissance(Date naissance) {
        this.naissance = naissance;
    }

    public boolean getSexe() {
        return sexe;
    }

    public void setSexe(boolean sexe) {
        this.sexe = sexe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<UtilisateurEntity> getDemandesContact() {
        return demandes_contact;
    }

    public void setDemandesContact(List<UtilisateurEntity> d_contact) {
        this.demandes_contact = d_contact;
    }
    
    public List<UtilisateurEntity> getListeContact() {
        return liste_contact;
    }

    public void setListeContact(List<UtilisateurEntity> liste_contact) {
        this.liste_contact = liste_contact;
    }
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String id) {
        this.login = id;
    }
    
    public List<ConversationEntity> getListeConversations() {
        return liste_conversations;
    }

    public void setListeConversations(List<ConversationEntity> liste_conversations) {
        this.liste_conversations = liste_conversations;
    }

    public List<UtilisateurEntity> getDemandesContactUnchecked() {
        return demandes_contact_unchecked;
    }

    public void setDemandesContactUnchecked(List<UtilisateurEntity> demandes_contact_unchecked) {
        this.demandes_contact_unchecked = demandes_contact_unchecked;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (login != null ? login.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UtilisateurEntity)) {
            return false;
        }
        UtilisateurEntity other = (UtilisateurEntity) object;
        if ((this.login == null && other.login != null) || (this.login != null && !this.login.equals(other.login))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.UtilisateurEntity[ id=" + login + " sexe = "+ sexe + " description = "+ description +"]";
    }
    
}
