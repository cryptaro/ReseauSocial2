/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Service;

import DAO.InfoDAO;
import DAO.InfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ctran
 */
@Service("HelloService")
public class HelloServiceImpl implements HelloService {

    @Autowired
    private InfoDAO i_dao; 
    
    @Override
    public void saveHello(String name) {
       InfoEntity i = new InfoEntity();
       i.setNom(name);
       i_dao.save(i);
    }
    
}
