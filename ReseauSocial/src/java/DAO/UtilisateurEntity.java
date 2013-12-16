/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
    
   /* @JoinTable(
            name="Contact_user",
            joinColumns=@JoinColumn(name="login_UtilisateurEntity"),
            inverseJoinColumns = @JoinColumn(name="id_contact"))
    @ManyToMany
    private List<UtilisateurEntity> amiList = new ArrayList<UtilisateurEntity>();
    */
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
/*
    public List<UtilisateurEntity> getAmiList() {
        return amiList;
    }

    public void setAmiList(List<UtilisateurEntity> amiList) {
        this.amiList = amiList;
    }   
*/
    public String getLogin() {
        return login;
    }

    public void setLogin(String id) {
        this.login = id;
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
