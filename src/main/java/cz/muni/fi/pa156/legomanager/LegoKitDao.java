/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.fi.pa156.legomanager;

import cz.muni.fi.pa165.legomanager.entity.LegoKit;
import java.util.List;

/**
 *
 * @author Petr
 */
public interface LegoKitDao {
    
    public List<LegoKit> getAllLegoKits();
    public void updateLegoKit(LegoKit kit);
    public void deleteLegoKit (LegoKit kit);
    public void addLegoKit (LegoKit kit);
    
}
