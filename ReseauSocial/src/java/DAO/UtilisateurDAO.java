/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

/**
 *
 * @author CTam
 */
public interface UtilisateurDAO {
    public void save(UtilisateurEntity e);
    public void retrieve(String log);
    public void update(UtilisateurEntity e);
    public void delete(UtilisateurEntity e);
}
