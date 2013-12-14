/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import org.springframework.stereotype.Repository;

/**
 *
 * @author ctran
 */
public interface InfoDAO {
    public void save(InfoEntity e);
    public void retrieve(int id);
    public void update(InfoEntity e);
    public void delete(InfoEntity e);
}
