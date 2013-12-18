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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

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
    @Temporal(javax.persistence.TemporalType.DATE)
    Date date;

    @ManyToOne
    ConversationEntity conversation;
    
    
    @ManyToOne
    UtilisateurEntity owner;
    
    @ManyToMany
    private List<UtilisateurEntity> likers = new ArrayList<UtilisateurEntity>();
    
    @ManyToMany
    private List<UtilisateurEntity> haters = new ArrayList<UtilisateurEntity>();

    public MessageEntity() {
        date = new Date();
        msg = "";
    }

    public MessageEntity(String msg, UtilisateurEntity owner, Date date, ConversationEntity conversation) {
        this.msg = msg;
        this.owner = owner;
        this.date = date;
        this.conversation = conversation;
    }
    
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ConversationEntity getConversation() {
        return conversation;
    }

    public void setConversation(ConversationEntity conversation) {
        this.conversation = conversation;
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
    
    /*
     * Les ajout pour le code HTTP 
     */
    
    public void addImg(String path, String alt){
        msg += "<img src="+path+" alt="+alt+" >";
    }
    
    public void addImg(String path, String alt, int width, int height){
        msg += "<img src="+path+" width="+width+" height="+height+" alt="+alt+" >";
    }
    
    /**
     * Ajoute une video dans le message
     * @param width     lageur
     * @param height    hauteur
     * @param src_mp4   lien vers video en *.mp4 (si necessaire, "" sinon)
     * @param src_webm  lien vers video en *.webm (si necessaire, "" sinon)
     * @param src_avi   lien vers video en *.avi (si necessaire, "" sinon)
     * @param src_flv   lien vers video en *.flv (si necessaire, "" sinon)
     * @param src_mkv   lien vers video en *.mkv (si necessaire, "" sinon)
     * @param src_wmv   lien vers video en *.wmv (si necessaire, "" sinon)
     * @param msg_alt   message si la video n'est pas affichable
     * @param posterImg image de poster de la video
     */
    public void addVideo(int width, int height, String src_mp4, String src_webm,
        String src_avi, String src_flv, String src_mkv, String src_wmv, 
        String msg_alt, String posterImg){
        // /!\ tester si il faut bien ajouter les \" autour des src_* !!
        
        msg += "<video width="+width+" height="+height+" controls=\"controls\" poster=\""+posterImg+"\">";
        
        if(src_mp4.replaceAll(" ", "").compareTo("")!=0)    
            // le mp4 en premier pour etre sur d'etre lu sur tablette / smartphone
            msg += " <source src=\""+src_mp4+"\" type=\"video/mp4\" />";
        if(src_webm.replaceAll(" ", "").compareTo("")!=0)
            msg += " <source src=\""+src_webm+"\" type=\"video/webm\" />";
        if(src_avi.replaceAll(" ", "").compareTo("")!=0)
            msg += " <source src=\""+src_avi+"\" type=\"video/avi\" />";
        if(src_flv.replaceAll(" ", "").compareTo("")!=0)
            msg += " <source src=\""+src_flv+"\" type=\"video/flv\" />";
        if(src_mkv.replaceAll(" ", "").compareTo("")!=0)
            msg += " <source src=\""+src_mkv+"\" type=\"video/mkv\" />";
        if(src_wmv.replaceAll(" ", "").compareTo("")!=0)
            msg += " <source src=\""+src_wmv+"\" type=\"video/wmv\" />";
        msg += msg_alt;
        msg += "</video>";
    }
    
    
    public void addVideo(String src_mp4, String src_webm,
        String src_avi, String src_flv, String src_mkv, String src_wmv, 
        String msg_alt, String posterImg){
        // /!\ tester si il faut bien ajouter les \" autour des src_* !!
        
        msg += "<video controls=\"controls\" poster=\""+posterImg+"\">";
        
        if(src_mp4.replaceAll(" ", "").compareTo("")!=0)    
            // le mp4 en premier pour etre sur d'etre lu sur tablette / smartphone
            msg += " <source src=\""+src_mp4+"\" type=\"video/mp4\" />";
        if(src_webm.replaceAll(" ", "").compareTo("")!=0)
            msg += " <source src=\""+src_webm+"\" type=\"video/webm\" />";
        if(src_avi.replaceAll(" ", "").compareTo("")!=0)
            msg += " <source src=\""+src_avi+"\" type=\"video/avi\" />";
        if(src_flv.replaceAll(" ", "").compareTo("")!=0)
            msg += " <source src=\""+src_flv+"\" type=\"video/flv\" />";
        if(src_mkv.replaceAll(" ", "").compareTo("")!=0)
            msg += " <source src=\""+src_mkv+"\" type=\"video/mkv\" />";
        if(src_wmv.replaceAll(" ", "").compareTo("")!=0)
            msg += " <source src=\""+src_wmv+"\" type=\"video/wmv\" />";
        msg += msg_alt;
        msg += "</video>";
    }
    
}
