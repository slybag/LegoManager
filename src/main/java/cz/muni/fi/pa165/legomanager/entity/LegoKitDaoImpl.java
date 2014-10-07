/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa165.legomanager.entity;

import java.util.List;
import javax.persistence.EntityManager;


/**
 *
 * @author Petr
 */
public class LegoKitDaoImpl implements LegoKitDao{
    
    List<LegoKit> kits;
    EntityManager em;
    
    @Override
    public List<LegoKit> getAllLegoKits() {
        return null;
    }

    @Override
    public void updateLegoKit(LegoKit kit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteLegoKit(LegoKit kit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addLegoKit(LegoKit kit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
