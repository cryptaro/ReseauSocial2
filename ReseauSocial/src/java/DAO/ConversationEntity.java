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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author Valentin GAUTHIER
 */
@Entity
public class ConversationEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    Date date;
    
    @Column
    VisibilityEnum visibility;

    @ManyToOne
    UtilisateurEntity owner;
    
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "conversationParticipants", 
            joinColumns = { @JoinColumn(name = "participants")}, 
            inverseJoinColumns={@JoinColumn(name="convers")})  
    List<UtilisateurEntity> participants = new ArrayList<UtilisateurEntity>();
    
    
    
    public ConversationEntity() {
        date = new Date();
        visibility = VisibilityEnum.Public;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public VisibilityEnum getVisibility() {
        return visibility;
    }

    public void setVisibility(VisibilityEnum visibility) {
        this.visibility = visibility;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    public UtilisateurEntity getOwner() {
        return owner;
    }

    public void setOwner(UtilisateurEntity owner) {
        this.owner = owner;
    }

    public List<UtilisateurEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(List<UtilisateurEntity> participants) {
        this.participants = participants;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConversationEntity)) {
            return false;
        }
        ConversationEntity other = (ConversationEntity) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Conversation[ id=" + id + " ]";
    }
    
}
