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
    public void save(InfoEntity e);
    public void retrieve(int id);
    public void update(InfoEntity e);
    public void delete(InfoEntity e);
}
