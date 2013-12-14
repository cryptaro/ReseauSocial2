/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 * @author Valentin GAUTHIER
 */
@Entity
public class MessageEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    String msg; // peut contenir des balises img et videos
    
    
    @Column
    @ManyToOne
    @JoinColumn(name="UtilisateurEntity_fk")
    UtilisateurEntity owner;
    
    @Column
    @JoinTable(
            name="likers",
            joinColumns=@JoinColumn(name="id_message"),
            inverseJoinColumns = @JoinColumn(name="login_UtilisateurEntity"))
    @ManyToMany
    private List<UtilisateurEntity> likers = new ArrayList<UtilisateurEntity>();
    
    @Column
    @JoinTable(
            name="haters",
            joinColumns=@JoinColumn(name="id_message"),
            inverseJoinColumns = @JoinColumn(name="login_UtilisateurEntity"))
    @ManyToMany
    private List<UtilisateurEntity> haters = new ArrayList<UtilisateurEntity>();
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public UtilisateurEntity getOwner() {
        return owner;
    }

    public void setOwner(UtilisateurEntity owner) {
        this.owner = owner;
    }

    public List<UtilisateurEntity> getLikers() {
        return likers;
    }

    public void setLikers(List<UtilisateurEntity> likers) {
        this.likers = likers;
    }

    public List<UtilisateurEntity> getHaters() {
        return haters;
    }

    public void setHaters(List<UtilisateurEntity> haters) {
        this.haters = haters;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MessageEntity)) {
            return false;
        }
        MessageEntity other = (MessageEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return msg;
    }
    
    public String toHTML() {
        return msg; // mettre le code html pour "machin a dit que :" + msg
    }
    
}
